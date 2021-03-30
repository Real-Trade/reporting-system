package com.realtrade.reportingsystem.controllers;

import com.realtrade.reportingsystem.models.Account;
import com.realtrade.reportingsystem.models.Client;
import com.realtrade.reportingsystem.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/client")
public class ClientController {
    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping(path = "/active")
    public List<Client> getAllActiveClients() {
        return clientService.getAllActiveClients();
    }

    @GetMapping(path = "/{clientId}")
    public Client getClientById(@PathVariable(name = "clientId") int clientId) {
        return clientService.getClientById(clientId).orElseThrow();
    }

    @PutMapping(path = "/{clientId}/update")
    @CrossOrigin("*")
    public Client updateClient(@PathVariable(name = "clientId")int clientId, @RequestBody Client client) {
        return clientService.updateClient(clientId, client).orElseThrow();
    }

    @DeleteMapping(path = "/{clientId}/delete")
    public boolean deleteClient(@PathVariable(name = "clientId") int clientId) {
        return clientService.deleteClient(clientId);
    }

}
