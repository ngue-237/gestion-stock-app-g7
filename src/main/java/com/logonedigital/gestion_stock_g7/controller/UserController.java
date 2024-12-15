package com.logonedigital.gestion_stock_g7.controller;

import com.logonedigital.gestion_stock_g7.dto.user.UserDTO;
import com.logonedigital.gestion_stock_g7.dto.user.UserReqDTO;
import com.logonedigital.gestion_stock_g7.services.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping(path = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserReqDTO userReqDTO){

        return ResponseEntity
                .status(201)
                .body(this.userService.registerUser(userReqDTO));
    }
}
