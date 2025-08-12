package com.foodapp.food_delivery_backend.controller;

import com.foodapp.food_delivery_backend.modal.User;
import com.foodapp.food_delivery_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // allow your frontend origin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Signup API
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, String> signupData) {
        String name = signupData.get("name");
        String email = signupData.get("email");
        String password = signupData.get("password");

        if (userRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.status(400).body(Map.of("message", "User already exists"));
        }

        User newUser = new User(name, email, password);
        userRepository.save(newUser);

        return ResponseEntity.ok(Map.of(
                "message", "User registered successfully",
                "username", name,
                "email", email
        ));
    }

    // Login API
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("username").trim().toLowerCase();
        String password = loginData.get("password");

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                return ResponseEntity.ok(Map.of(
                        "message", "Login Successful!",
                        "username", user.get().getName(),
                        "email", email
                ));
            }
        }
        return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
    }
}