package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.example.demo.entity.Animal;
import com.example.demo.entity.AnimalFactory;
import com.example.demo.entity.Cat;
import com.example.demo.entity.Dog;

/** Kiểm chứng ngắn các ví dụ OOP trong hierarchy Animal. */
class AnimalOopTest {

    @Test
    void runtimePolymorphismUsesConcreteAnimalBehavior() {
        Animal dog = new Dog("Lucky", 3, 12.5);

        assertEquals("woof", dog.sound());
        assertEquals("runs", dog.move());
        assertEquals("Dog", dog.getClass().getSimpleName());
    }

    @Test
    void constructorOverloadingSupportsDifferentArguments() {
        Cat catWithoutWeight = new Cat("Mimi", 2);
        Cat catWithWeight = new Cat("Mimi", 2, 4.2);

        assertEquals(0, catWithoutWeight.getWeight());
        assertEquals(4.2, catWithWeight.getWeight());
    }

    @Test
    void encapsulationValidatesStateThroughSetter() {
        Dog dog = new Dog("Lucky", 3, 12.5);

        assertThrows(IllegalArgumentException.class, () -> dog.setAge(-1));
        assertThrows(IllegalArgumentException.class, () -> dog.setName("  "));
    }

    @Test
    void factoryCreatesTheRequestedConcreteType() {
        assertEquals("Cat", AnimalFactory.create(" cat ", "Mimi", 2, 4.2)
                .getClass().getSimpleName());
        assertThrows(IllegalArgumentException.class,
                () -> AnimalFactory.create("dragon", "Draco", 1, 10));
    }
}
