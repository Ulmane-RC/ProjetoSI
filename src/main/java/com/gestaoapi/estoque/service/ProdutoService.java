package com.gestaoapi.estoque.service;

import com.gestaoapi.estoque.dto.ProdutoRequestDTO;
import com.gestaoapi.estoque.dto.ProdutoResponseDTO;
import com.gestaoapi.estoque.model.Produto;
import com.gestaoapi.estoque.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoResponseDTO cadastrar(ProdutoRequestDTO dto) {
        if (dto.preco() == null || dto.preco() <= 0.0f) {
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

        Produto produtoSalvo = repository.save(produto);

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
        return repository.findAll().stream()
                .map(p -> new ProdutoResponseDTO(
                        p.getId(), p.getDescricao(), p.getCategoria(), p.getPreco(), p.getQuantidade(), p.getStatus()
                ))
                .toList();
    }
}