package com.ferras.backendtransaction.controlleres;

import com.ferras.backendtransaction.application.dtos.UserDTO;
import com.ferras.backendtransaction.application.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("users")

public class UserController {

    private final IUserService _userService;
    @Autowired
    public UserController(IUserService userService) {
        _userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) throws Exception {
        UserDTO user = _userService.createUser(userDTO);
        return ResponseEntity.ok(user);
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUser(){
        List<UserDTO> users = _userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping(value = "/{document}")
    public ResponseEntity<UserDTO> findUserByDocument(@PathVariable("document") String document) throws Exception {
        UserDTO user = _userService.findUserByDocument(document);
        return ResponseEntity.ok(user);
    }
}
