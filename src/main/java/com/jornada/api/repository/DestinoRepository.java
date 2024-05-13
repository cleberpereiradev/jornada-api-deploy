package com.jornada.api.repository;

import com.jornada.api.entity.Destino;
import com.jornada.api.entity.enums.Estacoes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinoRepository extends JpaRepository<Destino, Long> {

    List<Destino> findByEstacaoRecomendada(Estacoes estacaoAtual);
}
