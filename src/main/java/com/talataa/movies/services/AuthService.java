package com.talataa.movies.services;

import com.talataa.movies.dto.SessionDTO;
import com.talataa.movies.dto.TokenDTO;
import com.talataa.movies.mapper.SessionMapper;
import com.talataa.movies.mapper.TokenMapper;
import com.talataa.movies.repositories.TheMovieDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Value("${movies.moviesDB.authUrl}")
    private String AUTH_URL;

    @Autowired
    TokenMapper tokenMapper;

    @Autowired
    SessionMapper sessionMapper;

    @Autowired
    TheMovieDBRepository theMovieDBRepository;

    public TokenDTO generateToken() {
        return tokenMapper.tokenAsTokenDTO(theMovieDBRepository.generateAuthToken(), AUTH_URL);
    }

    public SessionDTO logIn(String token) {
        return sessionMapper.sessionAsSessionDTO(theMovieDBRepository.createSession(token));
    }

    public void logOut(String sessionID) {
        theMovieDBRepository.deleteSession(sessionID);
    }

}

