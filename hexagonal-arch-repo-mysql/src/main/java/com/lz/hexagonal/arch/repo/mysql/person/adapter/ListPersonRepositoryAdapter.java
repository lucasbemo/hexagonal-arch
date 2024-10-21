package com.lz.hexagonal.arch.repo.mysql.person.adapter;

import com.lz.hexagonal.arch.repo.mysql.person.infra.mappers.PersonMapper;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.entities.PersonEntity;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.repository.IListPaginationPersonRepository;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.specifications.PersonSpecification;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.specifications.SearchCriteria;
import com.lz.hexagonal.arch.domain.person.dtos.ListPageablePersonDTO;
import com.lz.hexagonal.arch.domain.person.dtos.ListPersonDTO;
import com.lz.hexagonal.arch.domain.person.ports.out.IListPersonPort;
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
        IListPaginationPersonRepository repository, PersonMapper personMapper) implements IListPersonPort {

    @Override
    public ListPageablePersonDTO execute(final ListPersonDTO listPersonDTO) {
        Page<PersonEntity> personEntities = null;

        if (listPersonDTO != null && listPersonDTO.getFilters() != null)
            personEntities = findAllWithSpecification(listPersonDTO);
        else
            personEntities = findAll(listPersonDTO);

        return new ListPageablePersonDTO(
                personEntities.getTotalPages(),
                personEntities.getTotalElements(),
                personEntities.getSort().toString(),
                personEntities.getContent().stream()
                        .map(personEntity -> personMapper.toPerson(personEntity))
                        .collect(Collectors.toList()));
    }

    public Page<PersonEntity> findAllWithSpecification(final ListPersonDTO listPersonDTO) {
        return repository.findAll(
                Specification.where(getPersonSpecification(listPersonDTO)),
                getPageable(listPersonDTO));
    }

    public Page<PersonEntity> findAll(final ListPersonDTO listPersonDTO) {
        return repository.findAll(getPageable(listPersonDTO));
    }

    public Pageable getPageable(final ListPersonDTO listPersonDTO) {
        return PageRequest.of(
                listPersonDTO.page(), listPersonDTO.size(), Sort.by(listPersonDTO.sort()));
    }

    private Specification<PersonEntity> getPersonSpecification(final ListPersonDTO listPersonDTO) {
        Specification<PersonEntity> specification = null;
        int idx=0;

        if (listPersonDTO.getFilters() == null || listPersonDTO.getFilters().size() <= 0)
            return null;

        for (Map.Entry<String, String> pair: listPersonDTO.getFilters().entrySet()) {
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
