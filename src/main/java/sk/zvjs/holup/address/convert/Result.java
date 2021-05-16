package sk.zvjs.holup.address.convert;

import com.fasterxml.jackson.annotation.*;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    @lombok.Getter(onMethod_ = {@JsonProperty("address_components")})
    @lombok.Setter(onMethod_ = {@JsonProperty("address_components")})
    private AddressComponent[] addressComponents;
}
