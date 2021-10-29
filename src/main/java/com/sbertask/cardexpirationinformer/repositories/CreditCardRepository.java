package com.sbertask.cardexpirationinformer.repositories;

import com.sbertask.cardexpirationinformer.models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    Set<CreditCard> findAllByClient_Id(Long id);
    CreditCard findByNumber(String number);
    List<CreditCard> queryCreditCardsByExpirationDateBeforeAndStatusCard(LocalDate date, CreditCard.StatusCreditCard statusCreditCard);

}
