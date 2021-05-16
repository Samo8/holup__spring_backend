package sk.zvjs.holup.address.convert;


import com.fasterxml.jackson.annotation.*;
import sk.zvjs.holup.address.convert.Result;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
    @lombok.Getter(onMethod_ = {@JsonProperty("results")})
    @lombok.Setter(onMethod_ = {@JsonProperty("results")})
    private Result[] results;
    @lombok.Getter(onMethod_ = {@JsonProperty("status")})
    @lombok.Setter(onMethod_ = {@JsonProperty("status")})
    private String status;
}
