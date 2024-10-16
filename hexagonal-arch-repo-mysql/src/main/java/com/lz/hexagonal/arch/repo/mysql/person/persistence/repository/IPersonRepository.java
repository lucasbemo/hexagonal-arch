package com.lz.hexagonal.arch.repo.mysql.person.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.entities.PersonEntity;

import java.util.Optional;

public interface IPersonRepository extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByEmail(final String email);
}
