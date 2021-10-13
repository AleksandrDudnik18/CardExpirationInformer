package com.sbertask.cardexpirationinformer.service;

public interface MailingService {

    void sendMail(final String toAddress, final String oldCreditCardNumber, final String newCreditCardNumber);

}
