package com.example.controle_de_custo.repository;

import com.example.controle_de_custo.models.DespesaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DespesaRepository extends JpaRepository<DespesaModel, Long> {

    List<DespesaModel> findByEmail(String email);
    List<DespesaModel> findByEmailAndData(String email, LocalDate data);

    Page<DespesaModel> findByEmail(String email, Pageable pageable);

}
