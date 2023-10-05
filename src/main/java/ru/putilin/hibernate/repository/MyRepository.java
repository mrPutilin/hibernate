package ru.putilin.hibernate.repository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.putilin.hibernate.entity.Persons;

import java.util.List;
import java.util.Optional;

@Repository
public class MyRepository {


    private final PersonsRepository personsRepository;


    public MyRepository(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }

    public List<Persons> getPersonByCity(String city) {

        return personsRepository.findByCityIgnoreCase(city);
    }

    public List<Persons> getPersonsByAge(int age) {
       return personsRepository.findByPerson_AgeLessThan(age, Sort.by("person.age"));

    }

    public Optional<Persons> getPersonsByNameAndSurname(String name, String surname) {
        return personsRepository.findByPerson_NameAndPerson_Surname(name, surname);

    }



}
