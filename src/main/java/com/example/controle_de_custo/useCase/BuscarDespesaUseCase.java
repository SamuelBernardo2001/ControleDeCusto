package com.example.controle_de_custo.useCase;

import com.example.controle_de_custo.models.DespesaModel;
import com.example.controle_de_custo.service.DespesaService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Component
public class BuscarDespesaUseCase {

    private final DespesaService despesaService;

    public BuscarDespesaUseCase(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    public List<DespesaModel> execute(String email, LocalDate data) {
        return despesaService.findByEmailAndData(email, data);
    }
}