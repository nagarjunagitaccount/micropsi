package com.api.pojo;

public class PrimaryAddress {

    private String postalCode;
    private String city;

    private String streetLine1;

    private String region;

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetLine1() {
        return streetLine1;
    }

    public void setStreetLine1(String streetLine1) {
        this.streetLine1 = streetLine1;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    public PrimaryAddress(String postalCode, String city, String streetLine1, String region) {
        this.postalCode = postalCode;
        this.city = city;
        this.streetLine1 = streetLine1;
        this.region = region;
    }
}
