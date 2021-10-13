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
@Table(name="clients")
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

//    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
//    private List<CreditCard> creditCards;



//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Client client = (Client) o;
//        return Objects.equals(id, client.id) && Objects.equals(surname, client.surname) && Objects.equals(name, client.name) && Objects.equals(patronymic, client.patronymic) && Objects.equals(birthday, client.birthday);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, surname, name, patronymic, birthday);
//    }
}
