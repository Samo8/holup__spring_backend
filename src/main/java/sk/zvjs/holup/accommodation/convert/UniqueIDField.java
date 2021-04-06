package sk.zvjs.holup.accommodation.convert;

import com.fasterxml.jackson.annotation.*;

@lombok.Data
public class UniqueIDField {
    @lombok.Getter(onMethod_ = {@JsonProperty("name")})
    @lombok.Setter(onMethod_ = {@JsonProperty("name")})
    private String name;
    @lombok.Getter(onMethod_ = {@JsonProperty("isSystemMaintained")})
    @lombok.Setter(onMethod_ = {@JsonProperty("isSystemMaintained")})
    private boolean isSystemMaintained;
}