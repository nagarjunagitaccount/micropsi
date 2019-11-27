package com.api.pojo;

public class ScoreEmail extends Email {
    String model;
    clientsuppliedid clientSupplied;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public clientsuppliedid getClientSupplied() {
        return clientSupplied;
    }

    public void setClientSupplied(clientsuppliedid clientSupplied) {
        this.clientSupplied = clientSupplied;
    }

    public ScoreEmail(String first_name, String last_name, String email,String model) {
        super(first_name, last_name, email);
        this.model = model;
    }

}
