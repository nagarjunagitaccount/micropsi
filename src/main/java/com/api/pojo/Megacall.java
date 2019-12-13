package com.api.pojo;

import java.util.List;

public class Megacall {


    List<Megacallprofile> profiles=null;

    public List<Megacallprofile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Megacallprofile> profiles) {
        this.profiles = profiles;
    }
    public Megacall(List<Megacallprofile> profiles) {
        this.profiles = profiles;
    }


}
