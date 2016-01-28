package com.vitaliy.krasylovets.spyfall.resources;

/**
 * Created by vitaliy on 2016-01-27.
 */
public class SpyProfession extends Profession {

    public SpyProfession(String name) {
        super(name);
        this.isSpy = true;
    }
}
