package ru.putilin.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.putilin.hibernate.entity.Persons;

import java.util.List;

@Repository
public class MyRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public MyRepository() {
    }

    public List<Persons> getPersonByCity(String city) {

        String query = "SELECT persons FROM Persons persons where lower(persons.city) ilike ?1";

        List<Persons> list = entityManager.createQuery(query, Persons.class)
                .setParameter(1, city)
                .getResultList();
        return list;
    }

}
