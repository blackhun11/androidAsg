package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

/* Image Model */
public class Image {

    private int id;
    private String name;

    /* Image constructor */
    Image ( int id , String name ) {
        this.id = id;
        this.name = name;
    }

    /* Image setter getter */
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

