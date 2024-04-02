package com.example.satocup.service.impl;

import com.example.satocup.model.entity.Admin;
import com.example.satocup.model.security.AuthenticationResponse;
import com.example.satocup.model.security.Token;
import com.example.satocup.repository.AdminRepository;
import com.example.satocup.repository.TokenRepository;
import com.example.satocup.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AdminAuthenticationService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    public AdminAuthenticationService(AdminRepository adminRepository,
                                      PasswordEncoder passwordEncoder,
                                      JwtService jwtService,
                                      TokenRepository tokenRepository,
                                      AuthenticationManager authenticationManager) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse authenticate(Admin request) {
        Admin admin = adminRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new NoSuchElementException("Admin not found"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String jwt = jwtService.generateTokenAdmin(admin);
        revokeAllTokenByAdmin(admin);
        saveAdminToken(jwt, admin);

        return new AuthenticationResponse(jwt, "Admin login was successful");
    }

    private void revokeAllTokenByAdmin(Admin admin) {
        List<Token> validTokens = tokenRepository.findAllTokensByAdmin(admin.getAdminId());
        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }

    private void saveAdminToken(String jwt, Admin admin) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setAdmin(admin);
        tokenRepository.save(token);
    }

    public AuthenticationResponse register(Admin request) {
        if (adminRepository.findByUsername(request.getUsername()).isPresent()) {
            return new AuthenticationResponse(null, "Username is already taken");
        }

        Admin admin = new Admin();

        admin.setUsername(request.getUsername());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setEmail(request.getEmail());
        admin.setAvatar(request.getAvatar());

        admin = adminRepository.save(admin);

        String jwt = jwtService.generateTokenAdmin(admin);

        saveAdminToken(jwt, admin);

        return new AuthenticationResponse(jwt, "Admin registration was successful");

    }

}
