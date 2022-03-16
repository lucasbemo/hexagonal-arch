package com.lz.hexagonal.arch.person.out.persistence.adapter;

import com.lz.hexagonal.arch.domain.person.ports.out.IListPersonPort;
import com.lz.hexagonal.arch.domain.person.usecases.commands.ListPersonCommand;
import com.lz.hexagonal.arch.domain.person.usecases.response.ListPageablePersonResponse;
import com.lz.hexagonal.arch.person.mappers.PersonMapperImpl;
import com.lz.hexagonal.arch.person.out.persistence.entities.PersonEntity;
import com.lz.hexagonal.arch.person.out.persistence.repository.IListPaginationPersonRepository;
import com.lz.hexagonal.arch.person.out.persistence.specifications.PersonSpecification;
import com.lz.hexagonal.arch.person.out.persistence.specifications.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public record ListPersonRepositoryAdapter(IListPaginationPersonRepository repository) implements IListPersonPort {

    @Override
    public ListPageablePersonResponse execute(final ListPersonCommand listPersonCommand) {
        Page<PersonEntity> personEntities = null;

        if (listPersonCommand != null && listPersonCommand.filters() != null)
            personEntities = findAllWithSpecification(listPersonCommand);
        else
            personEntities = findAll(listPersonCommand);

        return ListPageablePersonResponse.builder()
                .totalPages(personEntities.getTotalPages())
                .totalElements(personEntities.getTotalElements())
                .contents(new PersonMapperImpl().toPerson(personEntities.getContent()))
                .sort(personEntities.getSort().toString())
                .build();
    }

    public Page<PersonEntity> findAllWithSpecification(final ListPersonCommand listPersonCommand) {
        return repository.findAll(
                Specification.where(getPersonSpecification(listPersonCommand)),
                getPageable(listPersonCommand));
    }

    public Page<PersonEntity> findAll(final ListPersonCommand listPersonCommand) {
        return repository.findAll(getPageable(listPersonCommand));
    }

    public Pageable getPageable(final ListPersonCommand listPersonCommand) {
        return PageRequest.of(
                listPersonCommand.page(), listPersonCommand.size(), Sort.by(listPersonCommand.sort()));
    }

    public Specification getPersonSpecification(final ListPersonCommand listPersonCommand) {
        Specification specification = null;
        int idx=0;

        if (listPersonCommand.filters() == null || listPersonCommand.filters().size() <= 0)
            return null;

        for (Map.Entry<String, String> pair: listPersonCommand.filters().entrySet()) {
            if (idx == 0) {
                specification = Specification
                        .where(new PersonSpecification(
                                new SearchCriteria(pair.getKey(), ":", pair.getValue())));
            }
            else {
                specification.and(new PersonSpecification(
                        new SearchCriteria(pair.getKey(), ":", pair.getValue())));
            }
        };

        return specification;
    }
}
