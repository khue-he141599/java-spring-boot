# Animal Management System — Giải thích theo chủ đề

Project minh họa: một hệ thống quản lý `Animal` (Dog, Cat, Bird) cùng `Owner`, `Service`, `Repository`, `Controller`.

Các tệp chính:
- [src/main/java/com/example/entity/Animal.java](src/main/java/com/example/entity/Animal.java)
- [src/main/java/com/example/entity/Dog.java](src/main/java/com/example/entity/Dog.java)
- [src/main/java/com/example/entity/Cat.java](src/main/java/com/example/entity/Cat.java)
- [src/main/java/com/example/entity/Bird.java](src/main/java/com/example/entity/Bird.java)
- [src/main/java/com/example/entity/AnimalFactory.java](src/main/java/com/example/entity/AnimalFactory.java)
- [src/main/java/com/example/demo/service/AnimalService.java](src/main/java/com/example/demo/service/AnimalService.java)
- [src/main/java/com/example/demo/repository/AnimalRepository.java](src/main/java/com/example/demo/repository/AnimalRepository.java)
- [src/main/java/com/example/demo/controller/AnimalController.java](src/main/java/com/example/demo/controller/AnimalController.java)
- [src/main/java/com/example/demo/controller/AnimalResponse.java](src/main/java/com/example/demo/controller/AnimalResponse.java)
- [src/main/java/com/example/demo/dto/AnimalRequest.java](src/main/java/com/example/demo/dto/AnimalRequest.java)
- [src/main/java/com/example/demo/exception/ResourceNotFoundException.java](src/main/java/com/example/demo/exception/ResourceNotFoundException.java)
- [src/main/java/com/example/demo/immutable/ImmutablePet.java](src/main/java/com/example/demo/immutable/ImmutablePet.java)

---

**Hướng dẫn đọc:** mỗi phần sau có 1) Khái niệm, 2) Code (tham chiếu), 3) Giải thích code, 4) Concepts, 5) Tại sao thiết kế như vậy, 6) Lỗi hay gặp, 7) Khi dùng/không dùng.

## 1. OOP

1. **Khái niệm**
   - Class: khuôn mẫu. Object: thể hiện cụ thể của class.
   - Encapsulation: đóng gói thuộc tính private + getter/setter.
   - Inheritance: kế thừa (`extends`).
   - Polymorphism: cùng kiểu nhưng có hành vi khác (override/overload).

2. **Code**
   - [src/main/java/com/example/entity/Animal.java](src/main/java/com/example/entity/Animal.java)
   - [src/main/java/com/example/entity/Dog.java](src/main/java/com/example/entity/Dog.java)
   - [src/main/java/com/example/entity/Cat.java](src/main/java/com/example/entity/Cat.java)
   - [src/main/java/com/example/entity/Bird.java](src/main/java/com/example/entity/Bird.java)
   - [src/main/java/com/example/entity/AnimalFactory.java](src/main/java/com/example/entity/AnimalFactory.java)

3. **Giải thích code**
   - `Animal` là `abstract class`: không tạo instance trực tiếp, chứa thuộc tính `name, age, weight` (encapsulation: private) và các phương thức chung như `eat()`, `sleep()` và các phương thức `abstract` (`sound()`, `move()`).
   - `Dog`, `Cat`, `Bird` kế thừa `Animal` và override `sound()` + `move()` — đây là phương thức overriding (runtime polymorphism).
   - `AnimalFactory` cung cấp static factory methods (`createDog`, `createCat`, `createBird`, `create(type,...)`) để tạo thể hiện cụ thể dựa trên input (demonstrates polymorphism + Factory pattern).

4. **OOP/Java concept đang được sử dụng**
   - Encapsulation: private fields + generated getters/setters (Lombok `@Data` trên `Animal`).
   - Inheritance: `extends`.
   - Abstraction: `abstract class` + `abstract methods`.
   - Polymorphism: overriding (e.g., `sound()`), overloading (constructors in `Animal`).

5. **Tại sao thiết kế như vậy**
   - Dùng `abstract` cho hành vi chung và để ép các class con triển khai chi tiết.
   - Factory tách logic tạo object khỏi controller/service, giảm coupling.

6. **Lỗi người mới thường gặp**
   - Ghi đè (override) phương thức nhưng đổi signature -> thành overloading, không override.
   - Truy cập trực tiếp vào fields thay vì dùng getter/setter -> phá encapsulation.

7. **Khi nào nên dùng / không nên dùng**
   - Dùng inheritance khi có quan hệ "is-a" rõ ràng; tránh lạm dụng inheritance nếu chỉ cần tái sử dụng code (ưu tiên composition).

---

## 2. Static

1. **Khái niệm**
   - `static` thuộc về class, không thuộc instance.

2. **Code**
   - [src/main/java/com/example/entity/Animal.java](src/main/java/com/example/entity/Animal.java) (ví dụ `public static final String ANIMAL_TYPE`)
   - [src/main/java/com/example/demo/controller/AnimalResponse.java](src/main/java/com/example/demo/controller/AnimalResponse.java) (`public static AnimalResponse from(Animal)`)

