package com.lz.hexagonal.arch.repo.mysql.person.adapter;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.ports.out.IListPersonPort;
import com.lz.hexagonal.arch.domain.person.usecase.commands.ListPersonCommand;
import com.lz.hexagonal.arch.domain.person.usecase.response.ListPageablePersonResponse;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.entities.PersonEntity;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.repository.IListPaginationPersonRepository;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.specifications.PersonSpecification;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.specifications.SearchCriteria;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public record ListPersonRepositoryAdapter(
        IListPaginationPersonRepository repository, ModelMapper modelMapper) implements IListPersonPort {

    @Override
    public ListPageablePersonResponse execute(final ListPersonCommand listPersonCommand) {
        Page<PersonEntity> personEntities = null;

        if (listPersonCommand != null && listPersonCommand.getFilters() != null)
            personEntities = findAllWithSpecification(listPersonCommand);
        else
            personEntities = findAll(listPersonCommand);

        return new ListPageablePersonResponse(
                personEntities.getTotalPages(),
                personEntities.getTotalElements(),
                personEntities.getSort().toString(),
                personEntities.getContent().stream()
                        .map(personEntity -> modelMapper.map(personEntity, Person.class))
                        .collect(Collectors.toList()));
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

    public Specification<PersonEntity> getPersonSpecification(final ListPersonCommand listPersonCommand) {
        Specification<PersonEntity> specification = null;
        int idx=0;

        if (listPersonCommand.getFilters() == null || listPersonCommand.getFilters().size() <= 0)
            return null;

        for (Map.Entry<String, String> pair: listPersonCommand.getFilters().entrySet()) {
            if (idx == 0) {
                specification = Specification
                        .where(new PersonSpecification(
                                new SearchCriteria(pair.getKey(), ":", pair.getValue())));
            }
            else {
                specification.and(new PersonSpecification(
                        new SearchCriteria(pair.getKey(), ":", pair.getValue())));
            }
            idx++;
        };
        return specification;
    }
}
