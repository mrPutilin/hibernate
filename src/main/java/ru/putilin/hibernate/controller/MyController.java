package ru.putilin.hibernate.controller;

import org.springframework.web.bind.annotation.*;
import ru.putilin.hibernate.entity.Persons;
import ru.putilin.hibernate.service.MyService;

import java.util.List;
import java.util.Optional;

@RequestMapping("/persons/by-city")
@RestController
public class MyController {

    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }


    @GetMapping()
    public List<Persons> getPersonByCity(@RequestParam("city") String city) {
        return myService.getPersonByCity(city);
    }

    @GetMapping("/age")
    public List<Persons> getPersonsByAge(@RequestParam("age") int age) {
        return myService.getPersonsByAge(age);
    }

    @GetMapping("/name")
    public Optional<Persons> getPersonsByNameAndSurname(@RequestParam("name") String name,
                                                        @RequestParam("surname") String surname) {
        return Optional.of(myService.getPersonsByNameAndSurname(name, surname).
                orElseThrow());
    }

}
