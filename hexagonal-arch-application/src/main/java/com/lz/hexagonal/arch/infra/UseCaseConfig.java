package com.lz.hexagonal.arch.infra;

import com.lz.hexagonal.arch.domain.person.ports.out.ICreatePersonPort;
import com.lz.hexagonal.arch.domain.person.ports.out.IListPersonPort;
import com.lz.hexagonal.arch.domain.person.usecases.ICreatePersonUseCase;
import com.lz.hexagonal.arch.domain.person.usecases.IListPersonUseCase;
import com.lz.hexagonal.arch.domain.person.usecases.impl.CreatePersonUseCase;
import com.lz.hexagonal.arch.domain.person.usecases.impl.ListPersonUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public ICreatePersonUseCase createPersonUseCase(final ICreatePersonPort createPersonPort) {
        return new CreatePersonUseCase(createPersonPort);
    }

    @Bean
    public IListPersonUseCase listPersonUseCase(final IListPersonPort listPersonPort) {
        return new ListPersonUseCase(listPersonPort);
    }
}
