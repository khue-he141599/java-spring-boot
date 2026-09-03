package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

/**
 * Lớp cha trừu tượng cho mọi động vật.
 *
 * <p>
 * Thứ tự thường dùng trong một class: field, constructor, getter/setter,
 * hành vi public, method hỗ trợ và các method override.
 * </p>
 *
 * <p>
 * {@code @Inheritance} khai báo rõ với JPA rằng các class con cùng thuộc
 * hierarchy của bảng {@code animals}.
 * </p>
 */
@Entity
@Table(name = "animals")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Animal {

    /** Hằng số thuộc về class, không thuộc riêng một object. */
    public static final String ANIMAL_TYPE = "Animal";

    /** Field private giúp bảo vệ dữ liệu theo nguyên tắc đóng gói. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private double weight;

    /**
     * JPA cần constructor này; protected nên code bên ngoài không tùy ý gọi, chỉ
     * các class con thì mới có thể sử dụng.
     */
    protected Animal() {
    }

    /** Constructor overload: khởi tạo object khi chưa biết cân nặng. */
    protected Animal(String name, int age) {
        setName(name);
        setAge(age);
    }

    /** Constructor overload: khác constructor trên bởi số lượng tham số. */
    protected Animal(String name, int age, double weight) {
        this(name, age);
        setWeight(weight);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            /* Đây là built-in exception */
            throw new IllegalArgumentException("Animal name must not be blank");
        }
        this.name = name.trim();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            /* Đây là built-in exception */
            throw new IllegalArgumentException("Animal age must not be negative");
        }
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Animal weight must not be negative");
        }
        this.weight = weight;
    }

    /** Hành vi chung có thể dùng lại bởi mọi class con. */
    public void eat() {
        System.out.println(name + " is eating");
    }

    /** Method overloading: cùng tên, khác danh sách tham số. */
    public void eat(String food) {
        System.out.println(name + " is eating " + food);
    }

    /** Method overloading lần thứ hai: thêm số lượng thức ăn. */
    public void eat(String food, int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Food amount must not be negative");
        }
        System.out.println(name + " is eating " + amount + " portions of " + food);
    }

    public void sleep() {
        System.out.println(name + " is sleeping");
    }

    /** Abstraction: class con bắt buộc cung cấp cách phát ra âm thanh. */
    public abstract String sound();

    /** Abstraction: class con bắt buộc cung cấp cách di chuyển. */
    public abstract String move();

    /** Polymorphism: sound() và move() được dispatch theo object thật runtime. */
    public String describe() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", sound='" + sound() + '\'' +
                ", move='" + move() + '\'' +
                '}';
    }

    /** Overriding: ghi đè toString() của Object. */
    @Override
    public String toString() {
        return describe();
    }
}
