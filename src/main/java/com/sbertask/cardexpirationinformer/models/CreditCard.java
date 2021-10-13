package com.sbertask.cardexpirationinformer.models;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

//lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
//jpa
@Entity
@Table(name="creditcard")
public class CreditCard {

    public enum StatusCreditCard {
        CANCELED,
        ACTIVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Client client;
    @Column(length = 16, nullable = false, unique = true)
    private String number;
    @Column(nullable = false)
    private LocalDate issueDate;
    @Column(nullable = false)
    private LocalDate expirationDate;
    @Column(nullable = false)
    private StatusCreditCard statusCard;

}
