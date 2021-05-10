package sk.zvjs.holup.address.convert;

import com.fasterxml.jackson.annotation.*;

@lombok.Data
public class Geometry {
    @lombok.Getter(onMethod_ = {@JsonProperty("bounds")})
    @lombok.Setter(onMethod_ = {@JsonProperty("bounds")})
    private Viewport bounds;
    @lombok.Getter(onMethod_ = {@JsonProperty("location")})
    @lombok.Setter(onMethod_ = {@JsonProperty("location")})
    private Location location;
    @lombok.Getter(onMethod_ = {@JsonProperty("location_type")})
    @lombok.Setter(onMethod_ = {@JsonProperty("location_type")})
    private String locationType;
    @lombok.Getter(onMethod_ = {@JsonProperty("viewport")})
    @lombok.Setter(onMethod_ = {@JsonProperty("viewport")})
    private Viewport viewport;
}
