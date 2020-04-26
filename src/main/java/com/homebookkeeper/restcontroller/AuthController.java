package com.homebookkeeper.restcontroller;

import com.homebookkeeper.DTO.UserCreationDTO;
import com.homebookkeeper.DTO.UserDTO;
import com.homebookkeeper.model.User;
import com.homebookkeeper.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private UserService userService;
    private ModelMapper modelMapper;

    public AuthController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(path = "/user")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email) {
        User user = userService.getByEmail(email).orElse(null);
        if (user == null) {
            return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<UserDTO>(convertToUserDTO(user), HttpStatus.OK);
    }

    @PostMapping(path = "/user")
    public ResponseEntity<UserDTO> createNewUser(@RequestBody UserCreationDTO userCreationDTO) {
        if (userCreationDTO == null) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = userService.save(convertToUser(userCreationDTO));

       return new ResponseEntity<UserDTO>(convertToUserDTO(user), HttpStatus.CREATED);
    }


    private UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private User convertToUser(UserCreationDTO userCreationDTO) {
        return modelMapper.map(userCreationDTO, User.class);
    }

    private User convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private UserCreationDTO convertToUserCreationDTO(User user) {
        return modelMapper.map(user, UserCreationDTO.class);
    }

    private UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

}
