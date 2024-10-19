package com.lz.hexagonal.arch.application.person.adapters.in.web;

import com.lz.hexagonal.arch.application.person.adapters.in.web.dtos.CreatePersonRequestWeb;
import com.lz.hexagonal.arch.application.person.usecases.ICreatePersonUseCase;
import com.lz.hexagonal.arch.application.person.usecases.IListPersonUseCase;
import com.lz.hexagonal.arch.domain.person.dtos.ListPageablePersonDTO;
import com.lz.hexagonal.arch.domain.person.dtos.ListPersonDTO;
import com.lz.hexagonal.arch.domain.person.models.Person;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
public record PersonController(ICreatePersonUseCase createPersonUseCase, IListPersonUseCase listPersonsUseCase) {

    static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody @Valid CreatePersonRequestWeb createPersonRequestWeb)
            throws Exception {
        logger.info("person_creating {}", createPersonRequestWeb);

        Person personCreated =  createPersonUseCase.execute(createPersonRequestWeb.toPerson());

        logger.info("person_created {}", personCreated);
        return new ResponseEntity<>(personCreated, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListPageablePersonDTO> list(@RequestParam(defaultValue = "0") int page
            , @RequestParam(defaultValue = "5") int size, @RequestParam(required = false) final String name
            , @RequestParam(required = false) final String email, @RequestParam(defaultValue = "id") String sort) {
        logger.info("person_listing {} {} {} {} {}", page, size, name, email, sort);

        ListPageablePersonDTO response = listPersonsUseCase
                .execute(new ListPersonDTO(page, size, sort, name, email));

        logger.info("person_listed {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
