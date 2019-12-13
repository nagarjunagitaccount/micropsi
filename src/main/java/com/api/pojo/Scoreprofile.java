package com.api.pojo;

import java.util.ArrayList;
import java.util.List;

public class Scoreprofile extends Summary
{

    private List<model> models = new ArrayList<>();
    public Scoreprofile(String first_name, String last_name, String address_line1, String address_line2, String city, String state, String zip, String email, String phone, List<model> models) {
        super(first_name, last_name, address_line1, address_line2, city, state, zip, email, phone);
        this.models = models;
    }

    public List<model> getModels() {
        return models;
    }

    public void setModels(List<model> models) {
        this.models = models;
    }



}
