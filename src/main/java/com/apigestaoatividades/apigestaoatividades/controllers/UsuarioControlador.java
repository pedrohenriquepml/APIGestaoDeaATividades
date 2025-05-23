package com.apigestaoatividades.apigestaoatividades.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apigestaoatividades.apigestaoatividades.models.Usuario;
import com.apigestaoatividades.apigestaoatividades.services.UsuarioServico;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    UsuarioServico usuarioServico;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> listaDeErros = new ArrayList<>();
            for (ObjectError erro : bindingResult.getAllErrors()) {
                listaDeErros.add(erro.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(listaDeErros);
        }

        try {
            if (usuarioServico.notExisteCpf(usuario.cpf)) {
                usuario = usuarioServico.cadastrar(usuario);
                return ResponseEntity.ok(usuario);
            }else{
                return ResponseEntity.badRequest().body("O CPF informado já se encontra cadastrado");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Não foi possível cadastrar o usuário");
        }

    }

    @GetMapping("/listar")
    public List<Usuario> listar() {
        return usuarioServico.listar();
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        if(id == null || id <= 0){
            return ResponseEntity.badRequest().body("O ID deverá ser superior a ZERO");
        }
        if(usuarioServico.isExiste(id)){
            usuarioServico.remover(id);
            return ResponseEntity.ok("Usuário removido com sucesso");
        }else{
            return ResponseEntity.badRequest().body("O ID informado não existe");
        }
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizar(@RequestBody @Valid Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> listaDeErros = new ArrayList<>();
            for (ObjectError erro : bindingResult.getAllErrors()) {
                listaDeErros.add(erro.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(listaDeErros);
        }

        try {
            if (usuarioServico.isExiste(usuario.id)) {
                usuario = usuarioServico.atualizar(usuario);
                return ResponseEntity.ok(usuario);
            }else{
                return ResponseEntity.badRequest().body("O ID informado não se encontra cadastrado");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Não foi possível atualizar o usuário");
        }

    }
}
