package com.api.pojo;

import java.util.List;

public class Scoreprofiles
{
    List<Scoreprofile> profiles=null;
    public Scoreprofiles(List<Scoreprofile> profiles) {
        this.profiles = profiles;
    }


    public List<Scoreprofile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Scoreprofile> profiles) {
        this.profiles = profiles;
    }


}
