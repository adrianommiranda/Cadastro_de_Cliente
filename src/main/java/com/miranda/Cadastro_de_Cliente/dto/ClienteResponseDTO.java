package com.miranda.Cadastro_de_Cliente.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO de resposta com dados do cliente")
public class ClienteResponseDTO {
   
    @Schema(description = "ID do cliente", example = "1")
    private Integer id;

    @Schema(description = "Nome do cliente", example = "Adriano")
    private String nome;

    @Schema(description = "Sobrenome do cliente", example = "Miranda")
    private String sobrenome;

    @Schema(description = "Idade do cliente", example ="14")
    private Integer idade;

    @Schema(description = "Alguma observação do cliente", example ="Cliente gosta de futebol")
    private String descricao;

    @Schema(description = "Data de cadastro", example = "2026-01-14T21:30:00")
    private LocalDateTime dataCadastro;

    @Schema(description = "Data da ultima atualização do cadastro", example = "2026-01-14T21:30:00")
    private LocalDateTime dataAtualizacaoCadastro;
}
