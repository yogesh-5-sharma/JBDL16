package com.example.filehandling;

import java.io.Serializable;

public class Pet implements Serializable {
    String name;
    String color;

    public Pet(String name, String color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
