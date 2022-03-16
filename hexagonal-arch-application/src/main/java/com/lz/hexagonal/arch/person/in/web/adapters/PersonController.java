package com.lz.hexagonal.arch.person.in.web.adapters;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.usecases.ICreatePersonUseCase;
import com.lz.hexagonal.arch.domain.person.usecases.IListPersonUseCase;
import com.lz.hexagonal.arch.domain.person.usecases.commands.CreatePersonCommand;
import com.lz.hexagonal.arch.domain.person.usecases.commands.ListPersonCommand;
import com.lz.hexagonal.arch.domain.person.usecases.response.ListPageablePersonResponse;
import com.lz.hexagonal.arch.person.in.web.adapters.dtos.CreatePersonRequestWeb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.kv;

@RestController
@RequestMapping("/persons")
@Slf4j
public record PersonController(ICreatePersonUseCase createPersonUseCase, IListPersonUseCase listPersonsUseCase) {

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody @Valid CreatePersonRequestWeb person) {
        log.info("person_creating", kv("person", person));

        CreatePersonCommand createPersonCommand = CreatePersonCommand.builder()
                .person(person.toPerson())
                .build();

        Person personCreated =  createPersonUseCase.execute(createPersonCommand);

        log.info("person_created", kv("person", personCreated));
        return new ResponseEntity<Person>(personCreated, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListPageablePersonResponse> list(@RequestParam(defaultValue = "0") int page
            , @RequestParam(defaultValue = "5") int size, @RequestParam(required = false) String name
            , @RequestParam(required = false) String email, @RequestParam(defaultValue = "id") String sort) {
        Map<String, String> filters = null;
        log.info("person_listing");

        if (name != null || email != null) {
            filters = new HashMap<>();
            if (name != null) filters.put("name", name);
            if (email != null) filters.put("email", email);
        }

        ListPageablePersonResponse response = listPersonsUseCase.execute(new ListPersonCommand(page,size,sort,filters));

        log.info("person_listed", kv("response", response));
        return new ResponseEntity<ListPageablePersonResponse>(response, HttpStatus.ACCEPTED);
    }
}