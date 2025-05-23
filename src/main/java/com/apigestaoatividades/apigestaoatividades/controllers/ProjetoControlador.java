package com.apigestaoatividades.apigestaoatividades.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apigestaoatividades.apigestaoatividades.models.Projeto;
import com.apigestaoatividades.apigestaoatividades.services.ProjetoServico;
import com.apigestaoatividades.apigestaoatividades.services.UsuarioServico;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/projeto")
public class ProjetoControlador {
    @Autowired
    ProjetoServico projetoServico;
    @Autowired
    UsuarioServico usuarioServico;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid Projeto projeto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ArrayList<String> erros = new ArrayList<>();
            for (ObjectError elem : bindingResult.getAllErrors()) {
                erros.add(elem.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(erros);
        }
        if (usuarioServico.isExiste(projeto.usuario.id)) {
            projeto = projetoServico.cadastrar(projeto);
            return ResponseEntity.ok(projeto);
        }else{
            return ResponseEntity.badRequest().body("O gestor não existe");
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(projetoServico.listar());
    }
    

}
