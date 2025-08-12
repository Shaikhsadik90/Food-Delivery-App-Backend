package com.foodapp.food_delivery_backend.repository;

import com.foodapp.food_delivery_backend.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // 👈 Add this line
}
