package com.example.demo.entity;

public class Bird extends Animal {

    public Bird() {
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
