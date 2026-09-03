package com.example.demo.entity;

import jakarta.persistence.Entity;

/** Concrete class: kế thừa dữ liệu/hành vi chung và triển khai hành vi riêng. */
@Entity
public class Dog extends Animal {

    protected Dog() {
        super();
    }

    public Dog(String name, int age, double weight) {
        super(name, age, weight);
    }

    /** Overriding: cùng signature với abstract method của Animal. */
    @Override
    public String sound() {
        return "woof";
    }

    @Override
    public String move() {
        return "runs";
    }
}
