package com.apigestaoatividades.apigestaoatividades.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "Atividades")
public class Atividades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank(message="O nome não pode estar em branco")
    @NotNull(message="O nome deve ser informado")
    @Column(name = "nome", columnDefinition = "varchar(100)", nullable = false)
    public String nome;

    @Size(max=300, message="A descrição não pode ultrapassar a 300 caracteres")
    @Column(name = "decricao", columnDefinition = "varchar(300)", nullable = true)
    public String descricao;

    @NotBlank(message="A data nao pode estar em branco")
    @NotNull(message="A data precisa ser definida")
    @Column(name ="data_inicio", columnDefinition = "DATE",nullable = false)
    public Date data_inicio = new Date();

    @NotBlank(message="A nao nao pode estar em branco")
    @NotNull(message="A data precisa ser definida")
    @Column(name ="data_final", columnDefinition = "DATE",nullable = false)
    public Date data_final = new Date();

    @NotBlank(message="A nao precisa pode estar em branco")
    @NotNull(message="A data precisa ser definida")
    @Enumerated(EnumType.STRING)
    @Column(name = "nv_prioridade", columnDefinition = "Varchar(5)",nullable = false)
    public Prioridade nv_prioridade;
    public enum Prioridade {
        BAIXA, MEDIA, ALTA
    }

    @Column(name = "status", columnDefinition = "Varchar(11)", nullable = false)
    public Status status;
    public enum Status{
        Parada, emandamento, Atrasada, finalizada
    }

    @ManyToOne
    @JoinColumn(name = "gestor_id")
    public Usuario usuario;

    @JoinColumn(name = "projeto")
    public Projeto projeto;


}
