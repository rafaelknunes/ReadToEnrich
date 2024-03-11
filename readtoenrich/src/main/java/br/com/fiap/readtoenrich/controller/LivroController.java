package br.com.fiap.readtoenrich.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import br.com.fiap.readtoenrich.model.Livro;
import br.com.fiap.readtoenrich.repository.LivroRepository;

//@RestController: Esta anotação indica que a classe é um Controller, um componente do Spring que lida com requisições HTTP. Os dados de retorno são automaticamente escritos no corpo da resposta, não precisando de @ResponseBody.
@RestController
//@RequestMapping("livro"): Define a rota base para todos os métodos dentro desse Controller. Assim, todas as requisições para esse Controller começarão com /livro.
@RequestMapping("livro")
public class LivroController {

    // Esta linha inicializa uma instância de `Logger`, que é usada para registrar mensagens (logs), o que é crucial para depuração e monitoramento de aplicações.
    Logger log = LoggerFactory.getLogger(getClass());

    // Aqui temos uma simulação de um repositório (banco de dados), utilizando uma lista em memória para armazenar objetos `Livro`. Em um cenário real, provavelmente estaríamos injetando um repositório real aqui, possivelmente utilizando `@Autowired`.
    List<Livro> repository = new ArrayList<>();

    // Esta anotação é usada pelo Spring para realizar a injeção de dependência. O Spring irá procurar um 
    // bean que corresponda ao tipo LivroRepository e injetá-lo automaticamente nesta variável. Isso elimina 
    // a necessidade de você instanciar manualmente LivroRepository ou gerenciar seu ciclo de vida.
    @Autowired 
    LivroRepository livroRepository;

    // Esta anotação indica que o método `getBookList()` será chamado quando uma requisição GET para `/livro/buscar` for feita. O método retorna uma lista de livros, que é o conteúdo atual do "repositório".
    @GetMapping("/buscar")
    public List<Livro> getBookList() {
        // O método findAll() é um dos métodos herdados de JpaRepository. 
        // Ele busca todas as instâncias de Livro no banco de dados e as retorna em uma lista.
        return livroRepository.findAll();
    }

    // Anotação que indica que o método `create(@RequestBody Livro livro)` será chamado para tratar requisições POST em `/livro/cadastrar`. Este método é responsável por adicionar um novo `Livro` ao repositório.
    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED) // Define o status HTTP da resposta como 201 Created.
    public Livro createBook(@RequestBody Livro livro) {
        log.info("Cadastrando livro: {}", livro);
        
        // Validação simplificada do título do livro
        if (livro.getTitulo() == null || livro.getTitulo().isEmpty()) {
            log.info("Falha ao cadastrar livro: título inválido");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Título do livro é inválido");
        }

        return livroRepository.save(livro); // Salva o livro no banco de dados e retorna o livro salvo.
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Livro> get(@PathVariable Long id) {
        log.info("Buscar por id: {}", id);

        return livroRepository
                .findById(id)
                .map(c -> ResponseEntity.ok(c))
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("Apagando livro {}", id);

        verificarSeExisteLivro(id);

        livroRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Livro livro){
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