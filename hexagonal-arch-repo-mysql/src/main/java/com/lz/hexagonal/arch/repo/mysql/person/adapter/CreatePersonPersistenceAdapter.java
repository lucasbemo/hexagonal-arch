package com.lz.hexagonal.arch.repo.mysql.person.adapter;

import com.lz.hexagonal.arch.domain.infra.exceptions.HexagonalException;
import com.lz.hexagonal.arch.domain.infra.exceptions.HexagonalInternalException;
import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.ports.out.ICreatePersonPort;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.entities.PersonEntity;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.repository.IPersonRepository;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

@Service
public record CreatePersonPersistenceAdapter(IPersonRepository repository) implements ICreatePersonPort {

    static final Logger logger = LoggerFactory.getLogger(CreatePersonPersistenceAdapter.class);

    @Override
    public Person execute(final Person person) throws HexagonalException {
        try {
            PersonEntity entity = PersonEntity.fromPerson(person);

            return repository.save(entity).toPerson();
        } catch (TransactionSystemException transactionSystemException) {
            if (transactionSystemException.getRootCause() instanceof ConstraintViolationException) {
                ConstraintViolationException validationException = (ConstraintViolationException)
                        transactionSystemException.getRootCause();
                throw validationException;
            }
            throw transactionSystemException;
        } catch (Exception exception) {
            throw new HexagonalInternalException("Error creating person", exception.fillInStackTrace());
        }
    }
}
