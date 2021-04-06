package sk.zvjs.holup.address;

import com.fasterxml.jackson.annotation.*;

@lombok.Data
public class Result {
    @lombok.Getter(onMethod_ = {@JsonProperty("address_components")})
    @lombok.Setter(onMethod_ = {@JsonProperty("address_components")})
    private AddressComponent[] addressComponents;
    @lombok.Getter(onMethod_ = {@JsonProperty("formatted_address")})
    @lombok.Setter(onMethod_ = {@JsonProperty("formatted_address")})
    private String formattedAddress;
    @lombok.Getter(onMethod_ = {@JsonProperty("geometry")})
    @lombok.Setter(onMethod_ = {@JsonProperty("geometry")})
    private Geometry geometry;
    @lombok.Getter(onMethod_ = {@JsonProperty("place_id")})
    @lombok.Setter(onMethod_ = {@JsonProperty("place_id")})
    private String placeID;
    @lombok.Getter(onMethod_ = {@JsonProperty("types")})
    @lombok.Setter(onMethod_ = {@JsonProperty("types")})
    private String[] types;
    @lombok.Getter(onMethod_ = {@JsonProperty("plus_code")})
    @lombok.Setter(onMethod_ = {@JsonProperty("plus_code")})
    private PlusCode plusCode;
}
