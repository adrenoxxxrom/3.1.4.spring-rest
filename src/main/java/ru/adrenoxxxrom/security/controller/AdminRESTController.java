package ru.adrenoxxxrom.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.adrenoxxxrom.security.model.Role;
import ru.adrenoxxxrom.security.model.User;
import ru.adrenoxxxrom.security.service.RoleService;
import ru.adrenoxxxrom.security.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRESTController {
    UserService userService;
    RoleService roleService;

    @Autowired
    public AdminRESTController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping(value = "/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> usersList = userService.getAllUsers();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User userById = userService.getUserById(id);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }

    @PatchMapping(value = "/update-user/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user, @PathVariable Long id) {
        userService.updateUser(id, user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.removeUserById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value ="/get-roles")
    public ResponseEntity<List<Role>> getRoles() {
        List<Role> roles = roleService.getRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
