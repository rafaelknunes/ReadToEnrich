package br.com.fiap.readtoenrich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.readtoenrich.model.Categoria;
import br.com.fiap.readtoenrich.repository.CategoriaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("categoria")
public class CategoriaController {
    
    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping
    public Categoria create(@RequestBody @Valid Categoria categoria){
        return categoriaRepository.save(categoria);
    }
}
