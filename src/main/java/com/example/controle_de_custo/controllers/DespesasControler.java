package com.example.controle_de_custo.controllers;

import com.example.controle_de_custo.models.DespesaModel;
import com.example.controle_de_custo.useCase.BuscarDespesaUseCase;
import com.example.controle_de_custo.useCase.CadastroDespesaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RequestMapping("/gestao")
@RestController
public class DespesasControler {

    // Lógica para criar uma nova despesa
    // o aurowired injeta a dependencia do caso de uso
    @Autowired
    CadastroDespesaUseCase cadastroDespesaUseCase;

    @Autowired
    BuscarDespesaUseCase buscarDespesaUseCase;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DespesaModel despesa) {
        try {
            var resultado = cadastroDespesaUseCase.execute(despesa);
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException erro) {
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }

    @GetMapping("{email}")
    public List<DespesaModel> findByEmailAndData(@PathVariable String email, @RequestParam(required = false) LocalDate data) {
        return buscarDespesaUseCase.execute(email, data);
    }


}

