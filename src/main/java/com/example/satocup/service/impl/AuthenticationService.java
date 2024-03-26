package com.example.satocup.service.impl;
/*
import com.example.satocup.model.entity.Client;
import com.example.satocup.model.entity.Client;
import com.example.satocup.model.security.AuthenticationResponse;
import com.example.satocup.model.security.Role;
import com.example.satocup.security.JwtService;
import com.example.satocup.service.ClientService;
import com.example.satocup.service.TokenRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class AuthenticationService {

    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final TokenRepository tokenRepository;

    private final AuthenticationManager authenticationManager;



    public AuthenticationService(ClientService clientService,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 TokenRepository tokenRepository,
                                 AuthenticationManager authenticationManager) {
        this.clientService = clientService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(Client request) {

        if(clientService.findByUsername(request.getUsername()).isPresent()) {
            return new AuthenticationResponse(null, "Client already exist");
        }
        Client client = new Client();


        client.setEmail(request.getFirstName());
        client.setUsername(request.getUsername());
        client.setPassword(passwordEncoder.encode(request.getPassword()));


        client = clientService.save(client);

        String jwt = jwtService.generateToken(client);

        saveClientToken(jwt, client);

        return new AuthenticationResponse(jwt, "Client registration was successful");

    }

    public AuthenticationResponse authenticate(Client request) {
        Client client = clientService.findByUsername(request.getUsername()).orElseThrow();


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String jwt = jwtService.generateToken(client);
        revokeAllTokenByClient(client);
        saveClientToken(jwt, client);

        return new AuthenticationResponse(jwt, "Client login was successful");
    }

    private void revokeAllTokenByClient(Client client) {
        List<Token> validTokens = tokenRepository.findAllTokensByClient(client.getId());
        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }

    private void saveClientToken(String jwt, Client client) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setClient(client);
        tokenRepository.save(token);
    }

    public void activateAccountById(Integer clientId) {
        Client client = clientService.findById(clientId)
                .orElseThrow(() -> new NoSuchElementException("Client not found with ID: " + clientId));

        client.setActive(true);
        clientService.save(client);
    }

    public void deactivateAccountById(Integer clientId) {
        Client client = clientService.findById(clientId)
                .orElseThrow(() -> new NoSuchElementException("Client not found with ID: " + clientId));

        client.setActive(false);
        clientService.save(client);
    }
}


 */