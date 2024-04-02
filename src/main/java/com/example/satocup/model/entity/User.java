package com.example.satocup.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

@MappedSuperclass
public abstract class User implements UserDetails{
    @Column(name = "username", unique = true, nullable = false)
    @NotBlank(message = "Username cannot be blank")
    protected String username;
    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password cannot be blank")
    protected String password;
    @Column(name = "email",unique = true, nullable = false)
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    protected String email;
    @Column(name = "avatar", nullable = false)
    @NotBlank(message = "Avatar cannot be blank")
    protected String avatar;



}
