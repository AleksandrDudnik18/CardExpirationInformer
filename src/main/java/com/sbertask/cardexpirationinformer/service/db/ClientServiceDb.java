package com.sbertask.cardexpirationinformer.service.db;

import com.sbertask.cardexpirationinformer.models.Client;
import com.sbertask.cardexpirationinformer.repositories.ClientRepository;
import com.sbertask.cardexpirationinformer.repositories.CreditCardRepository;
import com.sbertask.cardexpirationinformer.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ClientServiceDb implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client createClient(String surname, String name, String patronymic, LocalDate birthday, String email) {
        Client client = clientRepository.findFirstBySurnameAndNameAndPatronymicAndBirthday(surname, name, patronymic, birthday);

        if (client != null)
            return client;

        return clientRepository.save(new Client(0L, surname, name, patronymic, birthday, email));
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findClientById(Long id) {
        return clientRepository.findById(id).get();
    }
}
