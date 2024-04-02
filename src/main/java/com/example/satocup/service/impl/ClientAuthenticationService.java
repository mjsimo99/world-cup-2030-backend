package com.example.satocup.service.impl;

import com.example.satocup.model.entity.Client;
import com.example.satocup.model.security.AuthenticationResponse;
import com.example.satocup.model.security.Token;
import com.example.satocup.repository.ClientRepository;
import com.example.satocup.repository.TokenRepository;
import com.example.satocup.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientAuthenticationService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    public ClientAuthenticationService(ClientRepository clientRepository,
                                       PasswordEncoder passwordEncoder,
                                       JwtService jwtService,
                                       TokenRepository tokenRepository,
                                       AuthenticationManager authenticationManager) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse authenticate(Client request) {
        Client client = clientRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new NoSuchElementException("Client not found"));

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
        List<Token> validTokens = tokenRepository.findAllTokensByClient(client.getClientId());
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
    public  AuthenticationResponse register(Client request) {
        if (clientRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        Client client = new Client();

        client.setUsername(request.getUsername());
        client.setPassword(passwordEncoder.encode(request.getPassword()));
        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setEmail(request.getEmail());
        client.setPhoneNumber(request.getPhoneNumber());
        client.setAddress(request.getAddress());
        client.setDateOfBirth(request.getDateOfBirth());
        client.setAvatar(request.getAvatar())  ;
        client.setMoney(Double.parseDouble("1000.0"));

        String jwt = jwtService.generateToken(client);
        client = clientRepository.save(client);
        saveClientToken(jwt, client);

        return new AuthenticationResponse(jwt, "Client registration was successful");

    }
}
