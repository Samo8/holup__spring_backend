package sk.zvjs.holup.accommodation.convert;

import com.fasterxml.jackson.annotation.*;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccommodationData {
    @lombok.Getter(onMethod_ = {@JsonProperty("features")})
    @lombok.Setter(onMethod_ = {@JsonProperty("features")})
    private Feature[] features;
}