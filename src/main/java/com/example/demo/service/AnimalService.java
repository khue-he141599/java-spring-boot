package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Animal;
import com.example.demo.entity.Cat;
import com.example.demo.entity.Dog;

import java.util.List;

@Service
public class AnimalService {

    public List<Animal> findAll() {
        return List.of(
                new Dog("Lucky", 3, 12.5),
                new Cat("Mimi", 2, 4.2)
        );
    }
}
