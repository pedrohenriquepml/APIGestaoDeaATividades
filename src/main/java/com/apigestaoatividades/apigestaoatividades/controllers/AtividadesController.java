package com.apigestaoatividades.apigestaoatividades.controllers;

import com.apigestaoatividades.apigestaoatividades.models.Atividades;
import com.apigestaoatividades.apigestaoatividades.services.AtividadesServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atividades")
public class AtividadesController {

    @Autowired
    private AtividadesServico atividadesServico;

    @GetMapping
    public ResponseEntity<List<Atividades>> listarTodas() {
        List<Atividades> atividades = atividadesServico.listarTodas();
        return ResponseEntity.ok(atividades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atividades> buscarPorId(@PathVariable Long id) {
        return atividadesServico.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Atividades> criar(@RequestBody Atividades atividade) {
        Atividades novaAtividade = atividadesServico.salvar(atividade);
        return ResponseEntity.ok(novaAtividade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atividades> atualizar(@PathVariable Long id, @RequestBody Atividades atividadeAtualizada) {
        if (!atividadesServico.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        atividadeAtualizada.setId(id);
        return ResponseEntity.ok(atividadesServico.salvar(atividadeAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!atividadesServico.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        atividadesServico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}