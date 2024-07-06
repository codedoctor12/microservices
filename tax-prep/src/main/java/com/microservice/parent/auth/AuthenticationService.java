package com.microservice.parent.auth;

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
    public AuthenticationResponse register(RegisterRequest request) {
        var user = Customer.builder()
            .firstname(request.getFirstName())
            .lastname(request.getLastName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();
        repo.save(user);
        var jwtToken = jwt.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
        
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
                return AuthenticationResponse.builder().token(jwtToken).build();        
    }



}
