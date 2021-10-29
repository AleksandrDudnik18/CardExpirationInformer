package com.sbertask.cardexpirationinformer.controllers;


import com.sbertask.cardexpirationinformer.models.Client;
import com.sbertask.cardexpirationinformer.service.ClientService;
import com.sbertask.cardexpirationinformer.service.qualifiers.ClientQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    @ClientQualifier
    private ClientService clientService;

    @GetMapping("main")
    public String main(ModelMap model) {
        List<Client> clients = clientService.findAll();
        model.put("clients", clients);
        return "main";
    }

    @PostMapping("addClient")
    public String addClient(@RequestParam String surname,
                            @RequestParam String name,
                            @RequestParam String patronymic,
                            @RequestParam String birthday,
                            @RequestParam String email, ModelMap model) {
        LocalDate dateBirthday = LocalDate.parse(birthday);
        Client client = new Client(0L, surname, name, patronymic, dateBirthday, email);
        clientService.save(client);
        return "redirect:main";
    }

    @PostMapping("tocreditcards")
    public ModelAndView toCreditCards(@RequestParam String id, ModelMap model) {
        model.put("id", id);
        return new ModelAndView("redirect:creditcards", model);
    }
}
