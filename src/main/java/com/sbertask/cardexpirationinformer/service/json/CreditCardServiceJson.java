package com.sbertask.cardexpirationinformer.service.json;

import com.google.gson.reflect.TypeToken;
import com.sbertask.cardexpirationinformer.models.Client;
import com.sbertask.cardexpirationinformer.models.CreditCard;
import com.sbertask.cardexpirationinformer.service.CreditCardService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreditCardServiceJson extends JsonService<CreditCard, Long> implements CreditCardService {

    {
        Type type = new TypeToken<ArrayList<CreditCard>>() {
        }.getType();
        setType(CreditCard.class, type);
    }

    private Optional<CreditCard> findByNumber(String numberCreditCard) {
       return readFileJson().stream().filter(el -> el.getNumber().equals(numberCreditCard)).findFirst();
    }

    @Override
    public CreditCard createCreditCard(Client client, LocalDate expirationDate) {
        String numberCreditCard;

        do {
            numberCreditCard = generateNumberCreditCard();
        } while (findByNumber(numberCreditCard).isPresent());

        return new CreditCard(0L, client, numberCreditCard, LocalDate.now(), expirationDate,
                CreditCard.StatusCreditCard.ACTIVE);
    }

    @Override
    public void closeCreditCard(CreditCard creditCard) {
        creditCard.setStatusCard(CreditCard.StatusCreditCard.CANCELED);
        saveJson(creditCard);
    }

    @Override
    public List<CreditCard> saveAll(List<CreditCard> creditCards) {
        return saveAllJson(creditCards);
    }

    @Override
    public List<CreditCard> findExpiredCreditCards() {

        return readFileJson().stream().filter(el -> el.getExpirationDate().isBefore(LocalDate.now())
                && CreditCard.StatusCreditCard.ACTIVE.equals(el.getStatusCard())).collect(Collectors.toList());
    }

    @Override
    public CreditCard save(CreditCard creditCard) {
        return saveJson(creditCard);
    }

    @Override
    public Set<CreditCard> findAllByClient_Id(Long id) {
        return readFileJson().stream().filter(el -> el.getClient().getId().equals(id)).collect(Collectors.toSet());
    }
}
