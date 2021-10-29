package com.sbertask.cardexpirationinformer;

import com.sbertask.cardexpirationinformer.service.ClientService;
import com.sbertask.cardexpirationinformer.service.CreditCardService;
import com.sbertask.cardexpirationinformer.service.ExpireCreditCardService;
import com.sbertask.cardexpirationinformer.service.qualifiers.ClientQualifier;
import com.sbertask.cardexpirationinformer.service.qualifiers.CreditCardQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class CardExpirationInformerApplication implements CommandLineRunner {

    @Autowired
    private ExpireCreditCardService expireCreditCardService;

    public static void main(String[] args) {
        SpringApplication.run(CardExpirationInformerApplication.class, args);
    }

    @Autowired
    @ClientQualifier
    private ClientService clientService;
    @Autowired
    @CreditCardQualifier
    private CreditCardService creditCardService;


    @EventListener(ApplicationReadyEvent.class)
    public void applicationReady() {


//        System.out.println("START applicationReady()");
//
//        LocalDate ldt = LocalDate.now();
//        System.err.println(ldt);
//
//        System.err.println(LocalDate.now());



//        expireCreditCardService.start();

//        Client client = clientService.findClientById(5L);
//
//        creditCardService.save(creditCardService.createCreditCard(client, LocalDate.now().minusDays(1L)));

//        List<CreditCard> creditCards = Arrays.asList(
//                creditCardService.createCreditCard(client,
//                        new GregorianCalendar(2021, Calendar.MARCH, 5).getTime()),
//                creditCardService.createCreditCard(client,
//                        new GregorianCalendar(2020, Calendar.JANUARY, 5).getTime()),
//                creditCardService.createCreditCard(client,
//                        new GregorianCalendar(2021, Calendar.OCTOBER, 1).getTime())
//
//        );

//        List<CreditCard> creditCardList = creditCardService.saveAll(creditCards);


    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("RUN");

//        expireCreditCardService.start();

        System.out.println("AFTER SCAN DB");



    }
}
