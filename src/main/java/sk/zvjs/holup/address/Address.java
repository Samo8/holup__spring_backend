package sk.zvjs.holup.address;


import com.fasterxml.jackson.annotation.*;
import sk.zvjs.holup.address.convert.PlusCode;
import sk.zvjs.holup.address.convert.Result;

@lombok.Data
public class Address {
    @lombok.Getter(onMethod_ = {@JsonProperty("plus_code")})
    @lombok.Setter(onMethod_ = {@JsonProperty("plus_code")})
    private PlusCode plusCode;
    @lombok.Getter(onMethod_ = {@JsonProperty("results")})
    @lombok.Setter(onMethod_ = {@JsonProperty("results")})
    private Result[] results;
    @lombok.Getter(onMethod_ = {@JsonProperty("status")})
    @lombok.Setter(onMethod_ = {@JsonProperty("status")})
    private String status;
}
