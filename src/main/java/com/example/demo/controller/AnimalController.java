package com.example.demo.controller;

import com.example.demo.dto.AnimalRequest;
import com.example.demo.entity.Animal;
import com.example.demo.entity.AnimalFactory;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.service.AnimalService;

import jakarta.validation.Valid;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Controller: là một annotation trong Spring Boot được sử dụng để đánh dấu
 *              class là một controller trong mô hình MVC
 *              (Model-View-Controller). Nó cho phép
 *              class này xử lý các yêu cầu HTTP và trả về một view (thường là
 *              một trang HTML) để hiển thị dữ liệu cho người dùng (index.jsp,
 *              home.html, hoặc template để trình duyệt render).
 * 
 * @RestController: là một annotation trong Spring Boot được sử dụng để đánh dấu
 *                  class là một RESTful web service controller. Nó kết hợp
 *                  giữa @Controller và
 *                  @ResponseBody, cho phép class này xử lý các yêu cầu HTTP và
 *                  trả về dữ liệu
 *                  trực tiếp dưới dạng JSON hoặc XML thay vì trả về một view.
 *
 * @RequestMapping ("/animals"): là một annotation trong Spring
 *                 Boot được sử dụng
 *                 để ánh xạ các yêu cầu HTTP đến các phương thức xử lý trong
 *                 controller. Trong
 *                 trường hợp này, tất cả các yêu cầu đến đường dẫn "/animals"
 *                 sẽ được xử lý bởi
 *                 các phương thức trong AnimalController.
 */
@RestController
@RequestMapping("/animals")
public class AnimalController {

    /**
     * final là một từ khóa trong Java được sử dụng để khai báo hằng số, biến không
     * thể thay đổi giá trị sau khi được khởi tạo. Khi một biến được khai báo là
     * final, nó chỉ có thể được gán giá trị một lần duy nhất và không thể thay đổi
     * sau đó. Trong trường hợp này, final được sử dụng để đảm bảo rằng
     * animalService sẽ không bị thay đổi sau khi được khởi tạo trong constructor
     * của AnimalController.
     */
    private final AnimalService animalService;
    private final AnimalRepository animalRepository;

    public AnimalController(AnimalService animalService, AnimalRepository animalRepository) {
        this.animalService = animalService;
        this.animalRepository = animalRepository;
    }

    /**
     * @GetMapping: là một annotation trong Spring Boot được sử dụng để ánh xạ các
     *              yêu cầu HTTP GET đến các phương thức xử lý trong controller.
     *              Trong trường hợp
     *              này, phương thức findAll() sẽ được gọi khi có một yêu cầu GET
     *              đến
     *              đường dẫn "/animals".
     *              List<AnimalResponse>: là kiểu dữ liệu trả về của phương thức
     *              findAll().
     *              Nó là một danh sách (List) chứa các đối tượng AnimalResponse,
     *              đại diện
     *              cho thông tin của các động vật được trả về từ dịch vụ
     *              AnimalService.
     */
    @GetMapping
    public List<AnimalResponse> findAll() {
        return animalService.findAll()
                .stream()
                .map(AnimalResponse::from)
                .toList();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AnimalWithIdResponse findById(@PathVariable("id") Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found: " + id));
        return new AnimalWithIdResponse(id, AnimalResponse.from(animal));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AnimalWithIdResponse create(@Valid @RequestBody AnimalRequest request) {
        // create concrete Animal using the factory (demonstrates static factory +
        // polymorphism)
        Animal animal = AnimalFactory.create(request.getType(), request.getName(), request.getAge(),
                request.getWeight());
        Long id = animalRepository.save(animal);
        return new AnimalWithIdResponse(id, AnimalResponse.from(animal));
    }
}
