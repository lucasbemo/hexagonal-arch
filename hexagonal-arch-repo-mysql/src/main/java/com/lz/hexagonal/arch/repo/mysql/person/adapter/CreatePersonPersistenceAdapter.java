package com.lz.hexagonal.arch.repo.mysql.person.adapter;

import com.lz.hexagonal.arch.domain.infra.HexagonalErrorCodes;
import com.lz.hexagonal.arch.domain.infra.exceptions.HexagonalException;
import com.lz.hexagonal.arch.domain.infra.exceptions.HexagonalInternalException;
import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.ports.ICreatePersonPort;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.entities.PersonEntity;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.repository.IPersonRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class CreatePersonPersistenceAdapter implements ICreatePersonPort {
    static final Logger logger = LoggerFactory.getLogger(CreatePersonPersistenceAdapter.class);
    private final IPersonRepository repository;

    @Autowired
    public CreatePersonPersistenceAdapter(final IPersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person execute(@Valid final Person person) throws HexagonalException {
        try {
            PersonEntity entity = PersonEntity.fromPerson(person);

            var entityDB = repository.save(entity).toPerson();
            logger.info("Person saved: {}", entityDB);
            return entityDB;
        } catch (TransactionSystemException transactionSystemException) {
            if (transactionSystemException.getRootCause() instanceof ConstraintViolationException) {
                ConstraintViolationException validationException = (ConstraintViolationException)
                        transactionSystemException.getRootCause();
                throw validationException;
            }
            throw transactionSystemException;
        } catch (Exception exception) {
            throw new HexagonalInternalException(
                    "An unexpected error occurred while creating person"
                    , exception.fillInStackTrace(), HexagonalErrorCodes.HA_ERROR_CONSTRAINT_VIOLATION);
        }
    }
}
