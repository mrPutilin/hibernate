package ru.putilin.hibernate.controller;

import org.springframework.web.bind.annotation.*;
import ru.putilin.hibernate.entity.Persons;
import ru.putilin.hibernate.repository.MyRepository;

import java.util.List;

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
}
