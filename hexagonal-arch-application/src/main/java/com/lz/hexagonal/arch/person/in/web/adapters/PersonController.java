package com.lz.hexagonal.arch.person.in.web.adapters;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.usecases.ICreatePersonUseCase;
import com.lz.hexagonal.arch.domain.person.usecases.IListPersonUseCase;
import com.lz.hexagonal.arch.domain.person.usecases.commands.ListPersonCommand;
import com.lz.hexagonal.arch.domain.person.usecases.response.ListPageablePersonResponse;
import com.lz.hexagonal.arch.person.in.web.adapters.dtos.CreatePersonRequestWeb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@RestController
@RequestMapping("/persons")
public record PersonController(ICreatePersonUseCase createPersonUseCase, IListPersonUseCase listPersonsUseCase) {

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody @Valid CreatePersonRequestWeb createPersonRequestWeb) {
        log.info("person_creating", kv("person", createPersonRequestWeb));

        Person personCreated =  createPersonUseCase.execute(createPersonRequestWeb.toPerson());

        log.info("person_created", kv("person", personCreated));
        return new ResponseEntity<>(personCreated, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListPageablePersonResponse> list(@RequestParam(defaultValue = "0") int page
            , @RequestParam(defaultValue = "5") int size, @RequestParam(required = false) final String name
            , @RequestParam(required = false) final String email, @RequestParam(defaultValue = "id") String sort) {
        log.info("person_listing"
                , kv("page", page), kv("size", size), kv("name", name), kv("email", email), kv("sort", sort));

        ListPageablePersonResponse response = listPersonsUseCase
                .execute(new ListPersonCommand(page, size, sort, name, email));

        log.info("person_listed", kv("response", response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
