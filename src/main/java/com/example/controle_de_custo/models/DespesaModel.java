package com.example.controle_de_custo.models;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DespesaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false)
    private String descricao;
    @Column (nullable = false)
    private BigDecimal valor;
    @Column (nullable = false)
    private LocalDate data;

    // limitar o tamanho da categoria
    @Column (length = 100, nullable = false)
    private String categoria;

    // campo para armazenar a data de criação automaticamente
    @CreationTimestamp
    private LocalDateTime dataCriacao;
    @Column (nullable = false)
    private String email;

}
