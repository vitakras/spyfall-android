package com.vitaliy.krasylovets.spyfall;

/**
 * This class is responsible for describing different
 * Created by vitaliy on 2016-01-26.
 */
public class Profession {

    private String name;
    private boolean isSpy;

    public Profession(String name) {
        this.name = name;
        this.isSpy = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSpy() {
        return isSpy;
    }
}
