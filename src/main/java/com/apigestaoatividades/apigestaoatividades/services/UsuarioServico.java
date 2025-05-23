package com.apigestaoatividades.apigestaoatividades.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apigestaoatividades.apigestaoatividades.models.Usuario;
import com.apigestaoatividades.apigestaoatividades.repositories.UsuarioRepositorio;

@Service
public class UsuarioServico {
    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    public boolean isExiste(Long id) {
        return usuarioRepositorio.existsById(id);
    }

    public boolean notExisteCpf(String cpf) {
        List<Usuario> lista = usuarioRepositorio.findByCpf(cpf);
        return lista.isEmpty();
    }

    public Usuario cadastrar(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    public Usuario atualizar(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepositorio.findAll();
    }

    public void remover(Long id) {
        if (isExiste(id)) {
            usuarioRepositorio.deleteById(id);
        }
    }

    public ResponseEntity<?> remover2(Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().body("O ID deverá ser superior a ZERO");
        }
        if (isExiste(id)) {
            remover(id);
            return ResponseEntity.ok("Usuário removido com sucesso");
        } else {
            return ResponseEntity.badRequest().body("O ID informado não existe");
        }
    }
}
