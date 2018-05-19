package com.example.rami.picturetester;

import java.sql.Blob;

public class Thing {

    private int id;
    private String name;
    private byte [] image;

    public Thing(){

    }

    public Thing(int id, String name, byte [] image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte [] getImage() {
        return image;
    }

    public void setImage(byte [] image) {
        this.image = image;
    }
}
