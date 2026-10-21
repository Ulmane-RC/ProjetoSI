package com.gestaoapi.estoque.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor

public class Produto {
    private Long id;
    private String descricao;
    private String categoria;
    private Integer quantidade;
    private Float preco;
    private Boolean status;

}
