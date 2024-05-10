package br.com.fiap.readtoenrich.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.readtoenrich.model.Livro;
import br.com.fiap.readtoenrich.repository.LivroRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

//@RestController: Esta anotação indica que a classe é um Controller, um componente do Spring que lida com requisições HTTP. Os dados de retorno são automaticamente escritos no corpo da resposta, não precisando de @ResponseBody.
@RestController
//@RequestMapping("livro"): Define a rota base para todos os métodos dentro desse Controller. Assim, todas as requisições para esse Controller começarão com /livro.
@RequestMapping("livro")
@Slf4j
@CacheConfig(cacheNames = "livros")
@Tag(name = "livros", description = "Endpoint relacionados com livros cadastrados")
public class LivroController {

    // Aqui temos uma simulação de um repositório (banco de dados), utilizando uma lista em memória para armazenar objetos `Livro`. Em um cenário real, provavelmente estaríamos injetando um repositório real aqui, possivelmente utilizando `@Autowired`.
    //List<Livro> repository = new ArrayList<>();

    // Esta anotação é usada pelo Spring para realizar a injeção de dependência. O Spring irá procurar um 
    // bean que corresponda ao tipo LivroRepository e injetá-lo automaticamente nesta variável. Isso elimina 
    // a necessidade de você instanciar manualmente LivroRepository ou gerenciar seu ciclo de vida.
    @Autowired 
    LivroRepository livroRepository;

    // Método que retorna todos os livros cadastrados no repositório.
    // Se usuário passar parâmetro `titulo`, retorna apenas os livros que contém o título informado.
    @GetMapping
    @Cacheable
    @Operation(summary = "Lista todos os livros cadastradas no sistema.", description = "Endpoint que retorna um page de objetos do tipo livro os livros cadastrados")
    public Page<Livro> index(
        @RequestParam(required = false) String titulo,
        @ParameterObject @PageableDefault(sort = "titulo", direction = Direction.DESC) Pageable pageable
    ){
        if (titulo != null){
            return livroRepository.findByTitulo(titulo, pageable);
        }
        return livroRepository.findAll(pageable);
    }

    // Anotação que indica que o método `create(@RequestBody Livro livro)` será chamado para tratar requisições POST em `/livro/cadastrar`. Este método é responsável por adicionar um novo `Livro` ao repositório.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Define o status HTTP da resposta como 201 Created.
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Erro de validação da livro"),
            @ApiResponse(responseCode = "201", description = "Livro cadastrado com sucesso")
    })
    @Operation(summary = "Cadastro de um novo livro.")
    public Livro create(@ParameterObject @RequestBody @Valid Livro livro) {
        log.info("Cadastrando livro: {}", livro);
        
        // Validação simplificada do título do livro
        if (livro.getTitulo() == null || livro.getTitulo().isEmpty()) {

            log.info("Falha ao cadastrar livro: título inválido");

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Título do livro é inválido");
        }

        return livroRepository.save(livro); // Salva o livro no banco de dados e retorna o livro salvo.
    }

    @GetMapping("{id}")
    @Operation(summary = "Busca livro por id.")
    public ResponseEntity<Livro> get(@PathVariable Long id) {
        log.info("Buscar por id: {}", id);

        return livroRepository
                .findById(id)
                .map(c -> ResponseEntity.ok(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deleta livro por id.")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("Apagando livro {}", id);

        verificarSeExisteLivro(id);

        livroRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("{id}")
    @Operation(summary = "Edita livro por id.")
    public ResponseEntity<Object> update(@PathVariable Long id, @ParameterObject @RequestBody Livro livro){
        log.info("Atualizando livro de id {} para {}", id, livro);
        
        verificarSeExisteLivro(id);

        livro.setId(id);

        livroRepository.save(livro);

        return ResponseEntity.ok(livro);
    }

    private void verificarSeExisteLivro(Long id) {
        livroRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }
}