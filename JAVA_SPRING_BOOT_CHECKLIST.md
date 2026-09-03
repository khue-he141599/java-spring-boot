# Java, Spring và Spring Boot Checklist

Tài liệu này là bản đồ ôn luyện của project. Các mục có ví dụ trực tiếp trong
source; những chủ đề nâng cao sẽ được bổ sung thành các series riêng.

| Chủ đề | Trạng thái | Ví dụ trong project |
|---|---|---|
| Class và object | Đã có | `Animal`, `Dog`, `Cat`, `Bird` |
| Encapsulation | Đã có | Field `private`, getter/setter có validation trong `Animal` |
| Access modifier và package | Đã có | Các package `entity`, `service`, `repository`, `controller`; constructor `protected/private` |
| Inheritance | Đã có | `Dog extends Animal` |
| Abstraction | Đã có | `abstract Animal`, `sound()`, `move()` |
| Overriding | Đã có | `Dog/Cat/Bird` override `sound()` và `move()` |
| Overloading | Đã có | Constructor `Animal`, method `eat(...)`, `Cat` constructor |
| `this` và `super` | Đã có | Constructor chaining trong `Animal`, gọi cha trong class con |
| Static method/variable | Đã có | `AnimalFactory`, `ANIMAL_TYPE`, `AnimalResponse.from()` |
| `List`, `Set`, `Map` | Đã có | `AnimalRepository` |
| Mutable/Immutable | Đã có | `MutablePet`, `ImmutablePet`, response dùng field `final` |
| Built-in exception | Đã có | `IllegalArgumentException`, `MethodArgumentNotValidException` |
| Custom unchecked exception | Đã có | `ResourceNotFoundException` |
| Exception handler tập trung | Đã có | `GlobalExceptionHandler` |
| Bean Validation | Đã có | `AnimalRequest` và `@Valid` trong controller |
| Spring DI | Đã có | Constructor injection trong `AnimalController` |
| Spring Web REST | Đã có | `@RestController`, `@GetMapping`, `@PostMapping` |
| Spring component scan | Đã có | `@Service`, `@Repository`, `@RestController` |
| JPA entity/inheritance | Đã có | `Animal`, `Owner`, `@Inheritance(SINGLE_TABLE)` |
| Test | Đã có | `AnimalOopTest`, `DemoApplicationTests` |

## Các series tiếp theo

- Java core: `equals/hashCode`, `enum`, `interface`, generics, stream, lambda,
  Optional, exception checked/unchecked, thread và concurrency.
- Spring: IoC/DI sâu hơn, bean lifecycle, scopes, AOP, `@Transactional`,
  configuration và profile.
- Spring Boot: JPA repository thực tế, service boundary, DTO mapping,
  pagination, logging, Actuator, security và integration test.

Project có đủ các mục nền tảng hiện tại, nhưng không nên xem đây là toàn bộ
Java/Spring/Spring Boot; checklist này giúp mở rộng dần mà vẫn giữ cấu trúc rõ ràng.
