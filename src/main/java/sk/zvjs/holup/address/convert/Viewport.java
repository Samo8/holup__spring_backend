package sk.zvjs.holup.address.convert;

import com.fasterxml.jackson.annotation.*;

@lombok.Data
public class Viewport {
    @lombok.Getter(onMethod_ = {@JsonProperty("northeast")})
    @lombok.Setter(onMethod_ = {@JsonProperty("northeast")})
    private Location northeast;
    @lombok.Getter(onMethod_ = {@JsonProperty("southwest")})
    @lombok.Setter(onMethod_ = {@JsonProperty("southwest")})
    private Location southwest;
}
