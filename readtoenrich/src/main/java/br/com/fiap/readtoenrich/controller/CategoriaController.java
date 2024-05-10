package br.com.fiap.readtoenrich.controller;


import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("categoria")
@Slf4j
@CacheConfig(cacheNames = "categorias")
@Tag(name = "categorias", description = "Endpoint relacionados com categorias de livros")
public class CategoriaController {
    
    @Autowired
    CategoriaRepository categoriaRepository;

    // Método para buscar todas as categorias
    @GetMapping
    // Esta anotação indica que o resultado deste método deve ser armazenado em cache. 
    // O valor passado como argumento é o nome do cache.
    @Cacheable
    // Documentação
    @Operation(summary = "Lista todas as categorias cadastradas no sistema.", description = "Endpoint que retorna um array de objetos do tipo categoria com todas as categorias do sistema atual")
    public List<Categoria> index() {
        return categoriaRepository.findAll();
    }

    // Método para criar uma nova categoria
    @PostMapping
    @ResponseStatus(CREATED)
    @CacheEvict(allEntries = true) // Esta anotação é usada para limpar o cache. O valor passado como argumento é o nome do cache.
    @Operation(summary = "Cadastra uma nova categoria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Erro de validação da categoria"),
            @ApiResponse(responseCode = "201", description = "Categoria cadastrada com sucesso")
    })
    public Categoria create(@ParameterObject @RequestBody @Valid Categoria categoria) {
        log.info("cadastrando categoria: {}", categoria);
        return categoriaRepository.save(categoria);
    }

    // Método para buscar uma categoria por id
    @GetMapping("{id}")
    @Operation(summary = "Busca categoria por id.")
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
    @Operation(summary = "Deleta categoria por id.")
    public void destroy(@PathVariable Long id) {
        log.info("apagando categoria {}", id);

        verificarSeExisteCategoria(id);
        categoriaRepository.deleteById(id);
    }

    // Método para atualizar uma categoria por id
    @PutMapping("{id}")
    @CacheEvict(allEntries = true)
    @Operation(summary = "Edita categoria por id.")
    public Categoria update(@PathVariable Long id, @ParameterObject @RequestBody Categoria categoria){
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
