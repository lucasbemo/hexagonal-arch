package com.lz.hexagonal.arch.domain.person.models;

import com.lz.hexagonal.arch.domain.infra.PersonCsvLoader;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PersonTests {

    static final Logger logger = LoggerFactory.getLogger(PersonTests.class);
    private static List<Person> people;
    private static Validator validator;

    @BeforeAll
    static void beforeAll(){
        logger.debug("@BeforeAll");
        try {
            people = new PersonCsvLoader().loadPersonsFromCsv();
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
//        ValidatorFactory factory = Validation.byDefaultProvider()
//                .configure()
//                .messageInterpolator(new ParameterMessageInterpolator())
//                .buildValidatorFactory();
//        validator = factory.usingContext().getValidator();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
    
    @Test
    public void when_createPerson_expect_success() {
        var person = new Person(
                people.get(0).getId(), people.get(0).getName(), people.get(0).getEmail(), people.get(0).getCpf()
                , people.get(0).getPhone(), people.get(0).getBirthDate(), people.get(0).getCreateAt());

        assertEquals(person.getId(),  people.get(0).getId());
        assertEquals(person.getName(), people.get(0).getName());
        assertEquals(person.getEmail(), people.get(0).getEmail());
        assertEquals(person.getCpf(), people.get(0).getCpf());
        assertEquals(person.getPhone(), people.get(0).getPhone());
        assertEquals(person.getBirthDate(), people.get(0).getBirthDate());
        assertEquals(person.getCreateAt(), people.get(0).getCreateAt());
    }

    @Test
    public void when_createPersonWithoutId_expect_inputValuesIsSameAttributes() {
        var person = new Person(
                null, people.get(0).getName(), people.get(0).getEmail(), people.get(0).getCpf()
                , people.get(0).getPhone(), people.get(0).getBirthDate(), people.get(0).getCreateAt());

        assertEquals(null, person.getId());
        assertEquals(people.get(0).getName(), person.getName());
        assertEquals(people.get(0).getEmail(), person.getEmail());
        assertEquals(people.get(0).getCpf(), person.getCpf());
        assertEquals(people.get(0).getPhone(), person.getPhone());
        assertEquals(people.get(0).getBirthDate(), person.getBirthDate());
        assertEquals(people.get(0).getCreateAt(), person.getCreateAt());
    }

    @Test
    @DisplayName("1 + 1 = 2")
    void addsTwoNumbers() {
        assertEquals(2, 2, "1 + 1 should equal 2");
    }

//    @Test
    public void when_createPersonWithoutName_expect_nameViolation() {
        var person = new Person(
                null, null, people.get(0).getEmail(), people.get(0).getCpf()
                , people.get(0).getPhone(), people.get(0).getBirthDate(), people.get(0).getCreateAt());
        // Validate the person object
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        // Check that there is a violation for the name field
        assertFalse(violations.isEmpty());
        assertEquals("name must not be null or blank", violations.iterator().next().getMessage());

        assertEquals(null, person.getId());
        assertEquals(null, person.getName());
        assertEquals(people.get(0).getEmail(), person.getEmail());
        assertEquals(people.get(0).getCpf(), person.getCpf());
        assertEquals(people.get(0).getPhone(), person.getPhone());
        assertEquals(people.get(0).getBirthDate(), person.getBirthDate());
        assertEquals(people.get(0).getCreateAt(), person.getCreateAt());
    }
//    @Test
    public void when_createPersonWithBlankName_expect_nameViolation() {
        var person = new Person(
                null, "", people.get(0).getEmail(), people.get(0).getCpf()
                , people.get(0).getPhone(), people.get(0).getBirthDate(), people.get(0).getCreateAt());
        // Validate the person object
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        // Check that there is a violation for the name field
        assertFalse(violations.isEmpty());
        assertEquals("name must not be null or blank", violations.iterator().next().getMessage());

        assertEquals(null, person.getId());
        assertEquals("", person.getName());
        assertEquals(people.get(0).getEmail(), person.getEmail());
        assertEquals(people.get(0).getCpf(), person.getCpf());
        assertEquals(people.get(0).getPhone(), person.getPhone());
        assertEquals(people.get(0).getBirthDate(), person.getBirthDate());
        assertEquals(people.get(0).getCreateAt(), person.getCreateAt());
    }

    @Test
    public void when_createPersonWithoutEmail_expect_nameViolation() {
        var person = new Person(
                null, people.get(0).getName(), null, people.get(0).getCpf()
                , people.get(0).getPhone(), people.get(0).getBirthDate(), people.get(0).getCreateAt());
        // Validate the person object
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        // Check that there is a violation for the name field
        assertFalse(violations.isEmpty());
        assertEquals("email must not be null or blank", violations.iterator().next().getMessage());

        assertEquals(null, person.getId());
        assertEquals(people.get(0).getName(), person.getName());
        assertEquals(null, person.getEmail());
        assertEquals(people.get(0).getCpf(), person.getCpf());
        assertEquals(people.get(0).getPhone(), person.getPhone());
        assertEquals(people.get(0).getBirthDate(), person.getBirthDate());
        assertEquals(people.get(0).getCreateAt(), person.getCreateAt());
    }
    @Test
    public void when_createPersonWithBlankEmail_expect_nameViolation() {
        var person = new Person(
                null, people.get(0).getName(), "", people.get(0).getCpf()
                , people.get(0).getPhone(), people.get(0).getBirthDate(), people.get(0).getCreateAt());
        // Validate the person object
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        // Check that there is a violation for the name field
        assertFalse(violations.isEmpty());
        assertEquals("email must not be null or blank", violations.iterator().next().getMessage());

        assertEquals(null, person.getId());
        assertEquals(people.get(0).getName(), person.getName());
        assertEquals("", person.getEmail());
        assertEquals(people.get(0).getCpf(), person.getCpf());
        assertEquals(people.get(0).getPhone(), person.getPhone());
        assertEquals(people.get(0).getBirthDate(), person.getBirthDate());
        assertEquals(people.get(0).getCreateAt(), person.getCreateAt());
    }

    @Test
    public void when_createPersonWithoutCpf_expect_nameViolation() {
        var person = new Person(
                null, people.get(0).getName(), people.get(0).getEmail(), null
                , people.get(0).getPhone(), people.get(0).getBirthDate(), people.get(0).getCreateAt());
        // Validate the person object
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        // Check that there is a violation for the name field
        assertFalse(violations.isEmpty());
        assertEquals("cpf must not be null or blank", violations.iterator().next().getMessage());

        assertEquals(null, person.getId());
        assertEquals(people.get(0).getName(), person.getName());
        assertEquals(people.get(0).getEmail(), person.getEmail());
        assertEquals(null, person.getCpf());
        assertEquals(people.get(0).getPhone(), person.getPhone());
        assertEquals(people.get(0).getBirthDate(), person.getBirthDate());
        assertEquals(people.get(0).getCreateAt(), person.getCreateAt());
    }
    @Test
    public void when_createPersonWithBlankCpf_expect_nameViolation() {
        var person = new Person(
                null, people.get(0).getName(), people.get(0).getEmail(), ""
                , people.get(0).getPhone(), people.get(0).getBirthDate(), people.get(0).getCreateAt());
        // Validate the person object
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        // Check that there is a violation for the name field
        assertFalse(violations.isEmpty());
        assertEquals("cpf must not be null or blank", violations.iterator().next().getMessage());

        assertEquals(null, person.getId());
        assertEquals(people.get(0).getName(), person.getName());
        assertEquals(people.get(0).getEmail(), person.getEmail());
        assertEquals("", person.getCpf());
        assertEquals(people.get(0).getPhone(), person.getPhone());
        assertEquals(people.get(0).getBirthDate(), person.getBirthDate());
        assertEquals(people.get(0).getCreateAt(), person.getCreateAt());
    }

    @Test
    public void when_createPersonWithoutPhone_expect_nameViolation() {
        var person = new Person(
                null, people.get(0).getName(), people.get(0).getEmail(), people.get(0).getCpf()
                , null, people.get(0).getBirthDate(), people.get(0).getCreateAt());
        // Validate the person object
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        // Check that there is a violation for the name field
        assertFalse(violations.isEmpty());
        assertEquals("phone must not be null or blank", violations.iterator().next().getMessage());

        assertEquals(null, person.getId());
        assertEquals(people.get(0).getName(), person.getName());
        assertEquals(people.get(0).getEmail(), person.getEmail());
        assertEquals(people.get(0).getCpf(), person.getCpf());
        assertEquals(null, person.getPhone());
        assertEquals(people.get(0).getBirthDate(), person.getBirthDate());
        assertEquals(people.get(0).getCreateAt(), person.getCreateAt());
    }
    @Test
    public void when_createPersonWithBlankPhone_expect_nameViolation() {
        var person = new Person(
                null, people.get(0).getName(), people.get(0).getEmail(), people.get(0).getCpf()
                , "", people.get(0).getBirthDate(), people.get(0).getCreateAt());
        // Validate the person object
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        // Check that there is a violation for the name field
        assertFalse(violations.isEmpty());
        assertEquals("phone must not be null or blank", violations.iterator().next().getMessage());

        assertEquals(null, person.getId());
        assertEquals(people.get(0).getName(), person.getName());
        assertEquals(people.get(0).getEmail(), person.getEmail());
        assertEquals(people.get(0).getCpf(), person.getCpf());
        assertEquals("", person.getPhone());
        assertEquals(people.get(0).getBirthDate(), person.getBirthDate());
        assertEquals(people.get(0).getCreateAt(), person.getCreateAt());
    }

    @Test
    public void when_createPersonWithoutPhoneBirthDate_expect_nameViolation() {
        var person = new Person(
                null, people.get(0).getName(), people.get(0).getEmail(), people.get(0).getCpf()
                , people.get(0).getPhone(), null, people.get(0).getCreateAt());
        // Validate the person object
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        // Check that there is a violation for the name field
        assertFalse(violations.isEmpty());
        assertEquals("birthDate must not be null or blank", violations.iterator().next().getMessage());

        assertEquals(null, person.getId());
        assertEquals(people.get(0).getName(), person.getName());
        assertEquals(people.get(0).getEmail(), person.getEmail());
        assertEquals(people.get(0).getCpf(), person.getCpf());
        assertEquals(people.get(0).getPhone(), person.getPhone());
        assertEquals(null, person.getBirthDate());
        assertEquals(people.get(0).getCreateAt(), person.getCreateAt());
    }
}
