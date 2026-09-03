package com.example.demo.entity;


public final class AddressFactory {
   
   protected AddressFactory() {
      // Private constructor to prevent instantiation
   }

   public static Address createAddressFull(String city, String street, String zipCode) {
      return new Address(city, street, zipCode);
   }

   public static Address createAddress(String city, String street) {
      return new Address(city, street);
   }
}
