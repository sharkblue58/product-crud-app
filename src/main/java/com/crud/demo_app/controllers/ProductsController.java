package com.crud.demo_app.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crud.demo_app.models.Product;
import com.crud.demo_app.models.ProductDTO;
import com.crud.demo_app.services.ImageService;
import com.crud.demo_app.services.ProductsRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductsController {

  @Autowired
  private ProductsRepository repo;

  @Autowired
  private ImageService imageService;

  @GetMapping({ "", "/" })
  public String showProductList(Model model) {
    List<Product> products = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    model.addAttribute("products", products);
    return "products/index";
  }

  @GetMapping({ "/create", "/create/" })
  public String showCreatePage(Model model) {
    ProductDTO productDto = new ProductDTO();
    model.addAttribute("productDto", productDto);
    return "products/CreateProduct";
  }

  @PostMapping({ "/create", "/create/" })
  public String storeProduct(@Valid @ModelAttribute("productDto") ProductDTO productDto,
      BindingResult result) {
    if (productDto.getImageFile().isEmpty()) {
      result.addError(new FieldError("productDto", "imageFile", "image is required"));
    }

    if (result.hasErrors()) {
      return "/products/CreateProduct";
    }

    Product product = new Product();
    product.setName(productDto.getName());
    product.setBrand(productDto.getBrand());
    product.setCategory(productDto.getCategory());
    product.setPrice(productDto.getPrice());
    product.setDescription(productDto.getDescription());
    product.setImageFileName(imageService.storeImageOnDisk(productDto.getImageFile()));
    product.setCreatedAt(new Date());
    repo.save(product);

    return "redirect:/products";
  }

  @GetMapping( "/edit")
  public String showEditPage(Model model , @RequestParam int id) {
    try {
       Product product = repo.findById(id).get();
       model.addAttribute("product", product);
       ProductDTO productDto = new ProductDTO();
       productDto.setName(product.getName());
       productDto.setBrand(product.getBrand());
       productDto.setCategory(product.getCategory());
       productDto.setPrice(product.getPrice());
       productDto.setDescription(product.getDescription());
       
       model.addAttribute("productDto", productDto);

    } catch (Exception e) {
      System.out.println("Exception: " + e.getMessage());
      return "/products";
    }

    return "products/EditProduct";
  }

  @PostMapping({"/edit", "/edit/"})
  public String updateProduct(
    @Valid @ModelAttribute("productDto") ProductDTO productDto,
    @RequestParam int id ,
    Model model,
    BindingResult result
    ) {

    try {
      Product product = repo.findById(id).get();
      model.addAttribute("product", product);

      if (result.hasErrors()) {
        return "/products/EditProduct";
      }

      if (!productDto.getImageFile().isEmpty()) {
        imageService.deleteImageOnDisk(product.getImageFileName());
        product.setImageFileName(imageService.storeImageOnDisk(productDto.getImageFile()));
      }else{
        result.addError(new FieldError("productDto", "imageFile", "image is required"));
      }

      product.setName(productDto.getName());
      product.setBrand(productDto.getBrand());
      product.setCategory(productDto.getCategory());
      product.setPrice(productDto.getPrice());
      product.setDescription(productDto.getDescription());
      repo.save(product);

    } catch (Exception e) {
      System.out.println("Exception: " + e.getMessage());
    }
    return "redirect:/products";
  }

  @GetMapping({"/delete", "/delete/"})
  public String deleteProduct(@RequestParam int id) {
    try {
      Product product = repo.findById(id).get();
      imageService.deleteImageOnDisk(product.getImageFileName());
      repo.delete(product);
    } catch (Exception e) {
      System.out.println("Exception: " + e.getMessage());
    }
    return "redirect:/products";
  }

  @GetMapping({"/view", "/view/"})
  public String viewProduct(@RequestParam int id, Model model) {
    try {
      Product product = repo.findById(id).get();
      model.addAttribute("product", product);
    } catch (Exception e) {
      System.out.println("Exception: " + e.getMessage());
    }
    return "products/ViewProduct";
  }

}
