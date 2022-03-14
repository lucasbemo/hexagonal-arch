package com.lz.hexagonal.arch.person.out.persistence.repository;

import com.lz.hexagonal.arch.person.out.persistence.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<PersonEntity, Long> {
}
