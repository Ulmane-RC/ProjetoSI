package com.gestaoapi.estoque.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    private Long id;
    private String descricao;
    private String categoria;
    private Float preco;
    private Integer quantidade;
    private Boolean status;

}
