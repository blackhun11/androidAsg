package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

public class Image {

    private int id;
    private String name;

    public Image(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public int getId () {
        return id;
    }

    public void setId ( int id ) {
        this.id = id;
    }

    @Override
    public String toString () {
        return name;
    }

}