3. **Giải thích code**
   - `ANIMAL_TYPE` là `static final` — một hằng số dùng chung cho tất cả instance.
   - `AnimalResponse.from()` là `static factory method` giúp chuyển `Animal` -> `AnimalResponse` mà không cần khởi tạo `AnimalResponse` trước.

4. **OOP/Java concept đang được sử dụng**
   - Static variable: chia sẻ trạng thái/hằng số.
   - Static method: utility/convertor/factory.

5. **Tại sao phải thiết kế như vậy**
   - `static` phù hợp cho: hằng số, helper/utility methods, bộ nhớ cache chia sẻ, counters toàn cục.

6. **Lỗi người mới thường gặp**
   - Lạm dụng `static` để lưu state mutable -> gây race conditions trong đa luồng.
   - Nhầm lẫn giữa `static` và `instance` khi cần trạng thái theo object.

7. **Khi dùng / không dùng**
   - Dùng `static` cho hằng số (`final`) và phương thức thuần (stateless). Không dùng `static` để lưu trạng thái mutable của business domain.

---

## 3. Collection (`List`, `Set`, `Map`)

1. **Khái niệm ngắn**
   - `List`: ordered, cho phép duplicate.
   - `Set`: không duplicate, không đảm bảo thứ tự (HashSet), hoặc giữ thứ tự chèn (LinkedHashSet), hoặc sắp xếp (TreeSet).
   - `Map`: ánh xạ key -> value, key không duplicate.

2. **Code**
   - [src/main/java/com/example/demo/repository/AnimalRepository.java](src/main/java/com/example/demo/repository/AnimalRepository.java) (dùng `LinkedHashMap` + `List`)
   - [src/main/java/com/example/demo/service/AnimalService.java](src/main/java/com/example/demo/service/AnimalService.java) (dùng `List.of(...)`)

3. **Giải thích code**
   - `AnimalRepository` dùng `LinkedHashMap` để lưu order chèn (dùng Map cho ánh xạ id->Animal). `findAll()` trả `List` bằng cách chuyển `storage.values()`.
   - `AnimalService.findAll()` trả `List.of(...)` (immutable list) minh họa `List` dùng cho tập hợp có thứ tự.

4. **OOP/Java concept đang được sử dụng**
   - Các collection interfaces: `List`, `Map`.
   - Sử dụng `LinkedHashMap` nếu muốn vừa truy xuất theo key vừa bảo lưu thứ tự chèn.

5. **Tại sao phải thiết kế như vậy**
   - `Map` hữu ích khi cần ánh xạ id -> entity; `List` khi cần thứ tự/cho client trả về array JSON.

6. **Lỗi người mới thường gặp**
   - Dùng `HashSet` khi cần thứ tự; kết quả bất ngờ.
   - Dùng `ArrayList` không đồng bộ trong đa luồng mà không dùng biện pháp đồng bộ.

7. **Khi dùng / không dùng**
   - Dùng `Set` khi cần loại bỏ duplicate.
   - Dùng `Map` khi cần tìm nhanh theo key (O(1) với HashMap).

---

## 4. Mutable vs Immutable

1. **Khái niệm**
   - Mutable: trạng thái có thể thay đổi sau khi khởi tạo (ví dụ: `ArrayList`, POJO có setter).
   - Immutable: trạng thái không đổi; mọi field `final` và không có setter (ví dụ: `String`, `List.of(...)` trả về immutable list).

2. **Code**
   - Immutable example: [src/main/java/com/example/demo/immutable/ImmutablePet.java](src/main/java/com/example/demo/immutable/ImmutablePet.java)
   - Mutable example: [src/main/java/com/example/entity/Owner.java](src/main/java/com/example/entity/Owner.java) (hasters/setters via Lombok)

3. **Giải thích code**
   - `ImmutablePet` có field `final`, không có setter và constructor private + static factory `of()`.
   - `Owner` được Lombok `@Getter @Setter` nên mutable.

4. **OOP/Java concept đang được sử dụng**
   - `final` fields, encapsulation, factory methods.

5. **Tại sao phải thiết kế như vậy**
   - Immutable objects an toàn trong đa luồng, dễ reasoning, dùng làm key trong map, tránh side-effects.

6. **Lỗi người mới thường gặp**
   - Cho rằng chỉ cần `final` trên tham chiếu là đủ — nhưng nếu field là mutable object (List) thì nội dung vẫn có thể thay đổi.

7. **Khi dùng / không dùng**
   - Dùng immutable cho value objects, DTO read-only, keys.
   - Dùng mutable cho entities ORM hoặc khi cần cập nhật nhiều lần.

---

## 5. Exception

1. **Khái niệm**
   - `try-catch`, `finally`, `throw`, `throws`.
   - Checked exceptions: bắt buộc khai báo/handle (hiếm dùng trong nhiều codebase hiện đại).
   - Unchecked exceptions: extends `RuntimeException`.

