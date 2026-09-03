package com.example.demo.entity;

import jakarta.persistence.Entity;

/** Cat là một Animal, thể hiện inheritance và runtime polymorphism. */
@Entity
public class Cat extends Animal {

    protected Cat() {
        super();
    }

    public Cat(String name, int age) {
        super(name, age);
    }

    public Cat(String name, int age, double weight) {
        super(name, age, weight);
    }

    @Override
    public String sound() {
        return "meow";
    }

    @Override
    public String move() {
        return "jumps";
    }
}
