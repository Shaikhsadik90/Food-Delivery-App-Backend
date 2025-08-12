package com.foodapp.food_delivery_backend.repository;

import com.foodapp.food_delivery_backend.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
