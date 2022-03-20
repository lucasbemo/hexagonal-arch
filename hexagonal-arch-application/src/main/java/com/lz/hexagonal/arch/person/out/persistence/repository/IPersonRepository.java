package com.lz.hexagonal.arch.person.out.persistence.repository;

import com.lz.hexagonal.arch.person.out.persistence.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPersonRepository extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByEmail(final String email);
}
