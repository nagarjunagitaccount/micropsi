package com.api.pojo;

public class Phone
{
    String first_name;
    String last_name;
    String phone;
    String client_id;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }



    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Phone(String first_name, String last_name, String phone, String client_id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.client_id = client_id;
    }


}
