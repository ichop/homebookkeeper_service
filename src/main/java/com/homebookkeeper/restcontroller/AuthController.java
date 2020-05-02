package com.homebookkeeper.restcontroller;

import com.homebookkeeper.DTO.UserDTO;
import com.homebookkeeper.mapper.UserMapper;
import com.homebookkeeper.model.User;
import com.homebookkeeper.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final UserService userService;
    private final UserMapper userMapper;

    public AuthController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(path = "/user")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email) {
        User user = userService.getByEmail(email).orElse(null);
        if (user == null) {
            return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<UserDTO>(userMapper.toDTO(user), HttpStatus.OK);
    }

    @PostMapping(path = "/user")
    public ResponseEntity<UserDTO> createNewUser(@RequestBody UserDTO userDTO) {
        if (userDTO == null) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = userService.save(userMapper.toEntity(userDTO));

       return new ResponseEntity<UserDTO>(userMapper.toDTO(user), HttpStatus.CREATED);
    }
}
