package com.infamous.pigeons.infamousSpy.resources;

import java.io.Serializable;

/**
 * Created by vitaliy on 2016-01-27.
 */
public class Player implements Serializable{

    private String name;
    private Profession profession;

    public Player(String name){
        this.name = name;
        this.profession = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public void resetProfession() {
        this.profession = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Player: " + this.name + ",");
        sb.append("Role: " + this.profession.getName() + ",");
        sb.append("IsSpy: " + (this.profession.isSpy() ? "yes" : "no"));

        return sb.toString();
    }
}
