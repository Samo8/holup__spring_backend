package sk.zvjs.holup.address;

import com.fasterxml.jackson.annotation.*;

@lombok.Data
public class Location {
    @lombok.Getter(onMethod_ = {@JsonProperty("lat")})
    @lombok.Setter(onMethod_ = {@JsonProperty("lat")})
    private double lat;
    @lombok.Getter(onMethod_ = {@JsonProperty("lng")})
    @lombok.Setter(onMethod_ = {@JsonProperty("lng")})
    private double lng;
}
