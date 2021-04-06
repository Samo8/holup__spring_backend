package sk.zvjs.holup.accommodation.convert;

import com.fasterxml.jackson.annotation.*;

@lombok.Data
public class SpatialReference {
    @lombok.Getter(onMethod_ = {@JsonProperty("wkid")})
    @lombok.Setter(onMethod_ = {@JsonProperty("wkid")})
    private long wkid;
    @lombok.Getter(onMethod_ = {@JsonProperty("latestWkid")})
    @lombok.Setter(onMethod_ = {@JsonProperty("latestWkid")})
    private long latestWkid;
}