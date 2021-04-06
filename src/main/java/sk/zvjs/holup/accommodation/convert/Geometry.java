package sk.zvjs.holup.accommodation.convert;

import com.fasterxml.jackson.annotation.*;

@lombok.Data
public class Geometry {
    @lombok.Getter(onMethod_ = {@JsonProperty("x")})
    @lombok.Setter(onMethod_ = {@JsonProperty("x")})
    private double x;
    @lombok.Getter(onMethod_ = {@JsonProperty("y")})
    @lombok.Setter(onMethod_ = {@JsonProperty("y")})
    private double y;
}
