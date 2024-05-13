package com.jornada.api.repository;

import com.jornada.api.dto.depoimentos.DadosListagemDepoimento;
import com.jornada.api.entity.Depoimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepoimentoRepository extends JpaRepository<Depoimento,Long> {

    @Query("select d from Depoimento d order by rand() limit 3")
    List<DadosListagemDepoimento> findRandomDepoimentos();

    DadosListagemDepoimento findDepoimentosByLocal(String local);
}
