package com.lz.hexagonal.arch.domain.person.models;

import com.lz.hexagonal.arch.domain.infra.PersonCsvLoader;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void when_createPerson_expect_success() {
        var person = new Person(
                people.get(0).id(), people.get(0).name(), people.get(0).email(), people.get(0).cpf()
                , people.get(0).phone(), people.get(0).birthDate(), people.get(0).createAt());

        assertEquals(person.id(),  people.get(0).id());
        assertEquals(person.name(), people.get(0).name());
        assertEquals(person.email(), people.get(0).email());
        assertEquals(person.cpf(), people.get(0).cpf());
        assertEquals(person.phone(), people.get(0).phone());
        assertEquals(person.birthDate(), people.get(0).birthDate());
        assertEquals(person.createAt(), people.get(0).createAt());
    }

    @Test
    public void when_createPersonWithoutId_expect_inputValuesIsSameAttributes() {
        var person = new Person(
                null, people.get(0).name(), people.get(0).email(), people.get(0).cpf()
                , people.get(0).phone(), people.get(0).birthDate(), people.get(0).createAt());

        assertNull(person.id());
        assertEquals(people.get(0).name(), person.name());
        assertEquals(people.get(0).email(), person.email());
        assertEquals(people.get(0).cpf(), person.cpf());
        assertEquals(people.get(0).phone(), person.phone());
        assertEquals(people.get(0).birthDate(), person.birthDate());
        assertEquals(people.get(0).createAt(), person.createAt());
    }

    @Test
    @DisplayName("1 + 1 = 2")
    void addsTwoNumbers() {
        assertEquals(2, 2, "1 + 1 should equal 2");
    }

    //    @Test
    public void when_createPersonWithoutName_expect_nameViolation() {
        var person = new Person(
                null, null, people.get(0).email(), people.get(0).cpf()
                , people.get(0).phone(), people.get(0).birthDate(), people.get(0).createAt());
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        assertFalse(violations.isEmpty());
        assertEquals("name must not be null or blank", violations.iterator().next().getMessage());

        assertNull(person.id());
        assertNull(person.name());
        assertEquals(people.get(0).email(), person.email());
        assertEquals(people.get(0).cpf(), person.cpf());
        assertEquals(people.get(0).phone(), person.phone());
        assertEquals(people.get(0).birthDate(), person.birthDate());
        assertEquals(people.get(0).createAt(), person.createAt());
    }
    //    @Test
    public void when_createPersonWithBlankName_expect_nameViolation() {
        var person = new Person(
                null, "", people.get(0).email(), people.get(0).cpf()
                , people.get(0).phone(), people.get(0).birthDate(), people.get(0).createAt());
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        assertFalse(violations.isEmpty());
        assertEquals("name must not be null or blank", violations.iterator().next().getMessage());

        assertNull(person.id());
        assertEquals("", person.name());
        assertEquals(people.get(0).email(), person.email());
        assertEquals(people.get(0).cpf(), person.cpf());
        assertEquals(people.get(0).phone(), person.phone());
        assertEquals(people.get(0).birthDate(), person.birthDate());
        assertEquals(people.get(0).createAt(), person.createAt());
    }

    @Test
    public void when_createPersonWithoutEmail_expect_nameViolation() {
        var person = new Person(
                null, people.get(0).name(), null, people.get(0).cpf()
                , people.get(0).phone(), people.get(0).birthDate(), people.get(0).createAt());
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        assertFalse(violations.isEmpty());
        assertEquals("email must not be null or blank", violations.iterator().next().getMessage());

        assertNull(person.id());
        assertEquals(people.get(0).name(), person.name());
        assertNull(person.email());
        assertEquals(people.get(0).cpf(), person.cpf());
        assertEquals(people.get(0).phone(), person.phone());
        assertEquals(people.get(0).birthDate(), person.birthDate());
        assertEquals(people.get(0).createAt(), person.createAt());
    }
    @Test
    public void when_createPersonWithBlankEmail_expect_nameViolation() {
        var person = new Person(
                null, people.get(0).name(), "", people.get(0).cpf()
                , people.get(0).phone(), people.get(0).birthDate(), people.get(0).createAt());
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        assertFalse(violations.isEmpty());
        assertEquals("email must not be null or blank", violations.iterator().next().getMessage());

        assertNull(person.id());
        assertEquals(people.get(0).name(), person.name());
        assertEquals("", person.email());
        assertEquals(people.get(0).cpf(), person.cpf());
        assertEquals(people.get(0).phone(), person.phone());
        assertEquals(people.get(0).birthDate(), person.birthDate());
        assertEquals(people.get(0).createAt(), person.createAt());
    }

    @Test
    public void when_createPersonWithoutCpf_expect_nameViolation() {
        var person = new Person(
                null, people.get(0).name(), people.get(0).email(), null
                , people.get(0).phone(), people.get(0).birthDate(), people.get(0).createAt());
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        assertFalse(violations.isEmpty());
        assertEquals("cpf must not be null or blank", violations.iterator().next().getMessage());

        assertNull(person.id());
        assertEquals(people.get(0).name(), person.name());
        assertEquals(people.get(0).email(), person.email());
        assertEquals(null, person.cpf());
        assertEquals(people.get(0).phone(), person.phone());
        assertEquals(people.get(0).birthDate(), person.birthDate());
        assertEquals(people.get(0).createAt(), person.createAt());
    }
    @Test
    public void when_createPersonWithBlankCpf_expect_nameViolation() {
        var person = new Person(
                null, people.get(0).name(), people.get(0).email(), ""
                , people.get(0).phone(), people.get(0).birthDate(), people.get(0).createAt());
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        assertFalse(violations.isEmpty());
        assertEquals("cpf must not be null or blank", violations.iterator().next().getMessage());

        assertNull(person.id());
        assertEquals(people.get(0).name(), person.name());
        assertEquals(people.get(0).email(), person.email());
        assertEquals("", person.cpf());
        assertEquals(people.get(0).phone(), person.phone());
        assertEquals(people.get(0).birthDate(), person.birthDate());
        assertEquals(people.get(0).createAt(), person.createAt());
    }

    @Test
    public void when_createPersonWithoutPhone_expect_nameViolation() {
        var person = new Person(
                null, people.get(0).name(), people.get(0).email(), people.get(0).cpf()
                , null, people.get(0).birthDate(), people.get(0).createAt());
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        assertFalse(violations.isEmpty());
        assertEquals("phone must not be null or blank", violations.iterator().next().getMessage());

        assertNull(person.id());
        assertEquals(people.get(0).name(), person.name());
        assertEquals(people.get(0).email(), person.email());
        assertEquals(people.get(0).cpf(), person.cpf());
        assertEquals(null, person.phone());
        assertEquals(people.get(0).birthDate(), person.birthDate());
        assertEquals(people.get(0).createAt(), person.createAt());
    }
    @Test
    public void when_createPersonWithBlankPhone_expect_nameViolation() {
        var person = new Person(
                null, people.get(0).name(), people.get(0).email(), people.get(0).cpf()
                , "", people.get(0).birthDate(), people.get(0).createAt());
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        assertFalse(violations.isEmpty());
        assertEquals("phone must not be null or blank", violations.iterator().next().getMessage());

        assertNull(person.id());
        assertEquals(people.get(0).name(), person.name());
        assertEquals(people.get(0).email(), person.email());
        assertEquals(people.get(0).cpf(), person.cpf());
        assertEquals("", person.phone());
        assertEquals(people.get(0).birthDate(), person.birthDate());
        assertEquals(people.get(0).createAt(), person.createAt());
    }

    @Test
    public void when_createPersonWithoutPhoneBirthDate_expect_nameViolation() {
        var person = new Person(
                null, people.get(0).name(), people.get(0).email(), people.get(0).cpf()
                , people.get(0).phone(), null, people.get(0).createAt());
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        assertFalse(violations.isEmpty());
        assertEquals("birthDate must not be null or blank", violations.iterator().next().getMessage());

        assertNull(person.id());
        assertEquals(people.get(0).name(), person.name());
        assertEquals(people.get(0).email(), person.email());
        assertEquals(people.get(0).cpf(), person.cpf());
        assertEquals(people.get(0).phone(), person.phone());
        assertEquals(null, person.birthDate());
        assertEquals(people.get(0).createAt(), person.createAt());
    }
}