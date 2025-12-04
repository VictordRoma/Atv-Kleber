package com.victor.config.service;

import com.victor.repository.PessoaRepository;
import com.victor.service.PessoaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaServiceConfig {
    @Bean
    public PessoaService pessoaService(PessoaRepository repository){
        return new PessoaService(repository);
    }
}
