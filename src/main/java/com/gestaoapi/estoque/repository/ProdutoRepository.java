package com.gestaoapi.estoque.repository;

import com.gestaoapi.estoque.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProdutoRepository {

    private final List<Produto> produtos = Collections.synchronizedList(new ArrayList<>());
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Produto salvar(Produto produto) {
        produto.setId(idGenerator.getAndIncrement());
        produtos.add(produto);
        return produto;
    }

    public List<Produto> buscarTodos() {
        return new ArrayList<>(produtos);
    }
}