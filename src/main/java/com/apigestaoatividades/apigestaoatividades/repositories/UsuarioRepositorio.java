package com.apigestaoatividades.apigestaoatividades.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apigestaoatividades.apigestaoatividades.models.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
    List<Usuario> findByCpf(String cpf);
}
