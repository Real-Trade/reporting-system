package com.realtrade.reportingsystem.services;

import com.realtrade.reportingsystem.models.Client;
import com.realtrade.reportingsystem.repository.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private ClientDao clientDao;

    @Autowired
    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public List<Client> getAllClients() {
        return clientDao.findAll();
    }

    public Optional<Client> getClientById(int clientId) {
        return clientDao.findById(clientId);
    }

    public List<Client> getAllActiveClients() {
        return clientDao.getAllActiveClients();
    }

    public Client createClient(Client client) {
        Optional<Client> clientOptional = clientDao.findClientByEmail(client.getEmail());
        if(clientOptional.isPresent()) {
            throw new IllegalStateException("Client already exists");
        } else {
            Client newClient = new Client();
            newClient.setFirstName(client.getFirstName());
            newClient.setLastName(client.getLastName());
            newClient.setEmail(client.getEmail());
            newClient.setPassword(client.getPassword());
            newClient.setStatus(client.getStatus());
            clientDao.save(client);
        }
        return client;
    }

    public Optional<Client> updateClient(int clientId, Client updateClient) {
        Optional<Client> existingClient = clientDao.findById(clientId);
        if(existingClient.isPresent()) {
            Client client = existingClient.get();
            if(updateClient.getFirstName() != null) {
                client.setFirstName(updateClient.getFirstName());
            }
            if(updateClient.getLastName() != null) {
                client.setLastName(updateClient.getLastName());
            }
            if(updateClient.getEmail() != null) {
                client.setEmail(updateClient.getEmail());
            }
            client.setUpdateAt(OffsetDateTime.now());
            clientDao.save(client);
            return getClientById(clientId);
        }
        return existingClient;
    }

    @Transactional
    public boolean deleteClient(int clientId) {
        Optional<Client> client = getClientById(clientId);
        if(client.isPresent()) {
            client.get().setStatus(0);
            clientDao.save(client.get());
            return true;
        }
        return false;
    }
}
