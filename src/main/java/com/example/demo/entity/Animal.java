package com.example.demo.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "Animal")
@NoArgsConstructor
@AllArgsConstructor
/*
 * abstract tính trừu tượng: thường được sử dụng ở class cha, không thể khởi tạo
 * đối tượng trực tiếp từ class này, chỉ có thể khởi tạo từ các class con kế
 * thừa nó
 */
public abstract class Animal {

    /**
     * encapsulation tính đóng gói: các thuộc tính của class được khai báo là
     * private,
     * chỉ có thể truy cập thông qua các phương thức getter và setter
     *
     * Ghi nhớ nhanh:
     * - private: chỉ class đó biết
     * - default/package-private: chỉ cùng package biết
     * - protected: cùng package + class con biết
     * - public: ai cũng biết
     */
    private String name;
    private int age;
    private double weight;
    private String sound;

    /**
     * static final: thuộc về class, không thay đổi được.
     * Dùng cho hằng số chung của tất cả đối tượng.
     */
    public static final String ANIMAL_TYPE = "Animal";

    /**
     * public: có thể truy cập từ bất kỳ class nào
     * => nên dùng khi muốn mở rộng phạm vi truy cập cho tất cả.
     */
    public String another;

    /**
     * default: chỉ có thể truy cập(gắn trực tiếp gía trị vào cho thuộc tính) từ các
     * class trong cùng package
     * => dùng khi muốn giới hạn access trong cùng package.
     */
    String defaultPackagePrivate;

    /**
     * protected constructor: chỉ có thể truy cập từ các class con kế thừa, không
     * thể truy cập từ các class khác
     *
     * Ví dụ: Cat extends Animal thì Cat có thể gọi constructor này.
     */
    protected Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Tính đa hình: phương thức có thể có nhiều dạng khác nhau, nhưng phải cùng
     * tên,
     * khác nhau về số lượng hoặc kiểu dữ liệu của tham số
     * constructor: phương thức khởi tạo đối tượng, có thể có nhiều constructor
     * khác nhau, nhưng phải khác nhau về số lượng hoặc kiểu dữ liệu của tham số
     *
     * Overloading: cùng tên nhưng tham số khác nhau.
     * Ví dụ: Animal(String, int, double) và Animal(String, int, String)
     */
    public Animal(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public Animal(String name, int age, String sound) {
        this.name = name;
        this.age = age;
        this.sound = sound;
    }

    /**
     * method: phương thức của class, có thể truy cập từ các class khác
     *
     * Đây là hành vi chung của mọi Animal.
     * Mỗi class con có thể override nếu cần.
     */
    public void eat(String food) {
        System.out.println(name + " is eating " + food);
    }

    public void sleep() {
        System.out.println(name + " is sleeping");
    }

    /**
     * abstract method: phương thức trừu tượng, không có phần thân, chỉ có khai báo,
     * các class con kế thừa phải implement phương thức này
     *
     * Abstraction: class cha định nghĩa hành vi chung,
     * class con quyết định cách triển khai cụ thể.
     */
    public abstract String sound();

    public abstract String move();

    /**
     * Polymorphism: cùng một method describe() nhưng output khác nhau tùy từng class con.
     * Vì getClass().getSimpleName() trả về Dog, Cat, Bird ...
     */
    public String describe() {
        return getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", sound='" + sound() + '\'' +
                ", move='" + move() + '\'' +
                '}';
    }

    /**
     * Tính đa hình: Override ghi đè lại phương thức của class cha, có thể thay đổi
     * phần thân của phương thức
     * 
     * override phương thức toString() của class Object, để in ra thông tin của đối
     * tượng
     *
     * Ghi nhớ:
     * - Overriding: cùng tên, cùng tham số, khác phần thân trong class con.
     * - Overloading: cùng tên, khác tham số.
     */
    @Override
    public String toString() {
        return describe();
    }
}
