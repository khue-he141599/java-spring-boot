package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Animal;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory repository để ví dụ tập trung vào OOP và collection.
 */
@Repository
public class AnimalRepository {

   private final Map<Long, Animal> storage = new LinkedHashMap<>();
   private final AtomicLong idGen = new AtomicLong(1);
   private final Set<Animal> animals = new HashSet<>();

   public List<Animal> findAll() {
      return new ArrayList<>(storage.values());
   }

   /**
    * Tìm object trong Set. Set dùng equals/hashCode của object để xác định trùng.
    */
   public Optional<Animal> findInSet(Animal animal) {
      return animals.contains(animal) ? Optional.of(animal) : Optional.empty();
   }

   public Optional<Animal> findById(Long id) {
      return Optional.ofNullable(storage.get(id));
   }

   public Long save(Animal animal) {
      Long id = idGen.getAndIncrement();
      storage.put(id, animal);
      animals.add(animal);
      return id;
   }

   public void delete(Long id) {
      storage.remove(id);
   }
}
