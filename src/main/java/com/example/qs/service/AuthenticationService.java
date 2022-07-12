package com.example.qs.service;


import com.example.qs.exceptions.AuthenticationFailException;
import com.example.qs.model.AuthenticationToken;
import com.example.qs.model.User;
import com.example.qs.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {


    @Autowired
    TokenRepository tokenRepository;

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepository.findByUser(user);
    }

    //retrieving the user via the token
    public User getUser(String token) {
        final AuthenticationToken authenticationToken = tokenRepository.findByToken(token);

        if (Objects.isNull(authenticationToken)) {
            return null;
        }
        //assert false;
        return authenticationToken.getUser();
    }

    public void authenticate(String token) throws AuthenticationFailException {
        //perform a null check
        if (Objects.isNull(token)) {
            //throw an exception
            throw new AuthenticationFailException("no token found!!");
        }
        if (!Objects.isNull(getUser(token))) {
            throw new AuthenticationFailException("invalid token, no user found!!");
        }
    }
}
