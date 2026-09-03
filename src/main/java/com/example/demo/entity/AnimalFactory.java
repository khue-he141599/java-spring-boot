package com.example.demo.entity;

/** Factory tập trung logic tạo object, thay vì để controller biết từng constructor. */
public final class AnimalFactory {

   private AnimalFactory() {
      // private constructor để ngăn khởi tạo đối tượng
   }

   public static Animal createDog(String name, int age, double weight) {
      return new Dog(name, age, weight);
   }

   public static Animal createCat(String name, int age, double weight) {
      return new Cat(name, age, weight);
   }

   public static Animal createBird(String name, int age, double weight) {
      return new Bird(name, age, weight);
   }

   /** Runtime polymorphism: kiểu trả về là Animal, object thật là class con. */
   public static Animal create(String type, String name, int age, double weight) {
      if (type == null || type.isBlank()) {
         throw new IllegalArgumentException("Animal type must not be blank");
      }
      switch (type.trim().toLowerCase()) {
         case "dog":
            return createDog(name, age, weight);
         case "cat":
            return createCat(name, age, weight);
         case "bird":
            return createBird(name, age, weight);
         default:
            throw new IllegalArgumentException("Unsupported animal type: " + type);
      }
   }
}
