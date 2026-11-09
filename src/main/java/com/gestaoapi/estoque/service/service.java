package com.gestaoapi.estoque.service;

public class service { package com.gestaoapi.service;

import com.gestaoapi.dto.ProdutoRequestDTO;
import com.gestaoapi.dto.ProdutoResponseDTO;
import com.gestaoapi.model.Produto;
import com.gestaoapi.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class ProdutoService {

        private final ProdutoRepository repository;

        public ProdutoResponseDTO cadastrar(ProdutoRequestDTO dto) {
            if (dto.preco() == null || dto.preco().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Preço inválido: O preço do produto deve ser maior que zero.");
            }

            Produto produto = new Produto(
                    null,
                    dto.descricao(),
                    dto.categoria(),
                    dto.preco(),
                    dto.quantidade(),
                    dto.status() != null ? dto.status() : true
            );

            Produto produtoSalvo = repository.salvar(produto);

            return new ProdutoResponseDTO(
                    produtoSalvo.getId(),
                    produtoSalvo.getDescricao(),
                    produtoSalvo.getCategoria(),
                    produtoSalvo.getPreco(),
                    produtoSalvo.getQuantidade(),
                    produtoSalvo.getStatus()
            );
        }

        public List<ProdutoResponseDTO> listar() {
            return repository.buscarTodos().stream()
                    .map(p -> new ProdutoResponseDTO(
                            p.getId(), p.getDescricao(), p.getCategoria(), p.getPreco(), p.getQuantidade(), p.getStatus()
                    ))
                    .toList();
        }
    }
}
