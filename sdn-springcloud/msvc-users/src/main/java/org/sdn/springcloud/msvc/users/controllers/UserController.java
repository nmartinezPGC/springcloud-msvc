package org.sdn.springcloud.msvc.users.controllers;

import jakarta.validation.Valid;
import org.sdn.springcloud.msvc.users.Services.UserService;
import org.sdn.springcloud.msvc.users.models.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<UserEntity> listAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> userDetail(@PathVariable Long id){
        Optional<UserEntity> optionalUser = userService.getById(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<?> newUser(@Valid @RequestBody UserEntity user, BindingResult result){
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }

        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().
                    body(Collections.
                            singletonMap("msg", "Ya existe un Usuario con ese Email"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserEntity user, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }

        Optional<UserEntity> optionalUser = userService.getById(id);
        if (optionalUser.isPresent()) {
            UserEntity userEntity = optionalUser.get();

            if ( !user.getEmail().isEmpty() &&
                    !user.getEmail().equalsIgnoreCase(userEntity.getEmail()) &&
                    userService.findUserByEmail(user.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().
                        body(Collections.
                                singletonMap("msg", "Ya existe un Usuario con ese Email"));
            }

            userEntity.setFullUserName(user.getFullUserName());
            userEntity.setUserIdentification(user.getUserIdentification());
            userEntity.setUserAddress(user.getUserAddress());
            userEntity.setPassword(user.getPassword());
            userEntity.setUserName(user.getUserName());
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userEntity));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        Optional<UserEntity> userEntity = userService.getById(id);
        if (userEntity.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private static ResponseEntity<Map<String, String>> getMapResponseEntity(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
