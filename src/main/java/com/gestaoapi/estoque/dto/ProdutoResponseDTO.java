package com.gestaoapi.estoque.dto;

public record ProdutoResponseDTO(
        Long id,
        String descricao,
        String categoria,
        Float preco,
        Integer quantidade,
        Boolean status
)
{}