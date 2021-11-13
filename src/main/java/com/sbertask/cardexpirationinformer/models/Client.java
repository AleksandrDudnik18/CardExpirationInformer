package com.sbertask.cardexpirationinformer.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

//lombok
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
//jpa
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String surname;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String patronymic;
    @Column(nullable = false)
    private LocalDate birthday;
    @Column(nullable = false)
    private String email;

}
