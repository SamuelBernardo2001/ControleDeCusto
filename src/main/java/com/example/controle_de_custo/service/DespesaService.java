package com.example.controle_de_custo.service;

import com.example.controle_de_custo.models.DespesaModel;
import com.example.controle_de_custo.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    // Método para cadastrar uma nova despesa
    public DespesaModel execute(DespesaModel despesa) {
        validacao(despesa);
        // Lógica para salvar a despesa no repositório
        despesaRepository.save(despesa);

        return despesa;
    }

    public DespesaModel validacao(DespesaModel despesa) {
        // Lógica para verificar a despesa
        if (despesa.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor da despesa deve ser maior que zero");
        }
        if (despesa.getDescricao() == null || despesa.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("A descrição da despesa não pode ser vazia.");
        }
        if (despesa.getData() == null) {
            throw new IllegalArgumentException("A data da despesa não pode ser nula.");
        }
        if (despesa.getCategoria() == null || despesa.getCategoria().isEmpty()) {
            throw new IllegalArgumentException("A categoria da despesa não pode ser vazia.");
        }
        if (despesa.getEmail() == null || despesa.getEmail().isEmpty()) {
            throw new IllegalArgumentException("O email associado à despesa não pode ser vazio.");
        }
        return despesa;
    }

    public List<DespesaModel> findByEmailAndData(String email, LocalDate data) {
        List<DespesaModel> despesas;
        if (data != null) {
            despesas = despesaRepository.findByEmailAndData(email, data);
        }else{
            despesas = despesaRepository.findByEmail(email);
        }
        return despesas;
    }
}
