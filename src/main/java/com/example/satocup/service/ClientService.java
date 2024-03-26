// ClientService.java
package com.example.satocup.service;

import com.example.satocup.model.dto.ClientDTO;
import com.example.satocup.model.entity.Client;
import com.example.satocup.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<ClientDTO> getAllClients();
    ClientDTO getClientById(Long clientId);
    ClientDTO updateClient(Long clientId, ClientDTO clientDTO);
    void deleteClient(Long clientId);

}
