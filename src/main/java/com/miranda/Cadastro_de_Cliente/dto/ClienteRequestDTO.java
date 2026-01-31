package com.miranda.Cadastro_de_Cliente.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "DTO para criação/atualização de cliente")
public class ClienteRequestDTO {

    @Schema(description = "Nome do cliente", example = "Adriano")
    @NotBlank(message = "O nome do cliente não pode ser nulo!")
    private String nome;

    @Schema(description = "Sobrenome do cliente", example = "Miranda")
    @NotBlank(message = "O sobrenome do cliente não pode ser nulo!")
    private String sobrenome;

    @Schema(description = "Idade do cliente", example ="14")
    @NotNull(message = "A idade do cliente não pode ser nulo!")
    @Min(value = 0, message = "A idade mínima é 0")
    @Max(value = 150, message = "A idade máxima é 150")
    private Integer idade;

    @Schema(description = "Alguma observação do cliente", example ="Cliente gosta de futebol")
    @NotBlank(message = "O observações do cliente não pode ser nulo!")
    @Size(min = 15, max = 200, message = "A descrição deve conter entre 15 e 200 caracteres.")
    private String descricao;



}
