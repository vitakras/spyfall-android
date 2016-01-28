package com.vitaliy.krasylovets.spyfall.resources;

/**
 * Created by vitaliy on 2016-01-27.
 */
public class Player {

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
}
