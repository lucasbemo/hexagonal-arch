package com.lz.hexagonal.arch.repo.mysql.person.persistence.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.entities.PersonEntity;

public interface IListPaginationPersonRepository
        extends PagingAndSortingRepository<PersonEntity, Long>, JpaSpecificationExecutor<PersonEntity> {
}
