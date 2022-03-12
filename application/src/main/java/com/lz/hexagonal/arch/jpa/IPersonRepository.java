package com.lz.hexagonal.arch.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<PersonEntity, Long> {
}
