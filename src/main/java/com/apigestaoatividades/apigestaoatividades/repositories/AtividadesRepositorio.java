package com.apigestaoatividades.apigestaoatividades.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apigestaoatividades.apigestaoatividades.models.Atividades;


public interface AtividadesRepositorio extends JpaRepository<Atividades, Long>{
    
}
