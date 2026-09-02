package com.example.demo.entity;

public class Dog extends Animal {

    public Dog(String name, int age, double weight) {
        super(name, age, weight);
    }

    @Override
    public String sound() {
        return "woof";
    }

    @Override
    public String move() {
        return "runs";
    }
}
