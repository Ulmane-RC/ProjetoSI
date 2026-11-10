package com.gestaoapi.estoque.controller;

public class ProdutoController {

import com.gestaoapi.dto.ProdutoRequestDTO;
import com.gestaoapi.dto.ProdutoResponseDTO;
import com.gestaoapi.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/produtos")
    @RequiredArgsConstructor
    public class ProdutoController {

        private final ProdutoService service;

        @PostMapping
        public ResponseEntity<ProdutoResponseDTO> cadastrar(@RequestBody ProdutoRequestDTO dto) {
            ProdutoResponseDTO response = service.cadastrar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        @GetMapping
        public ResponseEntity<List<ProdutoResponseDTO>> listar() {
            List<ProdutoResponseDTO> lista = service.listar();
            return ResponseEntity.ok(lista);
        }
    }
}
