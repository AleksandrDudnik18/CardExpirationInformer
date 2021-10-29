package com.sbertask.cardexpirationinformer;

import com.sbertask.cardexpirationinformer.service.db.ClientServiceDb;
import com.sbertask.cardexpirationinformer.service.db.CreditCardServiceDb;
import com.sbertask.cardexpirationinformer.service.json.ClientServiceJson;
import com.sbertask.cardexpirationinformer.service.json.CreditCardServiceJson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class CardExpirationInformerApplicationTests {

    @Autowired
    private ClientServiceDb clientServiceDb;

    @Autowired
    private CreditCardServiceDb creditCardServiceDb;

    @Autowired
    private ClientServiceJson clientServiceJson;

    @Autowired
    private CreditCardServiceJson creditCardServiceJson;

    @Test
    void contextLoads() {
        Assert.notNull(clientServiceDb, "clientServiceDb");
        Assert.notNull(creditCardServiceDb, "creditCardServiceDb");
        Assert.notNull(clientServiceJson, "clientServiceJson");
        Assert.notNull(creditCardServiceJson, "creditCardServiceJson");
    }

}
