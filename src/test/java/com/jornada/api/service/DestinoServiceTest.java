package com.jornada.api.service;

import com.jornada.api.dto.destinos.DadosListagemDestinoAleatorio;
import com.jornada.api.repository.DestinoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DestinoServiceTest {

    @Mock
    private DestinoRepository destinoRepository;

    @InjectMocks
    private DestinoService destinoService;

    @Test
    void testFindRandomDestinos() {
        // Criando uma lista simulada de destinos
        List<DadosListagemDestinoAleatorio> mockDestinos = new ArrayList<>();
        mockDestinos.add(new DadosListagemDestinoAleatorio(1L, "Brasília"));
        mockDestinos.add(new DadosListagemDestinoAleatorio(2L, "Rio de Janeiro"));
        mockDestinos.add(new DadosListagemDestinoAleatorio(3L, "São Paulo"));

        // Definindo o comportamento do mock do repositório
        when(destinoRepository.findRandomDestinos()).thenReturn(mockDestinos);

        // Chamando o método do serviço
        List<DadosListagemDestinoAleatorio> result = destinoService.findRandomDestinos();

        // Verificando se o método do repositório foi chamado
        verify(destinoRepository).findRandomDestinos();

        // Verificando o resultado
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("Brasília", result.get(0).nome());
        assertEquals("Rio de Janeiro", result.get(1).nome());
        assertEquals("São Paulo", result.get(2).nome());
    }

    @Test
    void testFindRandomDestinos_NoDestinosFound() {
        // Definindo o comportamento do mock do repositório
        when(destinoRepository.findRandomDestinos()).thenReturn(Collections.emptyList());

        // Chamando o método do serviço
        List<DadosListagemDestinoAleatorio> result = destinoService.findRandomDestinos();

        // Verificando o resultado
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}