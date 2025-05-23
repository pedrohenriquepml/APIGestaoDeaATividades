package com.apigestaoatividades.apigestaoatividades.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apigestaoatividades.apigestaoatividades.models.Projeto;
import com.apigestaoatividades.apigestaoatividades.repositories.ProjetoRepositorio;

@Service
public class ProjetoServico {

    @Autowired
    private ProjetoRepositorio projetoRepositorio;

    public boolean isExiste(Long id) {
        return projetoRepositorio.existsById(id);
    }

    public Projeto cadastrar(Projeto projeto) {
        return projetoRepositorio.save(projeto);
    }

    public Projeto atualizar(Projeto projeto) {
        return projetoRepositorio.save(projeto);
    }

    public List<Projeto> listar() {
        return projetoRepositorio.findAll();
    }

    public Optional<Projeto> buscarPorId(Long id) {
        return projetoRepositorio.findById(id);
    }

    public void remover(Long id) {
        if (isExiste(id)) {
            projetoRepositorio.deleteById(id);
        }
    }

    public ResponseEntity<?> remover2(Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().body("O ID deverá ser superior a ZERO");
        }
        if (isExiste(id)) {
            remover(id);
            return ResponseEntity.ok("Projeto removido com sucesso");
        } else {
            return ResponseEntity.badRequest().body("O ID informado não existe");
        }
    }
}
