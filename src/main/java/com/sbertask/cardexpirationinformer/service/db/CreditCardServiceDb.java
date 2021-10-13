package com.sbertask.cardexpirationinformer.service.db;

import com.sbertask.cardexpirationinformer.models.Client;
import com.sbertask.cardexpirationinformer.models.CreditCard;
import com.sbertask.cardexpirationinformer.repositories.ClientRepository;
import com.sbertask.cardexpirationinformer.repositories.CreditCardRepository;
import com.sbertask.cardexpirationinformer.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class CreditCardServiceDb implements CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Override
    public String generateNumberCreditCard() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            stringBuilder.append(random.nextInt(10));
        }

        return stringBuilder.toString();
    }

    @Override
    public CreditCard createCreditCard(Client client, LocalDate expirationDate) {
        String numberCreditCard;

        do {
            numberCreditCard = generateNumberCreditCard();
        } while (creditCardRepository.findByNumber(numberCreditCard) != null);

        return new CreditCard(0L, client, numberCreditCard, LocalDate.now(), expirationDate,
                CreditCard.StatusCreditCard.ACTIVE);
    }

    @Override
    public void closeCreditCard(CreditCard creditCard) {
        creditCard.setStatusCard(CreditCard.StatusCreditCard.CANCELED);
        creditCardRepository.save(creditCard);
    }

    @Override
    public List<CreditCard> saveAll(List<CreditCard> creditCards) {
        return creditCardRepository.saveAll(creditCards);
    }

    @Override
    public List<CreditCard> findExpiredCreditCards() {
        return creditCardRepository.queryCreditCardsByExpirationDateBeforeAndStatusCard(
                LocalDate.now(), CreditCard.StatusCreditCard.ACTIVE);
    }

    @Override
    public CreditCard save(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    @Override
    public Set<CreditCard> findAllByClient_Id(Long id) {
        return creditCardRepository.findAllByClient_Id(id);
    }
}
