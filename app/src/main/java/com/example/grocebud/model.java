package com.example.grocebud;

public class model {
    String name,asile;

    model(){

    }

    public model(String name, String asile) {
        this.name = name;
        this.asile = asile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAsile() {
        return asile;
    }

    public void setAsile(String asile) {
        this.asile = asile;
    }
}
