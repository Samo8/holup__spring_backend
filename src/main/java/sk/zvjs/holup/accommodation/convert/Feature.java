package sk.zvjs.holup.accommodation.convert;

import com.fasterxml.jackson.annotation.*;

@lombok.Data
public class Feature {
    @lombok.Getter(onMethod_ = {@JsonProperty("attributes")})
    @lombok.Setter(onMethod_ = {@JsonProperty("attributes")})
    private Attributes attributes;
    @lombok.Getter(onMethod_ = {@JsonProperty("geometry")})
    @lombok.Setter(onMethod_ = {@JsonProperty("geometry")})
    private Geometry geometry;
}
