package br.com.fiap.readtoenrich.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.readtoenrich.model.Categoria;
import br.com.fiap.readtoenrich.repository.CategoriaRepository;
import jakarta.validation.Valid;

// Importa Pageable para fazer paginação. Do data Domain
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

@RestController
@RequestMapping("categoria")
public class CategoriaController {
    
    @Autowired
    CategoriaRepository categoriaRepository;

    // ...
    @GetMapping
    public Page<Categoria> index(
            @RequestParam(required = false) String categoria, 
            @RequestParam(required = false) Integer mes,
            @PageableDefault(sort = "dataCriacao", direction = Direction.DESC) Pageable pageable
            ){

        if (categoria != null){
            return categoriaRepository.findByCategoriaNome(categoria, pageable);
        }
        if (mes != null){
            return categoriaRepository.findByMes(mes, pageable);
        }
        if (categoria != null && mes != null){
            return categoriaRepository.findByCategoriaNomeMes(categoria, mes, pageable);
        }
        // Retorno por paginação. Náo retorna lista mas sim um objeto Page
        return categoriaRepository.findAll(pageable);
    }

    @PostMapping
    public Categoria create(@RequestBody @Valid Categoria categoria){
        return categoriaRepository.save(categoria);
    }
}
