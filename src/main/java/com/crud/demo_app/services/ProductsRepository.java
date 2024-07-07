package com.crud.demo_app.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.demo_app.models.Product;

public interface ProductsRepository extends JpaRepository<Product,Integer> {

}
