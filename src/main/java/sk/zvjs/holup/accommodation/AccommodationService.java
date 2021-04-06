package sk.zvjs.holup.accommodation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.zvjs.holup.accommodation.convert.Attributes;
import sk.zvjs.holup.accommodation.convert.Converter;
import sk.zvjs.holup.accommodation.convert.Welcome;
import sk.zvjs.holup.address.Address;
import sk.zvjs.holup.address.AddressComponent;
import sk.zvjs.holup.address.ConvertAddress;
import sk.zvjs.holup.address.Location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AccommodationService {
    private static final String ACCOMMODATIONS_URL = "https://services-eu1.arcgis.com/v8rjTx8d1cMu13Tc/ArcGIS/rest/services/poskytovatelia_ubytovania_view/FeatureServer/0/query?where=1%3D1&outFields=*&f=pjson&outSr=4326";
    private final AccommodationRepository accommodationRepository;

    @Autowired
    public AccommodationService(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    public List<Accommodation> fetchAccommodations(AccommodationDTO accommodationDTO) {
        List<Accommodation> accommodations =  accommodationRepository.findAllAccommodationsByDTO(
                accommodationDTO.getType(),
                accommodationDTO.getAge(),
                accommodationDTO.getGender(),
                accommodationDTO.getRegion()
        );
        Double inputDistance = accommodationDTO.getDistance();
        Location usersLocation = accommodationDTO.getLocation();
        if (inputDistance != null && usersLocation != null) {
            List<Accommodation> accommodationsByLocation = new ArrayList<>();
            for (Accommodation accommodation : accommodations) {
                double accommodationDistance = distance(accommodation.getLat(), usersLocation.getLat(), accommodation.getLon(), usersLocation.getLng());
                if (accommodationDistance <= inputDistance)
                    accommodationsByLocation.add(accommodation);
            }
            return accommodationsByLocation;
        }
        return accommodations;

    }

    public static double distance(double lat1, double lat2, double lon1, double lon2) {
        final int R = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }

    public void saveAccommodationsToDatabase() throws IOException {
        String accommodationsString = fetchDataFromAPI(ACCOMMODATIONS_URL);
        Welcome welcome = Converter.fromJsonString(accommodationsString);

        List<Accommodation> accommodations = new ArrayList<>();
        for (var feature : welcome.getFeatures()) {
            Attributes attributes =  feature.getAttributes();
            Accommodation accommodation = createAccommodationAndAddLocationParams(attributes);
            accommodations.add(accommodation);
        }
        accommodationRepository.saveAll(accommodations);
    }

    private Accommodation createAccommodationAndAddLocationParams(Attributes attributes) throws IOException {
        String mapsURL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + attributes.getLat() + "," + attributes.getLon() + "&key=AIzaSyA6hfZIEa1zt-jg0fSIV3Htb_8whuRwK2s";
        String addressJson = fetchDataFromAPI(mapsURL);
        Address address = ConvertAddress.fromJsonString(addressJson);

        String region = "";
        String district = "";
        String street = "";
        String postCode = "";

        var result= address.getResults()[0];

        for (AddressComponent addressComponent : result.getAddressComponents()) {
            String[] addressTypesFromApi = addressComponent.getTypes();
            List<String> addressTypes = new ArrayList<>(Arrays.asList(addressTypesFromApi));
            if (addressTypes.contains("route")) {
                street = addressComponent.getLongName();
            } else if (addressTypes.contains("postal_code")) {
                postCode = addressComponent.getLongName();
            } else if (addressTypes.contains("administrative_area_level_1")) {
                region = addressComponent.getLongName();
            }
        }

        return Accommodation.builder()
                .id(attributes.getFid())
                .name(attributes.getNzovZar())
                .address(attributes.getAdresaZar())
                .phoneNumber(attributes.getTelefonick())
                .email(attributes.getEMail())
                .webPage(attributes.getWebovLo())
                .gender(attributes.getPohlavie())
                .age(attributes.getVek())
                .type(attributes.getTypSlub())
                .lat(attributes.getLat())
                .lon(attributes.getLon())
                .region(region)
                .district(district)
                .street(street)
                .postCode(postCode)
                .build();
    }

    private String fetchDataFromAPI(String targetURL) throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(targetURL);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (var reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return result.toString();
    }
}
