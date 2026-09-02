package com.example.demo.entity;

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

   public static Animal create(String type, String name, int age, double weight) {
      if (type == null) {
         return createDog(name, age, weight);
      }
      switch (type.trim().toLowerCase()) {
         case "dog":
            return createDog(name, age, weight);
         case "cat":
            return createCat(name, age, weight);
         case "bird":
            return createBird(name, age, weight);
         default:
            return createDog(name, age, weight);
      }
   }
}
