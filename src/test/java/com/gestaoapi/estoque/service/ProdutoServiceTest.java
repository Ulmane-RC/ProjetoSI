package com.gestaoapi.estoque.service;

import com.gestaoapi.estoque.dto.ProdutoRequestDTO;
import com.gestaoapi.estoque.dto.ProdutoResponseDTO;
import com.gestaoapi.estoque.model.Produto;
import com.gestaoapi.estoque.repository.ProdutoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private ProdutoService service;

    @Test
    @DisplayName("Teste 1: Deve cadastrar um produto válido com sucesso")
    void CadastrarProdutoValido() {
        // Cenário (Arrange)
        ProdutoRequestDTO dto = new ProdutoRequestDTO(
                "Teclado Mecânico RGB",
                "PERIFERICOS",
                259.90f,
                15,
                true
        );

        Produto produtoSalvo = new Produto(1L, "Teclado Mecânico RGB", "PERIFERICOS", 259.90f, 15, true);
        when(repository.save(any(Produto.class))).thenReturn(produtoSalvo);

        // Ação (Act)
        ProdutoResponseDTO resultado = service.cadastrar(dto);

        // Verificação (Assert)
        assertNotNull(resultado);
        assertEquals(1L, resultado.id());
        assertEquals("Teclado Mecânico RGB", resultado.descricao());
        assertEquals(259.90f, resultado.preco());
        verify(repository, times(1)).save(any(Produto.class));
    }

    @Test
    @DisplayName("Teste 2: Deve bloquear cadastro quando o preço for zero ou negativo")
    void LancarExcecaoQuandoPrecoForInvalido() {
        // Cenário (Arrange)
        ProdutoRequestDTO dtoInvalido = new ProdutoRequestDTO(
                "Mouse Pad",
                "PERIFERICOS",
                0.0f,
                10,
                true
        );

        // Ação e Verificação (Act & Assert)
        assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(dtoInvalido);
        });

        // Garante que o banco nunca foi chamado
        verify(repository, never()).save(any(Produto.class));
    }

    @Test
    @DisplayName("Teste 3: Deve retornar a lista de produtos cadastrados na consulta")
    void ListarProdutosCadastrados() {
        // Cenário (Arrange)
        Produto p1 = new Produto(1L, "Teclado Mecânico", "PERIFERICOS", 259.90f, 15, true);
        Produto p2 = new Produto(2L, "Monitor 24", "MONITORES", 899.00f, 8, true);
        when(repository.findAll()).thenReturn(List.of(p1, p2));

        // Ação (Act)
        List<ProdutoResponseDTO> lista = service.listar();

        // Verificação (Assert)
        assertEquals(2, lista.size());
        assertEquals("Teclado Mecânico", lista.get(0).descricao());
        assertEquals("Monitor 24", lista.get(1).descricao());
        verify(repository, times(1)).findAll();
    }
}