package com.sbertask.cardexpirationinformer.service;

import com.sbertask.cardexpirationinformer.models.CreditCard;

import java.util.List;
import java.util.concurrent.Future;

public interface ExpireCreditCardService {

    Future<List<CreditCard>> checkExpireCreditCard();
    void start();

}
