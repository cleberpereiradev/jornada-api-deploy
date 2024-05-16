package com.jornada.api.repository;


import com.jornada.api.dto.destinos.DadosListagemDestinoAleatorio;
import com.jornada.api.entity.Destino;
import com.jornada.api.entity.enums.Estacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DestinoRepository extends JpaRepository<Destino, Long> {

    @Query("select d from Destino d where d.estacaoRecomendada = :estacaoAtual order by rand() limit 1")
    List<Destino> findByEstacaoRecomendada(Estacoes estacaoAtual);

    @Query("select new com.jornada.api.dto.destinos.DadosListagemDestinoAleatorio(d.id, d.nome) from Destino d order by rand() limit 3")
    List<DadosListagemDestinoAleatorio> findRandomDestinos();

    boolean existsByNome(String nome);

}
