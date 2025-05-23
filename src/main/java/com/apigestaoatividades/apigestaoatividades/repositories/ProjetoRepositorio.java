package com.apigestaoatividades.apigestaoatividades.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apigestaoatividades.apigestaoatividades.models.Projeto;

public interface ProjetoRepositorio extends JpaRepository<Projeto, Long>{
    
}
