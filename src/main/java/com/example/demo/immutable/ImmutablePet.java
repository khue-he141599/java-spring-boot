package com.example.demo.immutable;

/**
 * Simple immutable class example: all fields are final and there are no
 * setters.
 */
public final class ImmutablePet {

   private final String name;
   private final int age;

   private ImmutablePet(String name, int age) {
      this.name = name;
      this.age = age;
   }

   public static ImmutablePet of(String name, int age) {
      return new ImmutablePet(name, age);
   }

   public String getName() {
      return name;
   }

   public int getAge() {
      return age;
   }
}
