package com.miranda.Cadastro_de_Cliente.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.miranda.Cadastro_de_Cliente.dto.ClienteRequestDTO;
import com.miranda.Cadastro_de_Cliente.dto.ClienteResponseDTO;
import com.miranda.Cadastro_de_Cliente.exception.EntidadeNaoEncontradaException;
import com.miranda.Cadastro_de_Cliente.exception.RegraNegocioException;
import com.miranda.Cadastro_de_Cliente.model.Cliente;
import com.miranda.Cadastro_de_Cliente.repository.ClienteRepository;
import com.miranda.Cadastro_de_Cliente.service.ClienteService;

import jakarta.transaction.Transactional;

@Service
public class ClienteServiceImpl implements ClienteService{

    private final ModelMapper modelMapper;
    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ModelMapper modelMapper, ClienteRepository clienteRepository){
        this.modelMapper = modelMapper;
        this.clienteRepository = clienteRepository;
    }

    //MÉTODO CREATE
    @Override
    @Transactional
    public ClienteResponseDTO salvarCliente(ClienteRequestDTO clienteRequestDTO) {
        verificaSeNomeClienteExiste(null, clienteRequestDTO.getNome());
        Cliente cliente = modelMapper.map(clienteRequestDTO, Cliente.class); 
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return modelMapper.map(clienteSalvo, ClienteResponseDTO.class);
    }

    //MÉTODO UPDATE
    @Override
    @Transactional
    public ClienteResponseDTO atualizarCliente(Integer id, ClienteRequestDTO clienteRequestDTO) {
        Cliente clienteExiste = clienteRepository.findById(id)
                                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente com o código  " + id + " não encontrado."));

        verificaSeNomeClienteExiste(id, clienteRequestDTO.getNome());

        //SE PASSOU NAS 2 VERIFICAÇÕES, ATUALZIA OS CAMPOS DA ORIGEM (clienteRequestDTO) PARA O DESTINO (clienteExiste)
        modelMapper.map(clienteRequestDTO, clienteExiste);

        Cliente clienteAtualizado = clienteRepository.save(clienteExiste);

        return modelMapper.map(clienteAtualizado, ClienteResponseDTO.class);
    }


    //MÉTODOS DELETE

    //EXCLUIR POR ID
    @Override
    @Transactional
    public void excluirClienteID(Integer id) {
        if(!clienteRepository.existsById(id)){
            throw new EntidadeNaoEncontradaException("Cliente com o código " + id + " não encontado para exclusão");
        }
        clienteRepository.deleteById(id);
    }

    //EXCLUIR PELO NOME
    @Override
    @Transactional
    public void excluirClienteNome(String nome) {
        if(!clienteRepository.existsByNomeIgnoreCase(nome)){
            throw new EntidadeNaoEncontradaException("Cliente com o nome " + nome + " não encontado para exclusão");
        }
        clienteRepository.deleteByNomeIgnoreCase(nome);
    }


    // MÉTODOS PRIVADOS DE VALIDAÇÃO
   
    //VERIFICA SE O NOME DO CLIENTE EXISTE, MÉTODO INTERNO
    private void verificaSeNomeClienteExiste(Integer id, String nomeCliente){
        if(id == null){
            // Cenário de CADASTRO (id é nulo): verifica se o nome já existe em qualquer registro.
            if(clienteRepository.existsByNomeIgnoreCase(nomeCliente)){
                throw new RegraNegocioException("Já existe um cliente cadastrada com o nome '" + nomeCliente + "'.");
            }
        }else{
             // Cenário de UPDATE (id existe): verifica se o nome já existe em OUTRO registro que não seja o atual.
             clienteRepository.findByNomeIgnoreCaseAndIdNot(nomeCliente, id).ifPresent(cat -> {
                throw new RegraNegocioException("Já existe um cliente cadastrada com o nome '" + nomeCliente + "'.");
             });
        }
    }
        

    //MÉTODOS CONSULTA
    //LISTAR TODAS  OS CLIENTES
    @Override
    public List<ClienteResponseDTO> listarTodos() {
        List<Cliente> listaClientes =  clienteRepository.findAll();
        return listaClientes.stream().map(cli -> modelMapper.map(cli, ClienteResponseDTO.class)).collect(Collectors.toList());
    }

    //LISTAR CLIENTES POR ORDEM ALFABÉTICA   
    @Override
    public List<ClienteResponseDTO> listarNomePorOrdemAlfabetica() {
        
        List<Cliente> listaClientes = clienteRepository.findAllByOrderByNomeAsc();
        return listaClientes.stream().map(cli -> modelMapper.map(cli, ClienteResponseDTO.class)).collect(Collectors.toList());
    }

    //LISTAR CLIENTES QUE CONTENHAM PARTE DO NOME
    @Override
    public List<ClienteResponseDTO> listarPorNomeContendo(String nomeCliente) {
        List<Cliente> listaCliente = clienteRepository.findByNomeContainingIgnoreCase(nomeCliente);
        return listaCliente.stream().map(cli -> modelMapper.map(cli, ClienteResponseDTO.class)).collect(Collectors.toList());
    }

    //LISTAR CLIENTES QUE COMECEM COM O NOME
    @Override
    public List<ClienteResponseDTO> listarPorNomeComecandoCom(String nomeCliente) {
        List<Cliente> listaCliente = clienteRepository.findByNomeStartingWithIgnoreCase(nomeCliente);
        return listaCliente.stream().map(cli -> modelMapper.map(cli, ClienteResponseDTO.class)).collect(Collectors.toList());
    }

    //LISTAR CLIENTES POR SOBRENOME
    @Override
    public List<ClienteResponseDTO> listarPorSobrenome(String sobrenomeNomeCliente) {
        List<Cliente> listaCliente = clienteRepository.findBySobrenomeStartingWithIgnoreCase(sobrenomeNomeCliente);
        return listaCliente.stream().map(cli -> modelMapper.map(cli, ClienteResponseDTO.class)).collect(Collectors.toList());
    }


    //PESQUISA CLIENTE POR ID
    @Override
    public ClienteResponseDTO buscarPorId(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                          .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente com código " + id + " não encontrado."));
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    //PESQUISA CLIENTE PELO NOME
    @Override
    public ClienteResponseDTO buscarPorNome(String nome) {
        Cliente cliente =  clienteRepository.findByNomeIgnoreCase(nome)
                            .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente com o nome " + nome + " não encontrado."));
        
        return modelMapper.map(cliente, ClienteResponseDTO.class);

    }

    //TOTAL DE CLIENTES CADASTRADOS
    @Override
    public long totalCliente() {
        return clienteRepository.count();
    }

    //PESQUISA POR DATAS
    //PESQUISA POR DATA DE CADASTRO
    @Override
    public List<ClienteResponseDTO> buscarPorDataCadastro(LocalDate dataCadastro) {
        List<Cliente> listaCliente = clienteRepository.findByDataCadastro(dataCadastro);
        return listaCliente.stream().map(cli -> modelMapper.map(cli, ClienteResponseDTO.class)).collect(Collectors.toList());
    }

    //PESQUISA ENTRE DATAS
    @Override
    public List<ClienteResponseDTO> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        List<Cliente> listaCliente = clienteRepository.findByDataCadastroBetween(dataInicio, dataFim);
        return listaCliente.stream().map(cli -> modelMapper.map(cli, ClienteResponseDTO.class)).collect(Collectors.toList());
    }

    //PESQUISA APÓS A DATA DE PESQUISA
    @Override
    public List<ClienteResponseDTO> buscarCadastradosApos(LocalDate data) {
        List<Cliente> listaCliente = clienteRepository.findByDataCadastroAfter(data);
        return listaCliente.stream().map(cli -> modelMapper.map(cli, ClienteResponseDTO.class)).collect(Collectors.toList());
    }
    
        //PESQUISA ANTES A DATA DE PESQUISA
    @Override
    public List<ClienteResponseDTO> buscarCadastradosAntes(LocalDate data) {
        List<Cliente> listaCliente = clienteRepository.findByDataCadastroBefore(data);
        return listaCliente.stream().map(cli -> modelMapper.map(cli, ClienteResponseDTO.class)).collect(Collectors.toList());
    }

    //PESQUISA ORDENADO PELO NOME
    @Override
    public List<ClienteResponseDTO> buscarCadastradosOrdenadoNome(LocalDate dataCadastro) {
        List<Cliente> listaCliente = clienteRepository.findByDataCadastroOrderByNomeAsc(dataCadastro);
        return listaCliente.stream().map(cli -> modelMapper.map(cli, ClienteResponseDTO.class)).collect(Collectors.toList());
    }

}
