// ClientServiceImpl.java
package com.example.satocup.service.impl;

import com.example.satocup.model.dto.ClientDTO;
import com.example.satocup.model.entity.Client;
import com.example.satocup.repository.ClientRepository;
import com.example.satocup.service.ClientService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ClientDTO> getAllClients() {
        try {
            List<Client> clients = clientRepository.findAll();
            return clients.stream()
                    .map(client -> modelMapper.map(client, ClientDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all clients: " + e.getMessage());
        }
    }

    @Override
    public ClientDTO getClientById(Long clientId) {
        try {
            Client client = clientRepository.findById(clientId)
                    .orElseThrow(() -> new NotFoundException("Client not found with ID: " + clientId));
            return modelMapper.map(client, ClientDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch client with ID " + clientId + ": " + e.getMessage());
        }
    }


    @Override
    public ClientDTO updateClient(Long clientId, ClientDTO clientDTO) {
        try {
            Client existingClient = clientRepository.findById(clientId)
                    .orElseThrow(() -> new NotFoundException("Client not found with ID: " + clientId));
            modelMapper.map(clientDTO, existingClient);
            existingClient.setClientId(clientId);
            existingClient = clientRepository.save(existingClient);
            return modelMapper.map(existingClient, ClientDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update client with ID " + clientId + ": " + e.getMessage());
        }
    }

    @Override
    public void deleteClient(Long clientId) {
        try {
            if (!clientRepository.existsById(clientId)) {
                throw new NotFoundException("Client not found with ID: " + clientId);
            }
            clientRepository.deleteById(clientId);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete client with ID " + clientId);
        }
    }
}


