package com.api.pojo;

public class ScoreAddress extends Address {

    String model;

    public clientsuppliedid getClientSupplied() {
        return clientSupplied;
    }

    public void setClientSupplied(clientsuppliedid clientSupplied) {
        this.clientSupplied = clientSupplied;
    }

    clientsuppliedid clientSupplied;





    public String getModel() {
        return model;

    }

    public void setModel(String model) {
        this.model = model;
    }


    public ScoreAddress(String first_name, String last_name, String address_line1, String address_line2, String city, String state, String zip,String model) {
        super(first_name, last_name, address_line1, address_line2, city, state, zip);
        this.model=model;

    }






}
