package com.solomon.user.controller;

import com.solomon.user.VO.ResponseTemplateVO;
import com.solomon.user.entity.User;
import com.solomon.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTemplateVO> getUserWithDepartment(@PathVariable("id") Long userId) {
        return new ResponseEntity(userService.getUserWithDepartment(userId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity(userService.addUser(user), HttpStatus.CREATED);
    }


    @PostMapping("/saveall")
    public ResponseEntity<List<User>> saveAllUsers(@RequestBody List<User> userList) {
        List<User> userResponse = userService.saveAllUsers(userList);
        return new ResponseEntity(userResponse, HttpStatus.OK);
    }

}
