package com.api.pojo;

import java.util.List;

public class Profiles {

    private List<Summary> profiles;

    public List<Summary> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Summary> profiles) {
        this.profiles = profiles;
    }
    public Profiles(List<Summary> profiles) {
        this.profiles = profiles;
    }

}
