# Java OOP Full Practice Guide

## 1. CLASS VÀ OBJECT

### Class là gì?
Class là bản thiết kế, mô tả dữ liệu và hành vi của một đối tượng.

Ví dụ:
```java
public class Animal {
    private String name;
    private int age;
}
```

### Object là gì?
Object là thực thể được tạo ra từ class.

```java
Animal animal = new Dog("Milo", 3, 12.5);
```

### Giải thích từng phần:

```java
Animal animal = new Dog("Milo", 3, 12.5);
```

- `Animal` ở trái: kiểu dữ liệu / reference type
- `animal`: biến tham chiếu (reference variable)
- `new Dog(...)`: tạo object cụ thể trong heap; không thể `new Animal()` vì Animal là abstract
- Object thực tế: vùng nhớ được JVM cấp phát
- `animal` không phải object, nó chỉ trỏ tới object

### Field/Attribute
```java
private String name;
private int age;
```

### Method
```java
public void eat() {
    System.out.println("Eating");
}
```

### Constructor
```java
public Animal() {
    this.name = "Unknown";
}
```

### this
`this` dùng để tham chiếu tới chính đối tượng hiện tại.

```java
public void setName(String name) {
    this.name = name;
}
```

---

## 2. ENCAPSULATION

### Tại sao cần đóng gói?
Vì không ai nên thay đổi dữ liệu trực tiếp nếu không cần kiểm soát.

```java
private String name;
```

### Access modifier

| Modifier | Same Class | Same Package | Subclass | Other Package |
|----------|------------|--------------|----------|---------------|
| private  | Yes        | No           | No       | No            |
| default  | Yes        | Yes          | No       | No            |
| protected| Yes        | Yes          | Yes      | No            |
| public   | Yes        | Yes          | Yes      | Yes           |

### Getter/Setter
```java
public String getName() {
    return name;
}

public void setName(String name) {
    if (name == null || name.isBlank()) {
        throw new IllegalArgumentException("Name cannot be empty");
    }
    this.name = name;
}
```

### Tại sao không để field public?
Nếu để public, code bên ngoài có thể gán giá trị sai, ví dụ `name = null` hoặc `age = -100`.

### Immutable object
```java
public final class Person {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

---

## 3. INHERITANCE

```java
public class Dog extends Animal {
    public Dog(String name, int age, Address address) {
        super(name, age, AnimalType.DOG, address);
    }
}
```

### extends nghĩa là gì?
Class con kế thừa class cha.

### private có được kế thừa không?
Có, nhưng không truy cập trực tiếp được.

### protected
`protected` cho phép class con truy cập.

### super()
Gọi constructor của class cha.

```java
super(name, age, AnimalType.DOG, address);
```

### this vs super
- `this`: đối tượng hiện tại
- `super`: đối tượng cha

---

## 4. METHOD OVERRIDING

```java
@Override
public void makeSound() {
    System.out.println("Woof!");
}
```

### Điều kiện override
- cùng tên
- cùng tham số
- cùng kiểu trả về
- access modifier không hẹp hơn cha

### @Override
Cho trình biên dịch biết mình đang override.

### Có override static không?
Không, static thuộc class, không thể override.

### Có override private không?
Không.

### Có override final không?
Không.

---

## 5. POLYMORPHISM

```java
Animal animal = new Dog("Milo", 3, address);
animal.makeSound();
```

### Compile-time type
Biến `animal` có kiểu `Animal`.

### Runtime type
Object thật chứa trong heap là `Dog`.

### Dynamic Method Dispatch
JVM quyết định gọi method nào ở runtime theo object thực tế.

```java
List<Animal> animals = new ArrayList<>();
animals.add(new Dog("Milo", 3, address));
animals.add(new Cat("Luna", 2, address));

for (Animal a : animals) {
    a.makeSound();
}
```

---

## 6. ABSTRACTION

```java
public abstract class Animal {
    public abstract void makeSound();
}
```

### abstract class
- không thể new trực tiếp
- có thể chứa method abstract và method bình thường

### abstract method
- chỉ khai báo, không có thân
- class con bắt buộc override

---

## 7. INTERFACE

```java
public interface Flyable {
    void fly();
}
```

```java
public class Bird extends Animal implements Flyable {
    @Override
    public void fly() {
        System.out.println("Flying");
    }
}
```

### interface vs abstract class

| Abstract class | Interface |
|----------------|-----------|
| Có constructor | Không có constructor |
| Có field | Field là static final |
| Có method bình thường | method abstract, default, static |
| 1 class extends 1 abstract class | 1 class implements nhiều interface |

---

## 8. METHOD OVERLOADING

```java
public void eat() {
    System.out.println("Eating");
}

