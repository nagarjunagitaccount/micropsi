package com.api.pojo;

public class ScorePhone extends Phone {
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





    public ScorePhone(String first_name, String last_name, String phone,String model) {
        super(first_name, last_name, phone);
        this.model=model;

    }
}
