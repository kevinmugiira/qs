package com.example.qs.controller;


import com.example.qs.dto.ResponseDto;
import com.example.qs.dto.user.SingInDto;
import com.example.qs.dto.user.SingInResponseDto;
import com.example.qs.dto.user.SingupDto;
import com.example.qs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("user")
@RestController
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping("/singup")
    public ResponseDto signup(@RequestBody SingupDto singupDto) {
        return userService.signUp(singupDto);
    }

    @PostMapping("/singin")
    public SingInResponseDto singIn(@RequestBody SingInDto singInDto) {
        return userService.singIn(singInDto);
    }
}
