package com.sbertask.cardexpirationinformer.repositories;

import com.sbertask.cardexpirationinformer.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findAllBySurnameAndNameAndPatronymicAndBirthday(String surname, String name, String patronymic, LocalDate birthday);

    Client findFirstBySurnameAndNameAndPatronymicAndBirthday(String surname, String name, String patronymic, LocalDate birthday);
}
