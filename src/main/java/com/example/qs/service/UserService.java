package com.example.qs.service;


import com.example.qs.dto.ResponseDto;
import com.example.qs.dto.user.SingupDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public ResponseDto signUp(SingupDto singupDto) {
        ResponseDto responseDto = new ResponseDto("success", "dummy response");
        return responseDto;
    }
}
