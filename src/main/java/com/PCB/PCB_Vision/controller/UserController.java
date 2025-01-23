package com.PCB.PCB_Vision.controller;

import com.PCB.PCB_Vision.model.PCB;
import com.PCB.PCB_Vision.model.User;
import com.PCB.PCB_Vision.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get a user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userService.emailExists(user.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
        }
        userService.saveUser(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    // Retrieve the PCB list for a specific user by user ID
    @GetMapping("/{id}/pcbs")
    public List<PCB> getPCBsByUserId(@PathVariable String id) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            return userOptional.get().getPcbs();
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }

    // User login
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody Map<String, String> loginData) {
        Optional<User> userOptional = userService.login(loginData.get("email"), loginData.get("password"));
        if (userOptional.isPresent()) {
            return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Return 401 if login fails
        }
    }

    // Update user details
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        try {
            User user = userService.updateUser(id, updatedUser);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Add a new PCB to a specific user's list
    @PostMapping("/{userId}/pcbs")
    public ResponseEntity<String> addPcbToUser(@PathVariable String userId, @RequestBody PCB pcb) {
        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String uniqueId = UUID.randomUUID().toString();
            pcb.setId(uniqueId);
            user.getPcbs().add(pcb);
            userService.saveUser(user);
            return new ResponseEntity<>("PCB added successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    // Update PCB details for a specific user
    @PutMapping("/{userId}/pcbs/{pcbId}")
    public ResponseEntity<PCB> updatePCB(
            @PathVariable String userId,
            @PathVariable String pcbId,
            @RequestBody PCB updatedPCB
    ) {
        try {
            PCB pcb = userService.updatePCB(userId, pcbId, updatedPCB);
            return new ResponseEntity<>(pcb, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a specific PCB from a user's list
    @DeleteMapping("/{userId}/pcbs/{pcbId}")
    public ResponseEntity<String> deletePcbFromUser(@PathVariable String userId, @PathVariable String pcbId) {
        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            boolean removed = user.getPcbs().removeIf(pcb -> pcb.getId().equals(pcbId));
            if (removed) {
                userService.saveUser(user);
                return new ResponseEntity<>("PCB deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("PCB not found", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }


}
