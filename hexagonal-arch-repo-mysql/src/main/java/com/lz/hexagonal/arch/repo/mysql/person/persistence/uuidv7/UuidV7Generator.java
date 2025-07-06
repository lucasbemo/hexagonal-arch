package com.lz.hexagonal.arch.repo.mysql.person.persistence.uuidv7;

import com.lz.hexagonal.arch.domain.infra.uuid.UuidUtils;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

public class UuidV7Generator extends SequenceStyleGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        return UuidUtils.timeBasedV7();
    }
}
