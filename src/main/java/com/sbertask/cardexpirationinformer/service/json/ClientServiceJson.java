package com.sbertask.cardexpirationinformer.service.json;

import com.google.gson.reflect.TypeToken;
import com.sbertask.cardexpirationinformer.models.Client;
import com.sbertask.cardexpirationinformer.service.ClientService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceJson extends JsonService<Client, Long> implements ClientService {

    {
        Type type = new TypeToken<ArrayList<Client>>() {
        }.getType();
        setType(Client.class, type);
    }

    @Override
    public Client createClient(String surname, String name, String patronymic, LocalDate birthday, String email) {

        Optional<Client> val = readFileJson().stream().filter(el -> el.getSurname().equalsIgnoreCase(surname)
                && el.getName().equalsIgnoreCase(name) && el.getPatronymic().equalsIgnoreCase(patronymic)
                && el.getBirthday().equals(birthday)).findFirst();

        return val.orElseGet(() -> saveJson(new Client(0L, surname, name, patronymic, birthday, email)));

    }

    @Override
    public Client save(Client client) {
        return saveJson(client);
    }

    @Override
    public List<Client> findAll() {
        return readFileJson();
    }

    @Override
    public Client findClientById(Long id) {
        return getByIdJson(id);
    }


}
