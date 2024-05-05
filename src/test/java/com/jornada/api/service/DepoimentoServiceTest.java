package com.jornada.api.service;

import com.jornada.api.dto.depoimentos.DadosListagemDepoimento;
import com.jornada.api.repository.DepoimentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DepoimentoServiceTest {

    @Mock
    private DepoimentoRepository depoimentoRepository;

    @InjectMocks
    private DepoimentoService depoimentoService;

    @Test
    void testFindRandomDepoimentos() {
        // Criando uma lista simulada de depoimentos
        List<DadosListagemDepoimento> mockDepoimentos = new ArrayList<>();
        mockDepoimentos.add(new DadosListagemDepoimento(1L, "Cleber", "Depoimento 1", "aloimagem.com"));
        mockDepoimentos.add(new DadosListagemDepoimento(2L, "Lunna", "Depoimento 2", "aloimagem.com"));
        mockDepoimentos.add(new DadosListagemDepoimento(3L,"Silvia", "Depoimento 3", "aloimagem.com"));

        // Definindo o comportamento do mock do repositório
        when(depoimentoRepository.findRandomDepoimentos()).thenReturn(mockDepoimentos);

        // Chamando o método do serviço
        List<DadosListagemDepoimento> result = depoimentoService.findRandomDepoimentos();

        // Verificando se o método do repositório foi chamado
        verify(depoimentoRepository).findRandomDepoimentos();

        // Verificando o resultado
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("Depoimento 1", result.get(0).textoDepoimento());
        assertEquals("Depoimento 2", result.get(1).textoDepoimento());
        assertEquals("Depoimento 3", result.get(2).textoDepoimento());
    }

    @Test
    void testFindRandomDepoimentos_NoDepoimentosFound() {
        // Definindo o comportamento do mock do repositório
        when(depoimentoRepository.findRandomDepoimentos()).thenReturn(Collections.emptyList());

        // Chamando o método do serviço
        List<DadosListagemDepoimento> result = depoimentoService.findRandomDepoimentos();

        // Verificando o resultado
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindRandomDepoimentos_ExceptionInRepository() {
        // Definindo o comportamento do mock do repositório para lançar uma exceção
        when(depoimentoRepository.findRandomDepoimentos()).thenThrow(new RuntimeException("Erro no repositório"));

        // Testando a captura da exceção
        assertThrows(RuntimeException.class, () -> depoimentoService.findRandomDepoimentos());
    }

    @Test
    void testFindRandomDepoimentos_Aleatoriedade() {
        // Definindo vários conjuntos simulados de depoimentos
        List<DadosListagemDepoimento> depoimentosSet1 = createMockDepoimentos();
        List<DadosListagemDepoimento> depoimentosSet2 = createMockDepoimentos();

        // Definindo comportamento do mock para retornar os conjuntos simulados de forma alternada
        when(depoimentoRepository.findRandomDepoimentos())
                .thenReturn(depoimentosSet1)
                .thenReturn(depoimentosSet2);

        // Chamando o método do serviço várias vezes
        List<DadosListagemDepoimento> result1 = depoimentoService.findRandomDepoimentos();
        List<DadosListagemDepoimento> result2 = depoimentoService.findRandomDepoimentos();

        // Verificando que os resultados são diferentes
        assertNotEquals(result1, result2);
    }
    private List<DadosListagemDepoimento> createMockDepoimentos() {
        List<DadosListagemDepoimento> depoimentos = new ArrayList<>();
        depoimentos.add(new DadosListagemDepoimento(1L, "Cleber", "Depoimento 1", "aloimagem.com"));
        depoimentos.add(new DadosListagemDepoimento(2L, "Lunna", "Depoimento 2", "aloimagem2.com"));
        depoimentos.add(new DadosListagemDepoimento(3L, "Silvia", "Depoimento 3", "aloimagem3.com"));
        depoimentos.add(new DadosListagemDepoimento(4L, "Maria", "Depoimento 4", "aloimagem4.com"));
        depoimentos.add(new DadosListagemDepoimento(5L, "Joana", "Depoimento 5", "aloimagem5.com"));
        depoimentos.add(new DadosListagemDepoimento(6L, "Joanaa", "Depoimento 6", "aloimagem6.com"));
        // Adicione mais depoimentos simulados conforme necessário

        return depoimentos;
    }




}