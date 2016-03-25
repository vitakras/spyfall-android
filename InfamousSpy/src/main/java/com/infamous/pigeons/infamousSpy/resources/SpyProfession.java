package com.infamous.pigeons.infamousSpy.resources;

import java.io.Serializable;

/**
 * Created by vitaliy on 2016-01-27.
 */
public class SpyProfession extends Profession implements Serializable {

    public SpyProfession(String name) {
        super(name);
        this.isSpy = true;
    }
}
