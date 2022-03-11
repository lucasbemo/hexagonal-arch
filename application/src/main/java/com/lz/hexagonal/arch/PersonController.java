package com.lz.hexagonal.arch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private IPersonRepository repository;

    @GetMapping("/persons")
    public List<PersonEntity> list() {
        return repository.findAll();
    }

    @PostMapping("/persons")
    PersonEntity newEmployee(@RequestBody PersonEntity personEntity) {
        return repository.save(personEntity);
    }
}
