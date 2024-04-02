package com.example.satocup.model.entity;


import com.example.satocup.model.enumuration.UserRole;
import com.example.satocup.model.security.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
/*
import org.springframework.security.core.GrantedAuthority;


 */
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Clients")
@Inheritance
public class Client extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientId")
    private Long clientId;

    @Column(name = "firstName",nullable = false)
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be less than or equal to 50 characters")
    protected String firstName;

    @Column(name = "lastName",nullable = false)
    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be less than or equal to 50 characters")
    protected String lastName;

    @Column(name = "dateOfBirth",nullable = false)
    @NotBlank(message = "Date of birth is required")
    protected String dateOfBirth;

    @Column(name = "phoneNumber",unique = true,nullable = false)
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    protected String phoneNumber;

    @Column(name = "address",nullable = false)
    @NotBlank(message = "Address is required")
    @Size(max = 100, message = "Address must be less than or equal to 100 characters")
    protected String address;

    @Column(name = "money")
    private double money;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;

    @OneToMany
    private List<Token> tokens;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_CLIENT"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
