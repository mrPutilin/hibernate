package ru.putilin.hibernate.controller;

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


    @GetMapping()
    public List<Persons> getPersonByCity(@RequestParam("city") String city) {
        return myRepository.getPersonByCity(city);
    }

    @GetMapping("/age")
    public List<Persons> getPersonsByAge(@RequestParam("age") int age ) {
        return myRepository.getPersonsByAge(age);
    }

    @GetMapping("/name")
    public Persons getPersonsByNameAndSurname(@RequestParam("name") String name,
                                                        @RequestParam("surname") String surname) {
        return myRepository.getPersonsByNameAndSurname(name, surname).
                orElseThrow();
    }

}
