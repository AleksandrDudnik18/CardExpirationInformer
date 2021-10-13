package com.sbertask.cardexpirationinformer.controllers;


import com.sbertask.cardexpirationinformer.models.CreditCard;
import com.sbertask.cardexpirationinformer.service.ClientService;
import com.sbertask.cardexpirationinformer.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
public class CreditCardsController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private CreditCardService creditCardService;

    @GetMapping("creditcards")
    public String clientCreditCards(@ModelAttribute("id") Object id, ModelMap model) {
        Long clientId = Long.parseLong(String.valueOf(id));
        model.put("client", clientService.findClientById(clientId));
        model.put("creditcards", creditCardService.findAllByClient_Id(clientId));
        return "creditcards";
    }

    @PostMapping("addCreditCard")
    public ModelAndView addCreditCard(@RequestParam String id, ModelMap model) {
        Long clientId = Long.parseLong(id);
        CreditCard card = creditCardService.createCreditCard(
                clientService.findClientById(clientId), LocalDate.now().plusYears(4L));
        creditCardService.save(card);
        model.put("id", id);
        return new ModelAndView("redirect:creditcards", model);
    }
}
