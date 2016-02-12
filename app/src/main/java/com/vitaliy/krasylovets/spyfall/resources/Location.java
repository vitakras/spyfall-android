package com.vitaliy.krasylovets.spyfall.resources;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vitaliy on 2016-01-27.
 */
public class Location implements Serializable{

    private String name;
    private List<Profession> professionList;

    public Location(String name, List<Profession> professionList){
        this.name = name;
        this.professionList = professionList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Profession> getProfessionList() {
        return professionList;
    }
}
