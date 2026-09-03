package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 1. Built-in exception: exception có sẵn trong Java hoặc thư viện/framework.
 * 2. Custom exception: exception do người dùng tự định nghĩa để xử lý các tình
 * huống
 * đặc biệt trong ứng dụng của mình.
 */

/* Đây là custom exception */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
   public ResourceNotFoundException(String message) {
      super(message);
   }
}
