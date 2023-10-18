package ru.putilin.hibernate.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.putilin.hibernate.dao.PersonsRepository;
import ru.putilin.hibernate.entity.Persons;

import java.util.List;
import java.util.Optional;

@Repository
public class MyService {


    private final PersonsRepository personsRepository;


    public MyService(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }


    public List<Persons> getPersonByCity(String city) {
        return personsRepository.findPersonsByCity(city);
    }

    public List<Persons> getPersonsByAge(int age) {
        return personsRepository.findByPerson_AgeLessThan(age, Sort.by("person.age"));

    }

    public Optional<Persons> getPersonsByNameAndSurname(String name, String surname) {
        return personsRepository.findByPerson_NameAndPerson_Surname(name, surname);

    }


}