public void eat(String food) {
    System.out.println("Eating " + food);
}

public void eat(String food, int amount) {
    System.out.println("Eating " + amount + " of " + food);
}
```

### Overloading khác Overriding
- Overloading: cùng tên, khác tham số
- Overriding: cùng tên, cùng tham số, override logic ở class con

---

## 9. STATIC

```java
public static final String ANIMAL_TYPE = "Animal";
```

### static field
Thuộc class, không thuộc object.

```java
Animal.ANIMAL_TYPE
```

### static method
```java
public static void printAnimalType() {
    System.out.println(ANIMAL_TYPE);
}
```

### static không thể dùng this
`this` chỉ tồn tại trong instance method.

---

## 10. FINAL

### final variable
```java
final int x = 5;
```

### final method
```java
public final void run() {}
```

### final class
```java
public final class AnimalFactory {}
```

### static final
Dùng cho constant.

```java
public static final String TYPE = "ANIMAL";
```

---

## 11. ACCESS MODIFIER CỤ THỂ

### private
```java
private String name;
```

### default/package-private
```java
String breed;
```

### protected
```java
protected void test() {}
```

### public
```java
public String getName() {}
```

---

## 12. COMPOSITION

```java
public class Animal {
    private Address address;
}
```

`Dog` là `Animal` (IS-A)

`Animal` có `Address` (HAS-A)

### Favor composition over inheritance
Khi cần linh hoạt hơn, nên dùng composition.

---

## 13. ASSOCIATION / AGGREGATION / COMPOSITION

### Association
Hai object biết nhau nhưng độc lập.

### Aggregation
Tổng hợp nhưng có thể tồn tại riêng.

### Composition
Phần tử phụ thuộc rất mạnh vào owner.

---

## 14. OBJECT CLASS

Mọi class trong Java đều kế thừa `Object`.

### Các method quan trọng
- `toString()`
- `equals()`
- `hashCode()`
- `getClass()`

---

## 15. equals() VÀ hashCode()

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Dog other)) return false;
    return this.getName().equals(other.getName());
}
```

### `==` khác `equals()`
- `==` so sánh reference
- `equals()` so sánh nội dung

---

## 16. UPCASTING / DOWNCASTING

```java
Animal animal = new Dog("Milo", 3, address);
Dog dog = (Dog) animal;
```

### Upcasting
`Dog` -> `Animal`

### Downcasting
`Animal` -> `Dog`

### `instanceof`
```java
if (animal instanceof Dog) {
    Dog dog = (Dog) animal;
}
```

---

## 17. CONSTRUCTOR

```java
public Animal(String name, int age, AnimalType type, Address address) {
    this.name = name;
    this.age = age;
    this.type = type;
    this.address = address;
}
```

### Constructor overloading
```java
public Animal() {}
public Animal(String name) {}
```

### this()
Gọi constructor khác trong cùng class.

### super()
Gọi constructor của class cha.

---

## 18. JAVA PASS BY VALUE

Java luôn pass-by-value.

```java
public static void change(int x) {
    x = 10;
}
```

### Với object
reference được truyền theo giá trị, không phải object được truyền trực tiếp.

---

## 19. IMMUTABLE CLASS

