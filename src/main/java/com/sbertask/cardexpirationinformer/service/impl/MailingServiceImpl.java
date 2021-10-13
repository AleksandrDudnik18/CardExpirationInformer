package com.sbertask.cardexpirationinformer.service.impl;

import com.sbertask.cardexpirationinformer.service.MailingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailingServiceImpl implements MailingService {

    private static final String SUBJECT = "Change credit card";
    private static final String MESSAGE_TEMPLATE = "Your credit card with number: %s was changed to new credit card with number: %s";

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public void sendMail(final String toAddress, final String oldCreditCardNumber, final String newCreditCardNumber) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(SUBJECT);
        simpleMailMessage.setText(String.format(MESSAGE_TEMPLATE, oldCreditCardNumber, newCreditCardNumber));
        emailSender.send(simpleMailMessage);

    }




}
