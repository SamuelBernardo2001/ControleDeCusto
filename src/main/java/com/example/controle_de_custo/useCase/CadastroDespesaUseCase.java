package com.example.controle_de_custo.useCase;


import com.example.controle_de_custo.models.DespesaModel;
import com.example.controle_de_custo.service.DespesaService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CadastroDespesaUseCase {

    private final DespesaService despesaService;

    public CadastroDespesaUseCase(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    public DespesaModel execute(DespesaModel despesa) {
        return despesaService.execute(despesa);
    }
}