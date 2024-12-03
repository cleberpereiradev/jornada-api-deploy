package com.jornada.api.repository;

import com.jornada.api.entity.Destino;
import com.jornada.api.entity.enums.Estacoes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
public class DestinoRepositoryTest {

    @Autowired
    private DestinoRepository destinoRepository;

    @Test
    void testFindAll() {
        Destino destino = new Destino(1L,"Test Destination", "Test Description", Estacoes.PRIMAVERA);
        List<Destino> destinos = destinoRepository.findAll();
        destinos.add(destino);
        Assert.notNull(destinos, "Destinos list should not be null");
        Assert.notEmpty(destinos, "Destinos list should not be empty");
    }

    @Test
    void testSave() {
        Destino destino = new Destino();
        destino.setNome("Test Destination");
        destino.setDescricaoCompleta("Test Description");

        Destino savedDestino = destinoRepository.save(destino);
        Assert.notNull(savedDestino, "Destino salvo não deve ser nulo");
        Assert.notNull(savedDestino.getId(), "O id do destino salvo não deve ser nulo");
    }
}