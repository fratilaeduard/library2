package com.example.library2.service;
//
import com.example.library2.entity.User;
import com.example.library2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
    public User createUser(User user){
        return userRepository.save(user);
    }
    public User updateUser(Long id, User userDetails){
        if (userRepository.existsById(id)){
            userDetails.setId(id);
            return userRepository.save(userDetails);
        }
        return null;
    }
    @Transactional
    public ResponseEntity<String> deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return ResponseEntity.ok().body("Utilizatorul a fost șters cu succes.");
        } catch (Exception e) {
            // Loghează excepția pentru debug
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Eroare la ștergerea utilizatorului.");
        }
    }

}
