package com.api.pojo;

public class model
{
    clientsuppliedid clientSupplied;
    String name;

    public clientsuppliedid getClientSupplied() {
        return clientSupplied;
    }

    public void setClientSupplied(clientsuppliedid clientSupplied) {
        this.clientSupplied = clientSupplied;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public model(String name) {
        this.name = name;
    }
    public model() {

    }






}
