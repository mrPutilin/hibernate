package ru.putilin.hibernate.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.putilin.hibernate.entity.Person;
import ru.putilin.hibernate.entity.Persons;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonsRepository extends JpaRepository<Persons, Person> {

      List<Persons> findByCityIgnoreCase(String city);

      List<Persons> findByPerson_AgeLessThan(int person_age, Sort sort);

      Optional<Persons> findByPerson_NameAndPerson_Surname(String name, String surname);

}
