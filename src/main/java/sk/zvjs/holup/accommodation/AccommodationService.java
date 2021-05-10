package sk.zvjs.holup.accommodation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sk.zvjs.holup.accommodation.convert.Attributes;
import sk.zvjs.holup.accommodation.convert.Converter;
import sk.zvjs.holup.address.convert.ConvertAddress;

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
    @Autowired
    private AccommodationRepository accommodationRepository;
    @Value("${google.maps.key}")
    private String googleMapsKey;

    public List<Accommodation> fetchAccommodations(AccommodationDTO accommodationDTO) {
        System.out.println(accommodationDTO.getTypes());

        var accommodations = accommodationRepository.findAccommodationsByAllParams(
                accommodationDTO.getTypes(),
                accommodationDTO.getGender(),
                accommodationDTO.getRegions(),
                accommodationDTO.getDistricts(),
                accommodationDTO.getAges()
        );

        var inputDistance = accommodationDTO.getDistance();
        var usersLocation = accommodationDTO.getLocation();
        if (inputDistance != null && usersLocation != null) {
            List<Accommodation> accommodationsByLocation = new ArrayList<>();
            for (var accommodation : accommodations) {
                var location = accommodation.getLocation();
                var accommodationDistance = distance(
                        location.getLat(),
                        usersLocation.getLat(),
                        location.getLon(),
                        usersLocation.getLng()
                );
                if (accommodationDistance <= inputDistance)
                    accommodationsByLocation.add(accommodation);
            }
            return accommodationsByLocation;
        }
        return accommodations;
    }

    public static double distance(double lat1, double lat2, double lon1, double lon2) {
        final int R = 6371;

        var latDistance = Math.toRadians(lat2 - lat1);
        var lonDistance = Math.toRadians(lon2 - lon1);
        var a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        var distance = R * c;

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }

    public void saveAccommodationsToDatabase() throws IOException {
        var accommodationsString = fetchDataFromAPI(ACCOMMODATIONS_URL);
        var welcome = Converter.fromJsonString(accommodationsString);

        List<Accommodation> accommodations = new ArrayList<>();
        for (var feature : welcome.getFeatures()) {
            var attributes =  feature.getAttributes();
            var accommodation = createAccommodationAndAddLocationParams(attributes);
            accommodations.add(accommodation);
        }
        accommodationRepository.saveAll(accommodations);
    }

    private Accommodation createAccommodationAndAddLocationParams(Attributes attributes) throws IOException {
        var mapsURL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + attributes.getLat() + "," + attributes.getLon() + "&key=" + googleMapsKey;
        var addressJson = fetchDataFromAPI(mapsURL);
        var address = ConvertAddress.fromJsonString(addressJson);

        var region = "";
        var district = "";
        var city = "";
        var street = "";
        var postCode = "";

        var result= address.getResults()[0];

        for (var addressComponent : result.getAddressComponents()) {
            var addressTypesFromApi = addressComponent.getTypes();
            List<String> addressTypes = new ArrayList<>(Arrays.asList(addressTypesFromApi));
            if (addressTypes.contains("route")) {
                street = addressComponent.getLongName();
            } else if (addressTypes.contains("postal_code")) {
                postCode = addressComponent.getLongName();
            } else if (addressTypes.contains("sublocality") || addressTypes.contains("locality")) {
                city = addressComponent.getLongName();
            } else if (addressTypes.contains("administrative_area_level_1")) {
                region = addressComponent.getLongName();
            } else if (addressTypes.contains("administrative_area_level_2")) {
                district = addressComponent.getLongName();
            }
        }

        Accommodation accommodation = Accommodation.builder()
                .id(attributes.getFid())
                .name(attributes.getNzovZar())
                .phoneNumber(attributes.getTelefonick())
                .email(attributes.getEMail())
                .webPage(attributes.getWebovLo())
                .gender(attributes.getPohlavie())
                .age(attributes.getVek())
                .type(attributes.getTypSlub())
                .location(new AccommodationLocation(attributes.getLat(), attributes.getLon()))
                .address(new AccommodationAddress(region, district, city, street, postCode))
                .build();
        System.out.println(accommodation.toString());
        return accommodation;
    }

    private String fetchDataFromAPI(String targetURL) throws IOException {
        var result = new StringBuilder();
        var url = new URL(targetURL);

        var conn = (HttpURLConnection) url.openConnection();
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
