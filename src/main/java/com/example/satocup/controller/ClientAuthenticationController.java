package com.example.satocup.controller;

import com.example.satocup.model.entity.Client;
import com.example.satocup.model.security.AuthenticationResponse;
import com.example.satocup.service.impl.ClientAuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ClientAuthenticationController {

    private final ClientAuthenticationService clientAuthService;

    public ClientAuthenticationController(ClientAuthenticationService clientAuthService) {
        this.clientAuthService = clientAuthService;
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/client/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody Client request
    ) {
        return ResponseEntity.ok(clientAuthService.register(request));
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/client/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody Client request
    ) {
        return ResponseEntity.ok(clientAuthService.authenticate(request));
    }





}
