package com.miranda.Cadastro_de_Cliente.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tbl_cadastro")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @Column(nullable = false)
    private Integer idade;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao_cadastro", nullable = false)
    private LocalDateTime dataAtualizacaoCadastro;

    // Preenche datas ao CRIAR
    @PrePersist
    public void prePersist(){
        this.dataCadastro = LocalDateTime.now();
        this.dataAtualizacaoCadastro = LocalDateTime.now();
    }

    // Preenche datas ao EDITAR
    @PreUpdate
    public void preUpdate(){
        this.dataAtualizacaoCadastro = LocalDateTime.now();
    }

}
