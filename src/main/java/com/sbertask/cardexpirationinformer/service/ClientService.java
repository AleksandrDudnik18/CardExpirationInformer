package com.sbertask.cardexpirationinformer.service;

import com.sbertask.cardexpirationinformer.models.Client;

import java.time.LocalDate;
import java.util.List;

public interface ClientService {

    Client createClient(String surname, String name, String patronymic, LocalDate birthday, String email);

    Client save(Client client);

    List<Client> findAll();

    Client findClientById(Long id);


}
