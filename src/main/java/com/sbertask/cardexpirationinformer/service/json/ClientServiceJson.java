package com.sbertask.cardexpirationinformer.service.json;

import com.sbertask.cardexpirationinformer.models.Client;
import com.sbertask.cardexpirationinformer.service.ClientService;

import java.time.LocalDate;
import java.util.List;

public class ClientServiceJson implements ClientService {
    @Override
    public Client createClient(String surname, String name, String patronymic, LocalDate birthday, String email) {
        return null;
    }

    @Override
    public void save(Client client) {

    }

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    public Client findClientById(Long id) {
        return null;
    }
}
