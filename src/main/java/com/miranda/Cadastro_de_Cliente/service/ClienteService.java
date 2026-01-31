package com.miranda.Cadastro_de_Cliente.service;

import java.time.LocalDate;
import java.util.List;

import com.miranda.Cadastro_de_Cliente.dto.ClienteRequestDTO;
import com.miranda.Cadastro_de_Cliente.dto.ClienteResponseDTO;

public interface ClienteService {

//SALVANDO
ClienteResponseDTO salvarCliente(ClienteRequestDTO clienteRequestDTO);

//UPDATE
ClienteResponseDTO atualizarCliente(Integer id, ClienteRequestDTO clienteRequestDTO);

//DELETE
void excluirClienteID(Integer id);
void excluirClienteNome(String nome);

//LISTAR
List<ClienteResponseDTO> listarTodos();
List<ClienteResponseDTO> listarNomePorOrdemAlfabetica();
List<ClienteResponseDTO> listarPorNomeContendo(String nomeCliente);
List<ClienteResponseDTO> listarPorNomeComecandoCom(String nomeCliente);
List<ClienteResponseDTO> listarPorSobrenome(String sobrenomeNomeCliente);


// BUSCAR (OPTIONAL)
ClienteResponseDTO buscarPorId(Integer id);
ClienteResponseDTO buscarPorNome(String nome);

//CONTAGEM
long totalCliente();


// BUSCA POR DATA

// BUSCA CLIENTES CADASTRADOS EM UMA DATA ESPECÍFICA
List<ClienteResponseDTO> buscarPorDataCadastro(LocalDate dataCadastro);

// BUSCA CLIENTES CADASTRADOS ENTRE DUAS DATAS
List<ClienteResponseDTO> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim);

// BUSCA CLIENTES CADASTRADOS APÓS UMA DATA
List<ClienteResponseDTO> buscarCadastradosApos(LocalDate data);

// BUSCA CLIENTES CADASTRADOS ANTES DE UMA DATA
List<ClienteResponseDTO> buscarCadastradosAntes(LocalDate data);

// BUSCA CLIENTES CADASTRADOS EM UMA DATA, ORDENADOS POR NOME
List<ClienteResponseDTO> buscarCadastradosOrdenadoNome(LocalDate dataCadastro);
}
