package ru.putilin.hibernate.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.putilin.hibernate.entity.Persons;
import ru.putilin.hibernate.repository.MyRepository;

import java.util.List;
import java.util.Optional;

@RequestMapping("/persons/by-city")
@RestController
public class MyController {

    private final MyRepository myRepository;

    public MyController(MyRepository myRepository) {
        this.myRepository = myRepository;
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/city")
    public List<Persons> getPersonByCity(@RequestParam("city") String city) {
        return myRepository.getPersonByCity(city);
    }

    @Secured("ROLE_USER")
    @GetMapping("/age")
    public List<Persons> getPersonsByAge(@RequestParam("age") int age) {
        return myRepository.getPersonsByAge(age);
    }

    @PreAuthorize("hasRole ('ROLE_USER') OR hasRole ('ROLE_VIEWER')")
    @GetMapping("/name")
    public Optional<Persons> getPersonsByNameAndSurname(@RequestParam("name") String name,
                                                        @RequestParam("surname") String surname) {
        return Optional.of(myRepository.getPersonsByNameAndSurname(name, surname).
                orElseThrow());
    }

    @PostAuthorize("returnObject.person.name == authentication.principal.username ")
    @GetMapping("/write")
    public Persons getPersonsByName(@RequestParam("name") String username) {
        return myRepository.getPersonsByName(username);
    }

}
