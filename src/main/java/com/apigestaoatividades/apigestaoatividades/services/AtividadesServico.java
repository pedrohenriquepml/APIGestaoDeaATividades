package com.apigestaoatividades.apigestaoatividades.services;

import com.apigestaoatividades.apigestaoatividades.models.Atividades;
import com.apigestaoatividades.apigestaoatividades.repositories.AtividadesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadesServico {

    @Autowired
    private AtividadesRepositorio atividadesRepositorio;

    public List<Atividades> listarTodas() {
        return atividadesRepositorio.findAll();
    }

    public Optional<Atividades> buscarPorId(Long id) {
        return atividadesRepositorio.findById(id);
    }

    public Atividades salvar(Atividades atividade) {
        return atividadesRepositorio.save(atividade);
    }

    public boolean existePorId(Long id) {
        return atividadesRepositorio.existsById(id);
    }

    public void deletar(Long id) {
        atividadesRepositorio.deleteById(id);
    }
}
