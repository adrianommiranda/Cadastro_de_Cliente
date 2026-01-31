package com.miranda.Cadastro_de_Cliente.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.miranda.Cadastro_de_Cliente.model.Cliente;

import jakarta.transaction.Transactional;

//paSpecificationExecutor para consultas dinâmicas complexas.

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer>, JpaSpecificationExecutor<Cliente>{

    //LISTAS

    //MÉTODOS DE CONSULTA POR NOME DO CLIENTE POR ORDENAÇÃO
    List<Cliente> findAllByOrderByNomeAsc();

    //PARA LISTAR POR NOME CONTENDO (IGNORE CASE)
    List<Cliente> findByNomeContainingIgnoreCase(String nomeCliente);

    //PARA LISTAR POR NOME QUE COMEÇA COM (IGNORE CASE)
    List<Cliente> findByNomeStartingWithIgnoreCase(String nomeCliente);

    // PARA LISTAR POR SOBRENOME QUE COMEÇA COM (IGNORE CASE)
    List<Cliente> findBySobrenomeStartingWithIgnoreCase(String sobrenome);


    
    //PESQUISAS

    //PARA LISTAR POR NOME (BUSCA EXATA)
    Optional<Cliente> findByNomeIgnoreCase(String nomecliente);


    //ACESSORIOS DE CADASTROS DE UPDATE

    //PARA VERIFICAR ANTES DE CADASTRAR/UPDATE (IGNORE CASE), SE JÁ EXISTE NOME DO CLIENTE CADASTRADO
    boolean existsByNomeIgnoreCase(String nomeCliente);

    //PARA A VERIFICAÇÃO DO UPDATE, GARANTINDO QUE O NOME DO CLIENTE NÃO PERTENÇA A OUTRA CLIENTE
    Optional<Cliente> findByNomeIgnoreCaseAndIdNot(String nomeCliente, Integer id);

    //TRANSAÇÃO

    //DELETAR
    @Transactional
    void deleteByNomeIgnoreCase(String nomeCliente);


    // OUTROS

    // RETORNA O TOTAL DE CLIENTES CADASTRADOS
    long countAllBy();


   
    // BUSCA POR DATA

    // BUSCA CLIENTES CADASTRADOS EM UMA DATA ESPECÍFICA
    List<Cliente> findByDataCadastro(LocalDate dataCadastro);

    // BUSCA CLIENTES CADASTRADOS ENTRE DUAS DATAS
    List<Cliente> findByDataCadastroBetween(LocalDate dataInicio, LocalDate dataFim);

    // BUSCA CLIENTES CADASTRADOS APÓS UMA DATA
    List<Cliente> findByDataCadastroAfter(LocalDate data);

    // BUSCA CLIENTES CADASTRADOS ANTES DE UMA DATA
    List<Cliente> findByDataCadastroBefore(LocalDate data);

    // BUSCA CLIENTES CADASTRADOS EM UMA DATA, ORDENADOS POR NOME
    List<Cliente> findByDataCadastroOrderByNomeAsc(LocalDate dataCadastro);


}
