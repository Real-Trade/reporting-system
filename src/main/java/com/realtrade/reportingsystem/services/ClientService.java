package com.realtrade.reportingsystem.services;

import com.realtrade.reportingsystem.models.Client;
import com.realtrade.reportingsystem.repository.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        clientDao.save(client);
        return client;
    }

    public Client updateClient(Client client) {
        clientDao.save(client);
        return client;
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
