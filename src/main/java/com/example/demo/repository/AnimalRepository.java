package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Animal;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory repository to keep the example simple and focused on concepts.
 */
@Repository
public class AnimalRepository {

   private final Map<Long, Animal> storage = new LinkedHashMap<>();
   private final AtomicLong idGen = new AtomicLong(1);

   public List<Animal> findAll() {
      return new ArrayList<>(storage.values());
   }

   public Optional<Animal> findById(Long id) {
      return Optional.ofNullable(storage.get(id));
   }

   public Long save(Animal animal) {
      Long id = idGen.getAndIncrement();
      storage.put(id, animal);
      return id;
   }

   public void delete(Long id) {
      storage.remove(id);
   }
}
