package com.apigestaoatividades.apigestaoatividades.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name="projeto")
public class Projeto {
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

    @ManyToOne
    @JoinColumn(name = "gestor_id")
    public Usuario usuario;

    @JsonIgnore
    @OneToMany(mappedBy = "Projeto", cascade = CascadeType.ALL)
    public List<Atividades> atividades;
}
