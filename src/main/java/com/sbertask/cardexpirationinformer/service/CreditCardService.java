package com.sbertask.cardexpirationinformer.service;

import com.sbertask.cardexpirationinformer.models.Client;
import com.sbertask.cardexpirationinformer.models.CreditCard;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface CreditCardService {

    String generateNumberCreditCard();
    CreditCard createCreditCard(Client client, LocalDate expirationDate);
    void closeCreditCard(CreditCard creditCard);
    List<CreditCard> saveAll(List<CreditCard> creditCards);
    List<CreditCard> findExpiredCreditCards();
    CreditCard save(CreditCard creditCard);
    Set<CreditCard> findAllByClient_Id(Long id);
}
