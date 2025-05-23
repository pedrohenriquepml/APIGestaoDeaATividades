package com.apigestaoatividades.apigestaoatividades.models;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank(message="O nome não pode estar em branco")
    @NotNull(message="O nome deve ser informado")
    @Column(name = "nome", columnDefinition = "varchar(100)", nullable = false)
    public String nome;
    @NotBlank(message = "O CPF não pode estar em branco")
    @Column(name = "cpf", columnDefinition = "varchar(11)", nullable = false, unique = true)
    public String cpf;
    @NotBlank(message = "O E-mail não pode estar em branco")
    @Column(name = "email", columnDefinition = "varchar(100)", nullable = false)
    public String email;
    @NotBlank(message = "A senha não pode estar em branco")
    @Column(name = "senha", columnDefinition = "varchar(100)", nullable = false)
    public String senha;
    @Min(value=0,message="O valor mínimo para o status é 0")
    @Max(value=1,message="O valor máximo para o status é 1")
    @Column(name = "status", columnDefinition = "int", nullable = false)
    public int status;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    public List<Projeto> projetos;

}
