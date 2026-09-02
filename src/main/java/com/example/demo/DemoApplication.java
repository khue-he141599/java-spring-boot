package com.example.demo;

import com.example.demo.entity.Animal;
import com.example.demo.entity.AnimalFactory;
import com.example.demo.immutable.ImmutablePet;
import com.example.demo.mutable.MutablePet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static final String APP_NAME = "OOP Demo";

	public static void main(String[] args) {
		// Run simple OOP demo logic before starting Spring context
		Animal dog = AnimalFactory.createDog("Lucky", 3, 12.5);
		Animal cat = AnimalFactory.createCat("Mimi", 2, 4.5);
		Animal bird = AnimalFactory.createBird("Sky", 1, 0.8);

		System.out.println(dog.describe());
		System.out.println(cat.describe());
		System.out.println(bird.describe());

		printAnimalSound(dog);
		printAnimalSound(cat);
		printAnimalSound(bird);

		// Demonstrate mutable vs immutable
		ImmutablePet immutable = ImmutablePet.of("Bunny", 2);
		MutablePet mutable = new MutablePet("Bobby", 3);

		System.out.println("Before mutation: " + immutable.getName() + ", " + mutable);

		// mutate the mutable pet
		mutable.setName("Bobby Jr.");

		System.out.println("After mutation: " + immutable.getName() + ", " + mutable);

		System.out.println("Static final constant: " + APP_NAME);

		SpringApplication.run(DemoApplication.class, args);
	}

	public static void printAnimalSound(Animal animal) {
		System.out.println(animal.getClass().getSimpleName() + " says: " + animal.sound());
	}

}
