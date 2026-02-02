package com.example.controle_de_custo.performace;

import com.example.controle_de_custo.models.DespesaModel;
import com.example.controle_de_custo.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Profile("perfomance")
public class GestaoDeDespesaSeeder implements CommandLineRunner {

    @Autowired
    DespesaRepository despesaRepository;


    private static final List<String> CATEGORIAS = List.of(
            "ALIMENTAÇÃO", "TRANSPORTE", "LAZER", "SAÚDE",
            "EDUCAÇÃO", "MORADIA", "OUTROS"
    );

    private static final List<String> DESCRICOES = List.of(
            "Compra no mercado",
            "Almoço fora",
            "Assinatura mensal",
            "Uber / Transporte",
            "Consulta médica",
            "Curso online",
            "Conta de energia",
            "Conta de internet"
    );

    private static final List<String> EMAILS = List.of(
            "joao@gmail.com",
            "maria@gmail.com",
            "ana@gmail.com",
            "carlos@gmail.com",
            "perfomance@gmail.com"
    );

    @Override
    public void run(String... args) {
        System.out.println("Iniciando o seeding de despesas para testes de performance...");

        List<DespesaModel> despesas = new ArrayList<>();

        for (int i = 0; i < 5000; i++) {
            DespesaModel despesa = new DespesaModel();

            despesa.setDescricao(getDescricaoAleatoria());
            despesa.setCategoria(getCategoriaAleatoria());
            despesa.setEmail(getEmailAleatorio());
            despesa.setValor(getValorAleatorio());
            despesa.setData(getDataAleatoria());

            despesas.add(despesa);
        }

        despesaRepository.saveAll(despesas);

        System.out.println("Seeding de despesas concluído.");
    }

    // =========================
    // MÉTODOS AUXILIARES
    // =========================

    private String getDescricaoAleatoria() {
        return DESCRICOES.get(ThreadLocalRandom.current().nextInt(DESCRICOES.size()));
    }

    private String getCategoriaAleatoria() {
        return CATEGORIAS.get(ThreadLocalRandom.current().nextInt(CATEGORIAS.size()));
    }

    private String getEmailAleatorio() {
        return EMAILS.get(ThreadLocalRandom.current().nextInt(EMAILS.size()));
    }

    private BigDecimal getValorAleatorio() {
        double valor = ThreadLocalRandom.current().nextDouble(5.0, 1500.0);
        return BigDecimal.valueOf(valor).setScale(2, RoundingMode.HALF_UP);
    }

    private LocalDate getDataAleatoria() {
        int diasPassados = ThreadLocalRandom.current().nextInt(0, 180);
        return LocalDate.now().minusDays(diasPassados);
    }
}