package com.api.pojo;

public class megaaddress {


    private String street_line1;


    private String city;

        private String state;

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public megaaddress(String street_line1, String city, String state, String postal_code, String country, String street_line2) {
        this.street_line1 = street_line1;
        this.city = city;
        this.state = state;
        this.postal_code = postal_code;
        this.country = country;
        this.street_line2 = street_line2;
    }

    private String postal_code;


    public String getStreet_line1() {
        return street_line1;
    }

    public void setStreet_line1(String street_line1) {
        this.street_line1 = street_line1;
    }

    public String getStreet_line2() {
        return street_line2;
    }

    public void setStreet_line2(String street_line2) {
        this.street_line2 = street_line2;
    }

    private String country;



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private String street_line2;


}
