package com.sbertask.cardexpirationinformer.service.json;

import com.sbertask.cardexpirationinformer.models.Client;
import com.sbertask.cardexpirationinformer.models.CreditCard;
import com.sbertask.cardexpirationinformer.service.CreditCardService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class CreditCardServiceJson implements CreditCardService {
    @Override
    public String generateNumberCreditCard() {
        return null;
    }

    @Override
    public CreditCard createCreditCard(Client client, LocalDate expirationDate) {
        return null;
    }

    @Override
    public void closeCreditCard(CreditCard creditCard) {

    }

    @Override
    public List<CreditCard> saveAll(List<CreditCard> creditCards) {
        return null;
    }

    @Override
    public List<CreditCard> findExpiredCreditCards() {
        return null;
    }

    @Override
    public CreditCard save(CreditCard creditCard) {
        return null;
    }

    @Override
    public Set<CreditCard> findAllByClient_Id(Long id) {
        return null;
    }
}
