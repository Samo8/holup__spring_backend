package sk.zvjs.holup.accommodation.convert;

import com.fasterxml.jackson.annotation.*;

@lombok.Data
public class AccommodationData {
    @lombok.Getter(onMethod_ = {@JsonProperty("objectIdFieldName")})
    @lombok.Setter(onMethod_ = {@JsonProperty("objectIdFieldName")})
    private String objectIDFieldName;
    @lombok.Getter(onMethod_ = {@JsonProperty("uniqueIdField")})
    @lombok.Setter(onMethod_ = {@JsonProperty("uniqueIdField")})
    private UniqueIDField uniqueIDField;
    @lombok.Getter(onMethod_ = {@JsonProperty("globalIdFieldName")})
    @lombok.Setter(onMethod_ = {@JsonProperty("globalIdFieldName")})
    private String globalIDFieldName;
    @lombok.Getter(onMethod_ = {@JsonProperty("geometryType")})
    @lombok.Setter(onMethod_ = {@JsonProperty("geometryType")})
    private String geometryType;
    @lombok.Getter(onMethod_ = {@JsonProperty("spatialReference")})
    @lombok.Setter(onMethod_ = {@JsonProperty("spatialReference")})
    private SpatialReference spatialReference;
    @lombok.Getter(onMethod_ = {@JsonProperty("fields")})
    @lombok.Setter(onMethod_ = {@JsonProperty("fields")})
    private Field[] fields;
    @lombok.Getter(onMethod_ = {@JsonProperty("features")})
    @lombok.Setter(onMethod_ = {@JsonProperty("features")})
    private Feature[] features;
}