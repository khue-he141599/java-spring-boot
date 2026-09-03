package com.example.demo.entity;

import jakarta.persistence.Entity;

/** Bird là class con cụ thể của Animal. */
@Entity
public class Bird extends Animal {

    protected Bird() {
        super();
    }

    public Bird(String name, int age, double weight) {
        super(name, age, weight);
    }

    @Override
    public String sound() {
        return "chirp";
    }

    @Override
    public String move() {
        return "flies";
    }
}
