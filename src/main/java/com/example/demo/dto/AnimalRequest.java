package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Request DTO for creating a new animal. Shows Bean Validation annotations.
 */
public class AnimalRequest {

   @NotBlank(message = "type is required and cannot be blank")
   @Size(max = 20)
   private String type;

   @NotBlank(message = "name is required")
   @Size(max = 50)
   private String name;

   @NotNull
   @Min(0)
   private Integer age;

   @NotNull
   @Min(0)
   private Double weight;

   public AnimalRequest() {
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getAge() {
      return age;
   }

   public void setAge(Integer age) {
      this.age = age;
   }

   public Double getWeight() {
      return weight;
   }

   public void setWeight(Double weight) {
      this.weight = weight;
   }
}
