package com.gestaoapi.estoque.dto;

public record ProdutoRequestDTO (
    String descricao,
    String categoria,
    Float preco,
    Integer quantidade,
    Boolean status
){}
