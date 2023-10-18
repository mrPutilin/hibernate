package ru.putilin.hibernate.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.putilin.hibernate.entity.Person;
import ru.putilin.hibernate.entity.Persons;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonsRepository extends JpaRepository<Persons, Person> {

      @Query(value = "select * from Persons where city ilike ?1", nativeQuery = true)
      List<Persons> findPersonsByCity(String city);

      @Query(value = "select a from Persons a where a.person.age < ?1")
      List<Persons> findByPerson_AgeLessThan(int person_age, Sort sort);

      @Query("select a from Persons a where a.person.name ilike ?1 and a.person.surname ilike ?2")
      Optional<Persons> findByPerson_NameAndPerson_Surname(String name, String surname);

}
