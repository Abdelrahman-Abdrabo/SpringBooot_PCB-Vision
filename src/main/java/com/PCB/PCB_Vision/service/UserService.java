package com.PCB.PCB_Vision.service;

import com.PCB.PCB_Vision.model.PCB;
import com.PCB.PCB_Vision.model.User;
import com.PCB.PCB_Vision.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get a user by ID
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Save a new user
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Delete a user by ID
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }


    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    // User login: validate email and password
    public Optional<User> login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmailAndPassword(email, password);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
                return Optional.of(user); // Return the user if login is successful
        }
        return Optional.empty(); // Return empty if login fails
    }

    // Edit user details
    public User updateUser(String id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setFname(updatedUser.getFname());
            user.setLname(updatedUser.getLname());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setPhone(updatedUser.getPhone());
            user.setImageURL(updatedUser.getImageURL());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    // Edit PCB details
    public PCB updatePCB(String userId, String pcbId, PCB updatedPCB) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<PCB> pcbList = user.getPcbs();
            for (PCB pcb : pcbList) {
                if (pcb.getId().equals(pcbId)) {
                    pcb.setName(updatedPCB.getName());
                    pcb.setDefects(updatedPCB.getDefects());
                    pcb.setComponents(updatedPCB.getComponents());
                    userRepository.save(user); // Save updated user with updated PCB
                    return pcb;
                }
            }
            throw new RuntimeException("PCB not found with ID: " + pcbId);
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }
}
