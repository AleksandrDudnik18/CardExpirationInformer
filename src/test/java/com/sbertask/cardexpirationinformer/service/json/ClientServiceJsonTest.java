package com.sbertask.cardexpirationinformer.service.json;

import com.sbertask.cardexpirationinformer.models.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDate;

@SpringBootTest
class ClientServiceJsonTest {

    @Autowired
    private ClientServiceJson clientServiceJson;

    Client client = new Client(0L, "test", "test", "test", LocalDate.now(), "");

    @Test
    void save() {
        Client saveClient = clientServiceJson.save(client);
        Assert.notNull(saveClient, "save");
    }

    @Test
    void findAll() {
        Client saveClient = clientServiceJson.save(client);
        Assert.notNull(clientServiceJson.findAll(), "findAll");
    }

    @Test
    void findClientById() {
        Client saveClient = clientServiceJson.save(client);
        Assert.notNull(clientServiceJson.findClientById(1L), "findClientById");
    }
}