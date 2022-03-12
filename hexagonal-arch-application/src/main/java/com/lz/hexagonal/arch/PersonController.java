package com.lz.hexagonal.arch;

import com.lz.hexagonal.arch.jpa.IPersonRepository;
import com.lz.hexagonal.arch.jpa.PersonEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@RestController
public class PersonController {

    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private IPersonRepository repository;

    @GetMapping("/persons")
    public List<PersonEntity> list() {
        LOG.info("listando EM", kv("teste", "test"));
        return repository.findAll();
    }

    @PostMapping("/persons")
    PersonEntity newEmployee(@RequestBody PersonEntity personEntity) {
        return repository.save(personEntity);
    }
}
