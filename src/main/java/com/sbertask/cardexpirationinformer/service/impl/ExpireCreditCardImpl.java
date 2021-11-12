package com.sbertask.cardexpirationinformer.service.impl;

import com.sbertask.cardexpirationinformer.models.CreditCard;
import com.sbertask.cardexpirationinformer.service.CreditCardService;
import com.sbertask.cardexpirationinformer.service.ExpireCreditCardService;
import com.sbertask.cardexpirationinformer.service.MailingService;
import com.sbertask.cardexpirationinformer.service.qualifiers.CreditCardQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class ExpireCreditCardImpl implements ExpireCreditCardService {

    @Autowired
    @CreditCardQualifier
    private CreditCardService creditCardService;

    @Autowired
    private MailingService mailingService;

    @Override
    @Async
    public Future<List<CreditCard>> checkExpireCreditCard() {

        List<CreditCard> creditCards = creditCardService.findExpiredCreditCards();

        creditCards.forEach(creditCard -> creditCard.setStatusCard(CreditCard.StatusCreditCard.CANCELED));
        creditCardService.saveAll(creditCards);

        return new AsyncResult<>(creditCards);

    }

    @Override
    @Scheduled(cron = "*/30 * * * * *", zone = "Europe/Moscow")
//    @Scheduled(cron = "0 0 0 * * *", zone = "Europe/Moscow")
    @Async
    public void start() {

        try {

            Future<List<CreditCard>> scanDB = checkExpireCreditCard();

            while (!scanDB.isDone()) {
                Thread.sleep(10);
            }

            List<CreditCard> creditCardsClosed = scanDB.get();
            creditCardsClosed.forEach(System.err::println);

            creditCardsClosed.forEach(oldCreditCard -> {
                CreditCard newCreditCard = creditCardService.save(creditCardService.createCreditCard(oldCreditCard.getClient(), LocalDate.now().plusYears(4L)));

                try {
                    mailingService.sendMail(oldCreditCard.getClient().getEmail(), oldCreditCard.getNumber(), newCreditCard.getNumber());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
