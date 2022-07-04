package com.example.qs.service;


import com.example.qs.dto.ResponseDto;
import com.example.qs.dto.user.SingInDto;
import com.example.qs.dto.user.SingInResponseDto;
import com.example.qs.dto.user.SingupDto;
import com.example.qs.exceptions.AuthenticationFailException;
import com.example.qs.exceptions.CustomException;
import com.example.qs.model.AuthenticationToken;
import com.example.qs.model.User;
import com.example.qs.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Transactional
    public ResponseDto signUp(SingupDto singupDto) throws JsonProcessingException {

        //Actions to do here
        //check if user exists
        //save the user
        //hash the password
        //create the token
        if (Objects.nonNull(userRepository.findByEmail(singupDto.getEmail()))) {
            ObjectMapper objectMapper = new ObjectMapper();
            String mys = "user already exists";
            String newString = objectMapper.writeValueAsString(mys);
            throw new CustomException(newString);
        }

        String encryptedpassword = singupDto.getPassword();

        try {
            encryptedpassword = hashPassword(encryptedpassword);
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

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationService.saveConfirmationToken(authenticationToken);

        return new ResponseDto("success", "user has been created");
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }

    public SingInResponseDto singIn(SingInDto singInDto) throws JsonProcessingException {

        //tasks to be accomplished by this method

        //find the user if present
        User user = userRepository.findByEmail(singInDto.getEmail());
        if (Objects.isNull(user)) {
            ObjectMapper objectMapper = new ObjectMapper();
            String mys = "user not found";
            String newString = objectMapper.writeValueAsString(mys);
            throw new AuthenticationFailException(newString);
        }

        //hash the password and make a comparison in the db
        try {
            if (!user.getPassword().equals(hashPassword(singInDto.getPassword()))) {
                throw new AuthenticationFailException("password not matching");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //retrieve the token
        AuthenticationToken token = authenticationService.getToken(user);
        if (Objects.isNull(token)) {
            throw new CustomException("token not present");
        }

        return new SingInResponseDto("success", token.getToken());

        //return a response
    }
}
