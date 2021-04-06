package sk.zvjs.holup.accommodation.convert;

import com.fasterxml.jackson.annotation.*;

@lombok.Data
public class Field {
    @lombok.Getter(onMethod_ = {@JsonProperty("name")})
    @lombok.Setter(onMethod_ = {@JsonProperty("name")})
    private String name;
    @lombok.Getter(onMethod_ = {@JsonProperty("type")})
    @lombok.Setter(onMethod_ = {@JsonProperty("type")})
    private Type type;
    @lombok.Getter(onMethod_ = {@JsonProperty("alias")})
    @lombok.Setter(onMethod_ = {@JsonProperty("alias")})
    private String alias;
    @lombok.Getter(onMethod_ = {@JsonProperty("sqlType")})
    @lombok.Setter(onMethod_ = {@JsonProperty("sqlType")})
    private SQLType sqlType;
    @lombok.Getter(onMethod_ = {@JsonProperty("domain")})
    @lombok.Setter(onMethod_ = {@JsonProperty("domain")})
    private Object domain;
    @lombok.Getter(onMethod_ = {@JsonProperty("defaultValue")})
    @lombok.Setter(onMethod_ = {@JsonProperty("defaultValue")})
    private Object defaultValue;
    @lombok.Getter(onMethod_ = {@JsonProperty("length")})
    @lombok.Setter(onMethod_ = {@JsonProperty("length")})
    private Long length;
    @lombok.Getter(onMethod_ = {@JsonProperty("description")})
    @lombok.Setter(onMethod_ = {@JsonProperty("description")})
    private String description;
}