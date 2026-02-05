package com.miranda.Cadastro_de_Cliente.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miranda.Cadastro_de_Cliente.dto.ClienteRequestDTO;
import com.miranda.Cadastro_de_Cliente.dto.ClienteResponseDTO;
import com.miranda.Cadastro_de_Cliente.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "gerenciamento de cadastro de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    // CRUD

    @Operation(summary = "Criar um novo cliente")
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO){
        ClienteResponseDTO clienteResponseDTO = clienteService.salvarCliente(clienteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponseDTO);
    }

    @Operation(summary = "Excluir cliente por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorID(@PathVariable Integer id){
        clienteService.excluirClienteID(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Excluir cliente por nome")
    @DeleteMapping("/nome")
    public ResponseEntity<Void> excluirPorNome(@RequestParam("nome") String nome){
        clienteService.excluirClienteNome(nome);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar cliente por ID")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteRequestDTO clienteRequestDTO){
        return ResponseEntity.ok(clienteService.atualizarCliente(id, clienteRequestDTO));
    }


    // LISTAGEM E BUSCA

    @Operation(summary = "Listar todas os clientes")
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos(){
        return ResponseEntity.ok(clienteService.listarTodos());
    }
    
    @Operation(summary = "Buscar cliente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @Operation(summary = "Buscar cliente por nome exato")
    @GetMapping("/buscar-por-nome")
    public ResponseEntity<ClienteResponseDTO> buscarPorNomeExato(@RequestParam("nome") String nome){
        return ResponseEntity.ok(clienteService.buscarPorNome(nome));
    }

    @Operation(summary = "Pesquisar cliente contendo termo")
    @GetMapping("/pesquisar-contendo")
    public ResponseEntity<List<ClienteResponseDTO>> pesquisarPorNomeContendo(@RequestParam("termo") String termo){
        return ResponseEntity.ok(clienteService.listarPorNomeContendo(termo));
    } 

    @Operation(summary = "Pesquisar cliente começando com prefixo")
    @GetMapping("/pesquisar-comecando-com")
    public ResponseEntity<List<ClienteResponseDTO>> pesquisarPorNomeComecandoCom(@RequestParam("prefixo") String prefixo){
        return ResponseEntity.ok(clienteService.listarPorNomeComecandoCom(prefixo));
    }

    @Operation(summary = "Listar clientes por ordem alfabética")
    @GetMapping("/listar-ordem-alfabetica")
    public ResponseEntity<List<ClienteResponseDTO>> listarOrdenadoPorNome(){
        return ResponseEntity.ok(clienteService.listarNomePorOrdemAlfabetica());
    } 

    @Operation(summary = "Pesquisar cliente por sobrenome")
    @GetMapping("/pesquisar-sobrenome")
    public ResponseEntity<List<ClienteResponseDTO>> pesquisarPorSobrenome(@RequestParam("sobrenome") String sobrenome){
        return ResponseEntity.ok(clienteService.listarPorSobrenome(sobrenome)); 
    }


    // BUSCA POR DATA

    @Operation(summary = "Buscar clientes por data de cadastro")
    @GetMapping("/por-data-cadastro")
    public ResponseEntity<List<ClienteResponseDTO>> buscarPorDataCadastro(@RequestParam("data") LocalDate data){
        return ResponseEntity.ok(clienteService.buscarPorDataCadastro(data));
    }

    @Operation(summary = "Buscar clientes por período")
    @GetMapping("/por-periodo")
    public ResponseEntity<List<ClienteResponseDTO>> buscarPorPeriodo(
        @RequestParam("dataInicio") LocalDate dataInicio,
        @RequestParam("dataFim") LocalDate dataFim){
        return ResponseEntity.ok(clienteService.buscarPorPeriodo(dataInicio, dataFim));
    }

    @Operation(summary = "Buscar clientes cadastrados após data")
    @GetMapping("/cadastrados-apos")
    public ResponseEntity<List<ClienteResponseDTO>> buscarCadastradosApos(@RequestParam("data") LocalDate data){
        return ResponseEntity.ok(clienteService.buscarCadastradosApos(data));
    }

    @Operation(summary = "Buscar clientes cadastrados antes de data")
    @GetMapping("/cadastrados-antes")
    public ResponseEntity<List<ClienteResponseDTO>> buscarCadastradosAntes(@RequestParam("data") LocalDate data){
        return ResponseEntity.ok(clienteService.buscarCadastradosAntes(data));
    }

    @Operation(summary = "Buscar clientes por data ordenado por nome")
    @GetMapping("/por-data-ordenado-nome")
    public ResponseEntity<List<ClienteResponseDTO>> buscarPorDataOrdenadoNome(@RequestParam("data") LocalDate data){
        return ResponseEntity.ok(clienteService.buscarCadastradosOrdenadoNome(data));
    }

    // ADICIONAIS

    @Operation(summary = "Retornar total de clientes")
    @GetMapping("/total")
    public ResponseEntity<Map<String, Long>> getClientes(){
        long total =clienteService.totalCliente();
        
        Map<String, Long> response = new HashMap<>();
        response.put("total", total);
        
        return ResponseEntity.ok(response);
    }


}
