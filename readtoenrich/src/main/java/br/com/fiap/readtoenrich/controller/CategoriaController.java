package br.com.fiap.readtoenrich.controller;


import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.readtoenrich.model.Categoria;
import br.com.fiap.readtoenrich.repository.CategoriaRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("categoria")
@Slf4j
public class CategoriaController {
    
    @Autowired
    CategoriaRepository categoriaRepository;

    // Método para buscar todas as categorias
    @GetMapping
    public List<Categoria> index() {
        return categoriaRepository.findAll();
    }

    // Método para criar uma nova categoria
    @PostMapping
    @ResponseStatus(CREATED)
    public Categoria create(@RequestBody @Valid Categoria categoria) {
        log.info("cadastrando categoria: {}", categoria);
        return categoriaRepository.save(categoria);
    }

    // Método para buscar uma categoria por id
    @GetMapping("{id}")
    public ResponseEntity<Categoria> get(@PathVariable Long id) {
        log.info("Buscar por id: {}", id);

        return categoriaRepository
                    .findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());

    }

    // Método para deletar uma categoria por id
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("apagando categoria {}", id);

        verificarSeExisteCategoria(id);
        categoriaRepository.deleteById(id);
    }

    // Método para atualizar uma categoria por id
    @PutMapping("{id}")
    public Categoria update(@PathVariable Long id, @RequestBody Categoria categoria){
        log.info("atualizando categoria id {} para {}", id, categoria);
        
        verificarSeExisteCategoria(id);

        categoria.setId(id);
        return categoriaRepository.save(categoria);

    }
    
    // Método para verificar se a categoria existe por id
    private void verificarSeExisteCategoria(Long id) {
        categoriaRepository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada" )
            );
    }

}
