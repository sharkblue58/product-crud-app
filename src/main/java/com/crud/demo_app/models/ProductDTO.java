package com.crud.demo_app.models;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDTO {

    @Valid

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Brand cannot be empty")
    private String brand;

    @NotBlank(message = "Category cannot be empty")
    private String category;

    @Min(value = 0, message = "Price cannot be negative")
    private double price;

    @Size(min = 10, message = "Description must be at least 10 characters")
    @Size(max = 2000, message = "Description must be less than 2000 characters")
    private String description;

    private MultipartFile imageFile;

}
