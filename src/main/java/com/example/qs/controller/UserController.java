package com.example.qs.controller;


import com.example.qs.dto.ResponseDto;
import com.example.qs.dto.user.SingInDto;
import com.example.qs.dto.user.SingInResponseDto;
import com.example.qs.dto.user.SingupDto;
import com.example.qs.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("user")
@RestController
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping("/singup")
    public ResponseDto signup(@RequestBody SingupDto singupDto) throws JsonProcessingException {
        return userService.signUp(singupDto);
    }

    @PostMapping("/singin")
    public SingInResponseDto singIn(@RequestBody SingInDto singInDto) throws JsonProcessingException {
        return userService.singIn(singInDto);
    }
}
