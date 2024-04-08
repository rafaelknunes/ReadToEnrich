// Este código define uma classe Livro que é anotada para funcionar com JPA (Java Persistence API), 
// especificamente usando a especificação Jakarta Persistence, e também utiliza a biblioteca Lombok para reduzir 
// a verbosidade do código, automaticamente gerando getters, setters, e outros métodos comuns.

package br.com.fiap.readtoenrich.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

// Esta é uma anotação do Lombok que é uma espécie de atalho para @ToString, getters, setters, etc.
@Data 
// Esta anotação faz com que a classe Livro seja mapeada para uma tabela no banco de dados.
@Entity
// A classe Livro contém vários campos que representam as propriedades de um livro, como titulo, autor, isbn, categoria, 
// e imagem. Estes campos serão mapeados para colunas na tabela de livros no banco de dados.
public class Livro {
    // Marca o campo id como a chave primária da tabela de livros no banco de dados.
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Pattern(regexp = "^[A-Za-z0-9 ]*$" , message = "O título do livro deve conter apenas letras e números")
    private String titulo;
    private String autor;
    private String isbn;
    private String categoria;
    private String imagem;
}