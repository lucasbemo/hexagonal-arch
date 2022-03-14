package com.lz.hexagonal.arch.person.out.persistence.repository;

import com.lz.hexagonal.arch.person.out.persistence.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IListPaginationPersonRepository
        extends PagingAndSortingRepository<PersonEntity, Long>, JpaSpecificationExecutor<PersonEntity> {
}
