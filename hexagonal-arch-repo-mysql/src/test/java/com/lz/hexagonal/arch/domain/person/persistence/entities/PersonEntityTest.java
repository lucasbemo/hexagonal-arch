package com.lz.hexagonal.arch.domain.person.persistence.entities;

import com.lz.hexagonal.arch.domain.person.infra.PersonEntityCsvLoader;
import com.lz.hexagonal.arch.domain.person.models.Person;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class PersonEntityTest {

    static final Logger logger = LoggerFactory.getLogger(PersonEntityTest.class);
    private static List<Person> people;
    private static Validator validator;

    @BeforeAll
    static void beforeAll(){
        logger.debug("@BeforeAll");
        try {
            people = new PersonEntityCsvLoader().loadPersonsFromCsv();
            logger.debug("@BeforeAll - finished");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void beforeEach(){
        logger.debug("@BeforeEach");
        validate();
    }

    public static void validate() {
        ValidatorFactory factory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        validator = factory.usingContext().getValidator();

        //        validator = Validation.buildDefaultValidatorFactory().getValidator();
        System.out.println("Validator: " + validator);
    }
}
