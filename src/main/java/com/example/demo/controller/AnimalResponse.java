package com.example.demo.controller;

import com.example.demo.entity.Animal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnimalResponse {

    /*
     * Dùng final khi:
     * 
     * Muốn giá trị không đổi.
     * Muốn bảo vệ tính toàn vẹn của logic.
     * Muốn ngăn override / inheritance.
     */
    private final String type;
    private final String name;
    private final int age;
    private final double weight;
    private final String sound;
    private final String move;

    /**
     * static là một từ khóa trong Java được sử dụng để khai báo các thành phần
     * (biến, phương thức, lớp) thuộc về lớp thay vì thuộc về đối tượng. Khi một
     * thành phần được khai báo là static, nó có thể được truy cập trực tiếp thông
     * qua tên lớp mà không cần tạo một đối tượng của lớp đó. Trong trường hợp này,
     * phương thức from() được khai báo là static, cho phép bạn gọi nó mà không cần
     * tạo một đối tượng AnimalResponse.
     * 
     */

    /**
     * static = “chia sẻ cho cả class”-Dùng cho utility, helper, cache, counter.
     * final = “không thay đổi được”
     */

    public static AnimalResponse from(Animal animal) {
        return new AnimalResponse(
                animal.getClass().getSimpleName(),
                animal.getName(),
                animal.getAge(),
                animal.getWeight(),
                animal.sound(),
                animal.move());
    }
}
