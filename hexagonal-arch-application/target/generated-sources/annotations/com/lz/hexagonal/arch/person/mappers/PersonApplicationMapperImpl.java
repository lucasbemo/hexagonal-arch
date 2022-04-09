package com.lz.hexagonal.arch.person.mappers;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.person.in.web.adapters.dtos.CreatePersonRequestWeb;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-09T13:56:46-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Eclipse Adoptium)"
)
@Component
public class PersonApplicationMapperImpl implements PersonApplicationMapper {

    @Override
    public Person toPerson(CreatePersonRequestWeb createPersonRequestWeb) {
        if ( createPersonRequestWeb == null ) {
            return null;
        }

        String name = null;
        String email = null;
        String cpf = null;
        String phone = null;
        LocalDate birthDate = null;

        name = createPersonRequestWeb.name();
        email = createPersonRequestWeb.email();
        cpf = createPersonRequestWeb.cpf();
        phone = createPersonRequestWeb.phone();
        birthDate = createPersonRequestWeb.birthDate();

        Long id = null;
        LocalDateTime createAt = null;

        Person person = new Person( id, name, email, cpf, phone, birthDate, createAt );

        return person;
    }
}
