package com.api.pojo;

import java.util.List;

public class Megacallprofile
{


    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public List<megaaddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<megaaddress> addresses) {
        this.addresses = addresses;
    }

    public Megacallprofile(Identity identity, List<megaaddress> addresses) {
        this.identity = identity;
        this.addresses = addresses;
    }

    Identity identity;
    List<megaaddress> addresses=null;





}
