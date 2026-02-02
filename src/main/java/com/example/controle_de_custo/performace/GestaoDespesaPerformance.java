package com.example.controle_de_custo.performace;

import com.example.controle_de_custo.models.DespesaModel;
import com.example.controle_de_custo.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/gestao/performance")
@RestController
@EnableCaching
public class GestaoDespesaPerformance {

    @Autowired
    DespesaRepository despesa;

    @GetMapping("/lista-sem-paginacao") //http://localhost:8080/gestao/performance/lista-sem-paginacao
    public ResponseEntity<List<DespesaModel>> listaSemPaginacao(){
        long inicio = System.currentTimeMillis();
        var listaDespesas = despesa.findAll();

        long fim = System.currentTimeMillis();
        System.out.println("Tempo de execução (listaSemPaginacao): " + (fim - inicio) + "ms");

        return ResponseEntity.ok().body(listaDespesas);
    }

    @GetMapping("/lista-com-paginacao/{email}") // http://localhost:8080/gestao/performance/lista-com-paginacao/ana@gmail.com?page=0&size=55
    public ResponseEntity<Page<DespesaModel>> listaComPaginacao(@PathVariable String email, Pageable pageable){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        var listaDespesas = despesa.findByEmail(email, pageable); // paginacao
        stopWatch.stop();

        System.out.println("Tempo de execução (listaComPaginacao): " + stopWatch.getTotalTimeMillis() + "ms");

        return ResponseEntity.ok().body(listaDespesas);
    }

    @Cacheable(
            value = "despesasCache",
            key = "#email + '-' + #pageable.pageNumber + '-' + #pageable.pageSize"
    )
    @GetMapping("/cache/{email}") // http://localhost:8080/gestao/performance/cache/ana@gmail.com?page=0&size=55
    public ResponseEntity<Page<DespesaModel>> cacheComPaginacao(@PathVariable String email, Pageable pageable){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        var listaDespesas = despesa.findByEmail(email, pageable); // paginacao
        stopWatch.stop();

        System.out.println("Tempo de execução (listaComPaginacao.): " + stopWatch.getTotalTimeMillis() + "ms");

        return ResponseEntity.ok().body(listaDespesas);
    }
}
