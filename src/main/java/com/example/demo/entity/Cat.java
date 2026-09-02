package com.example.demo.entity;

/**
 * Extends dùng để kế thừa class cha, class con sẽ có tất cả các thuộc tính và
 * phương thức của class cha
 */
public class Cat extends Animal {

    public Cat() {
        super();
    }

    public Cat(String name, int age, double weight) {
        super(name, age, weight);
    }

    public Cat(String name, int age) {
        super(name, age);
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
