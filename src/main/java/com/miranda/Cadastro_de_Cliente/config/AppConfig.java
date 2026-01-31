package com.miranda.Cadastro_de_Cliente.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /*
        Configurei uma estratégia de mapeamento mais rigorosa chamada 'STRICT'.
        Isso força o ModelMapper a apenas mapear campos que tenham nomes e tipos
        Exatamente iguais entre a classe de origem e a de destino.
        É uma boa prática para evitar mapeamentos inesperados e difíceis de depurar.

        O ModelMapper é uma biblioteca que copia automaticamente os dados de um objeto para outro. 
        Ele evita que você escreva código repetitivo de conversão
    */

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}
