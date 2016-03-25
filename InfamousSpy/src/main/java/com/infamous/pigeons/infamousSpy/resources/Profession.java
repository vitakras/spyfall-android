package com.infamous.pigeons.infamousSpy.resources;

import java.io.Serializable;

/**
 * This class is responsible for describing different
 * Created by vitaliy on 2016-01-26.
 */
public class Profession implements Serializable{

    private String name;
    protected boolean isSpy;

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
