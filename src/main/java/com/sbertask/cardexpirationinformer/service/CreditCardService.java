package com.sbertask.cardexpirationinformer.service;

import com.sbertask.cardexpirationinformer.models.Client;
import com.sbertask.cardexpirationinformer.models.CreditCard;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

public interface CreditCardService {

    CreditCard createCreditCard(Client client, LocalDate expirationDate);

    void closeCreditCard(CreditCard creditCard);

    List<CreditCard> saveAll(List<CreditCard> creditCards);

    List<CreditCard> findExpiredCreditCards();

    CreditCard save(CreditCard creditCard);

    Set<CreditCard> findAllByClient_Id(Long id);

    default String generateNumberCreditCard() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            stringBuilder.append(random.nextInt(10));
        }

        return stringBuilder.toString();
    }

}
