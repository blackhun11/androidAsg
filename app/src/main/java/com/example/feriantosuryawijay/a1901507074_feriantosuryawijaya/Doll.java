package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

/* Doll Model */
public class Doll {
    private int id;
    private int image;
    private String name;
    private String description;


    /* Doll constructor */
    Doll ( int id , int image , String name , String description ) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
    }

    /* Doll setter getter */
    public int getId () {
        return id;
    }

    public void setId ( int id ) {
        this.id = id;
    }

    public int getImage () {
        return image;
    }

    public void setImage ( int image ) {
        this.image = image;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription ( String description ) {
        this.description = description;
    }


}