2. **Code**
   - Custom unchecked: [src/main/java/com/example/demo/exception/ResourceNotFoundException.java](src/main/java/com/example/demo/exception/ResourceNotFoundException.java)
   - Controller dùng `orElseThrow(...)` để ném `ResourceNotFoundException` trong [src/main/java/com/example/demo/controller/AnimalController.java](src/main/java/com/example/demo/controller/AnimalController.java)

3. **Giải thích code**
   - Khi truy vấn `id` không tìm thấy, ta `throw new ResourceNotFoundException("...")` (unchecked). Spring sẽ map exception có `@ResponseStatus` thành HTTP 404.
   - `try-catch` dùng để bắt và xử lý lỗi cục bộ; `finally` dùng để dọn dẹp (close streams).

4. **OOP/Java concept đang được sử dụng**
   - Exception handling, custom exception classes.

5. **Tại sao phải thiết kế như vậy**
   - Dùng unchecked cho lỗi runtime (bad request, not found) để không làm cho API ký hợp đồng nặng nề bởi `throws`.

6. **Lỗi người mới thường gặp**
   - Bắt `Exception` chung quá rộng rồi che giấu lỗi gốc.
   - Dùng checked exceptions không cần thiết gây rối API.

7. **Khi dùng / không dùng**
   - Dùng checked khi caller có thể và nên xử lý (ví dụ IO). Dùng unchecked cho logic lỗi và programming error.

---

## 6. Validation

1. **Khái niệm**
   - Bean Validation (`jakarta.validation`): `@NotNull`, `@NotBlank`, `@Size`, `@Min`, `@Max`, `@Email`.

2. **Code**
   - Request DTO with annotations: [src/main/java/com/example/demo/dto/AnimalRequest.java](src/main/java/com/example/demo/dto/AnimalRequest.java)
   - Controller endpoint uses `@Valid` in [src/main/java/com/example/demo/controller/AnimalController.java](src/main/java/com/example/demo/controller/AnimalController.java)

3. **Giải thích code**
   - Annotations trên fields cho phép framework (Spring + Hibernate Validator) tự kiểm tra yêu cầu.
   - `@Valid` trên parameter buộc Spring validate trước khi vào method; nếu không hợp lệ, Spring trả `400 Bad Request`.

4. **OOP/Java concept đang được sử dụng**
   - Declarative validation bằng annotation, separation of concerns.

5. **Tại sao phải thiết kế như vậy**
   - Đảm bảo dữ liệu đúng ngay từ rìa hệ thống (edge) trước khi vào service/repo.

6. **Lỗi người mới thường gặp**
   - Đặt validation logic trong controller trả về lỗi chung; nên dùng annotations cộng centralized handling.

7. **Khi dùng / không dùng**
   - Dùng Bean Validation cho input DTO. Không dùng cho nghiệp vụ phức tạp (sử dụng service-level checks).

---

## 7. Annotation (tổng quát + Spring + Lombok + JPA)

1. **Khái niệm**
   - Annotation là metadata gắn vào class/method/field để framework/library đọc và thay đổi hành vi.

2. **Code**
   - Spring: `@RestController`, `@Service`, `@Repository` trong các tệp controller/service/repository.
   - JPA: `@Entity`, `@Id` ví dụ trong [src/main/java/com/example/entity/Owner.java](src/main/java/com/example/entity/Owner.java)
   - Lombok: `@Getter`, `@Setter`, `@Data`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor` trong entity classes.

3. **Giải thích code**
   - `@RestController` = `@Controller` + `@ResponseBody` — trả JSON.
   - `@Service` đánh dấu component business; `@Repository` đánh dấu tầng persist.
   - Lombok tự sinh getter/setter/ctor để giảm boilerplate; nhưng cần chú ý phụ thuộc compile-time.

4. **OOP/Java concept đang được sử dụng**
   - Metadata-driven programming; IoC/DI (Spring) dựa trên annotation.

5. **Tại sao phải thiết kế như vậy**
   - Annotations làm code declarative, giảm cấu hình tay, tích hợp mạnh với framework.

6. **Lỗi người mới thường gặp**
   - Tin tưởng Lombok quá mức — quên rằng IDE hoặc build cần plugin/config.
   - Dùng `@Entity` mà quên `@Id` khiến JPA lỗi.

7. **Khi dùng / không dùng**
   - Dùng annotation khi framework hỗ trợ (Spring, JPA, Validation). Tránh lạm dụng custom annotation trừ khi thực sự cần.

---

## Kết luận ngắn

- Project này dùng một bài toán duy nhất: Animal Management. Mỗi file đã được thiết kế để minh họa rõ các khái niệm: OOP (kế thừa, trừu tượng, đa hình), `static` (factory, helper), collections (`List`, `Map`), immutable vs mutable, exceptions, validation, annotation (Spring/JPA/Lombok).
- Muốn tiếp: tôi có thể thêm `README` chạy thử, tests, hoặc chuyển `AnimalRepository` sang JPA `CrudRepository` để minh hoạ đầy đủ JPA persistence.
