package com.api.pojo;

public class ContactDetails {

    private String primaryPhone;

    private String primaryEmail;



    private PrimaryAddress primaryAddress;

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public PrimaryAddress getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(PrimaryAddress primaryAddress) {
        this.primaryAddress = primaryAddress;
    }
    public ContactDetails(String primaryPhone, String primaryEmail, PrimaryAddress primaryAddress) {
        this.primaryPhone = primaryPhone;
        this.primaryEmail = primaryEmail;
        this.primaryAddress = primaryAddress;
    }
}
