package com.example.satocup.controller;

import com.example.satocup.model.entity.Admin;
import com.example.satocup.model.security.AuthenticationResponse;
import com.example.satocup.service.impl.AdminAuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AdminAuthenticationController {
    private final AdminAuthenticationService adminAuthService;

    public AdminAuthenticationController(AdminAuthenticationService adminAuthService) {
        this.adminAuthService = adminAuthService;
    }
    @PreAuthorize("permitAll()")
    @PostMapping("/admin/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody Admin request
    ) {
        return ResponseEntity.ok(adminAuthService.register(request));
    }
    @PreAuthorize("permitAll()")

    @PostMapping("/admin/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody Admin request
    ) {
        return ResponseEntity.ok(adminAuthService.authenticate(request));
    }

}
