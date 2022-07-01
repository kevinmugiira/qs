package com.example.qs.service;


import com.example.qs.dto.ResponseDto;
import com.example.qs.dto.user.SingupDto;
import com.example.qs.exceptions.CustomException;
import com.example.qs.model.User;
import com.example.qs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseDto signUp(SingupDto singupDto) {

        //Actions to do here
        //check if user exists
        //save the user
        //hash the password
        //create the token
        if (Objects.nonNull(userRepository.findByEmail(singupDto.getEmail()))) {
            throw new CustomException("user already exists!");
        }

        String encryptedpassword = singupDto.getPassword();

        try {
            encryptedpassword = hashPassword(singupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new CustomException(e.getMessage()); //you can use this if you want to throw an exception
        }

        User user = new User(singupDto.getFirstName(),
                             singupDto.getLastName(),
                             singupDto.getContact(),
                             singupDto.getEmail(),
                             encryptedpassword
                );
        userRepository.save(user);


        return new ResponseDto("success", "dummy response");
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;
    }
}
