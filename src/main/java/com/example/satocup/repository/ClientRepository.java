package com.example.satocup.repository;

import com.example.satocup.model.entity.Admin;
import com.example.satocup.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUsername(String username);
}
