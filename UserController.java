package com.example.library2.controller;

import com.example.library2.entity.User;
import com.example.library2.exception.UserNotFoundException;
import com.example.library2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return userService.createUser(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails){
        return userService.getUserById(id)
                .map(user -> {
                    user.setUsername(userDetails.getUsername());
                    user.setPassword(userDetails.getPassword());
                    userService.updateUser(id, user);
                    return new ResponseEntity<>(user, HttpStatus.OK);
                })
                .orElseThrow(() -> new UserNotFoundException("Utilizatorul cu ID-ul" + id + " nu a fost gasit. "));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return userService.getUserById(id)
                .map(user -> {
                    userService.deleteUser(id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                })
                .orElseThrow(()-> new UserNotFoundException("Utilizatorul cu ID-ul " + id + " nu a fost gasit. "));
    }

}
