package com.api.pojo;

public class ellucian {

    private PersonName personName;



    private ContactDetails contactDetails;

    private String id;

    public PersonName getPersonName() {
        return personName;
    }

    public void setPersonName(PersonName personName) {
        this.personName = personName;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public ellucian(PersonName personName, ContactDetails contactDetails, String id) {
        this.personName = personName;
        this.contactDetails = contactDetails;
        this.id = id;
    }
}
