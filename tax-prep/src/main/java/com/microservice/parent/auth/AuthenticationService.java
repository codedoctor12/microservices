package com.microservice.parent.auth;

import com.microservice.parent.model.Token;
import com.microservice.parent.model.TokenType;
import com.microservice.parent.repo.TokenRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.microservice.parent.config.JwtService;
import com.microservice.parent.model.Customer;
import com.microservice.parent.model.Role;
import com.microservice.parent.repo.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final CustomerRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwt;    
    private final AuthenticationManager auth;
    private final TokenRepository tokenRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = Customer.builder()
            .firstname(request.getFirstName())
            .lastname(request.getLastName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();
        var savedUser = repo.save(user);
        var jwtToken = jwt.generateToken(user);
        var refreshToken = jwt.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
        

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
         auth.authenticate(
            new UsernamePasswordAuthenticationToken(
            request.getEmail(), 
            request.getPassword())
            );
            var user = repo.findByEmail(request.getEmail())
                .orElseThrow();
                var jwtToken = jwt.generateToken(user);
        var refreshToken = jwt.generateRefreshToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
    private void saveUserToken(Customer user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
    private void revokeAllUserTokens(Customer user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(Math.toIntExact(user.getId()));
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }



}