```java
public final class Person {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

---

## 20. ENUM

```java
public enum AnimalType {
    DOG,
    CAT,
    BIRD,
    OTHER
}
```

### Khi nào dùng enum?
Khi dữ liệu có tập hợp cố định.

---

## 21. RECORD

```java
public record AnimalResponse(Long id, String name) {}
```

### Record dùng cho DTO
Không cần viết getter/setter bằng tay.

---

## 22. LOMBOK

```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
```

### @Data
Nó bao gồm:
- @Getter
- @Setter
- @ToString
- @EqualsAndHashCode

### Rủi ro
Không nên dùng @Data quá rộng cho Entity JPA vì equals/hashCode có thể gây vấn đề.

---

## 23. OOP TRONG SPRING BOOT

### @Entity
```java
@Entity
public class AnimalEntity {
    @Id
    private Long id;
}
```

### @Service
```java
@Service
public class AnimalService {}
```

### @Repository
```java
@Repository
public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {}
```

### @RestController
```java
@RestController
public class AnimalController {}
```

---

## 24. TỔNG KẾT

| Khái niệm | Ý nghĩa | Ví dụ |
|----------|---------|-------|
| Class | Bản thiết kế | Animal |
| Object | Thực thể được tạo ra | new Animal() |
| Encapsulation | Đóng gói dữ liệu | private field |
| Inheritance | Kế thừa | Dog extends Animal |
| Polymorphism | Một kiểu thể hiện nhiều hình dạng | Animal a = new Dog() |
| Abstraction | Ẩn chi tiết, chỉ hiển thị bản chất | abstract class |
| Interface | Kế hoạch hành vi | Flyable |
| Overloading | Cùng tên khác tham số | eat() / eat(String) |
| Overriding | Ghi đè logic cha | Dog.makeSound() |
| Static | Thuộc class | Animal.ANIMAL_TYPE |
| Final | Không thay đổi được | final class |
| Composition | HAS-A | Animal has Address |

---

## 25. MIND MAP OOP JAVA

```text
OOP Java
├── Class / Object
├── Encapsulation
│   ├── private
│   ├── public
│   ├── protected
│   └── default
├── Inheritance
│   ├── extends
│   ├── super()
│   └── this
├── Polymorphism
│   ├── upcasting
│   ├── downcasting
│   └── overriding
├── Abstraction
│   ├── abstract class
│   └── abstract method
├── Interface
│   ├── implements
│   └── default method
├── Overloading
├── Static / Final
├── Composition
├── Object class
├── equals / hashCode
├── Constructor
├── Pass by value
├── Immutable class
├── Enum / Record
├── Lombok
└── Spring Boot integration
```

---

## 26. 10 QUY TẮC CẦN NHỚ NHẤT

1. Class là bản thiết kế, Object là thực thể.
2. Dữ liệu nên private và expose qua getter/setter.
3. Inheritance dùng cho IS-A, composition dùng cho HAS-A.
4. Abstract class dùng cho chung, interface dùng cho hành vi.
5. Override là ghi đè logic cha.
6. Overloading là cùng tên khác tham số.
7. `static` thuộc class, `final` không đổi.
8. `Animal animal = new Dog();` là upcasting và runtime polymorphism.
9. `equals()` dùng để so sánh nội dung, `==` so sánh reference.
10. Interface và abstraction giúp code linh hoạt, dễ mở rộng.

---

## 27. 20 CÂU HỎI PHỎNG VẤN OOP JAVA

### 1. Class và Object khác nhau như thế nào?
### 2. Tại sao field nên private?
### 3. Java có pass-by-reference không?
### 4. `this` và `super` khác nhau thế nào?
### 5. Override và Overload khác gì?
### 6. abstract class và interface khác gì?
### 7. `Animal animal = new Dog();` nghĩa là gì?
### 8. Dynamic method dispatch là gì?
### 9. `static` khác `final` như thế nào?
### 10. `private` có kế thừa được không?
### 11. Giải thích `instanceof`.
### 12. `equals()` và `hashCode()` phải override cùng lúc không?
### 13. Constructor được kế thừa không?
### 14. Vì sao cần `@Override`?
### 15. `final class` và `final method` dùng khi nào?
### 16. Composition tốt hơn inheritance khi nào?
### 17. `enum` dùng khi nào?
### 18. Record là gì?
### 19. `@Data` có nên dùng cho Entity không?
### 20. Khi nào nên dùng interface thay vì class?

---

## 28. BÀI TẬP THỰC HÀNH

1. Tạo class `Employee` và `Manager` kế thừa.
2. Tạo `Vehicle` và `Car`, `Bike` với `move()` override.
3. Viết class `BankAccount` có `deposit()`, `withdraw()`.
4. Viết `Circle`, `Rectangle`, `Shape` với `calculateArea()`.
5. Viết `AnimalFactory` sử dụng static method.
6. Viết `ImmutableUser`.
7. Tạo `enum DayOfWeek`.
8. Viết `equals()` và `hashCode()` cho class `Person`.
9. Tạo `Flyable` interface cho `Bird`.
10. Tạo project Spring Boot với `Entity` + `Service` + `Controller`.

---

## 29. KẾT LUẬN

Sau khi học bài này, bạn phải hiểu:

- Class là bản thiết kế.
- Object là hiện thực của class.
- Encapsulation giúp kiểm soát dữ liệu.
- Inheritance giúp tái sử dụng code.
- Polymorphism giúp code linh hoạt hơn.
- Abstraction giúp khái quát.
- Interface giúp định nghĩa hành vi.
- Overloading/Overriding là hai khái niệm khác nhau.
- static/final là kỹ thuật rất quan trọng.
- Composition và aggregation là dùng trong thiết kế thực tế.

Một project Java tốt không chỉ viết code chạy được, mà còn code theo đúng nguyên tắc OOP.
