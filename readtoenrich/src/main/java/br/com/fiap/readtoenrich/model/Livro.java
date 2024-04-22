// Este código define uma classe Livro que é anotada para funcionar com JPA (Java Persistence API), 
// especificamente usando a especificação Jakarta Persistence, e também utiliza a biblioteca Lombok para reduzir 
// a verbosidade do código, automaticamente gerando getters, setters, e outros métodos comuns.

package br.com.fiap.readtoenrich.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Esta é uma anotação do Lombok que é uma espécie de atalho para @ToString, getters, setters, etc.
@Data 
// Esta anotação faz com que a classe Livro seja mapeada para uma tabela no banco de dados.
@Entity
// A classe Livro contém vários campos que representam as propriedades de um livro, como titulo, autor, isbn, categoria, 
// e imagem. Estes campos serão mapeados para colunas na tabela de livros no banco de dados.
@Builder // Anotação para funcionar o builder no DatabaseSeeder
@NoArgsConstructor // Anotação para funcionar o builder no DatabaseSeeder
@AllArgsConstructor // Anotação para funcionar o builder no DatabaseSeeder
public class Livro {
    // Marca o campo id como a chave primária da tabela de livros no banco de dados.
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Pattern(regexp = "^[\\p{L}\\p{M}0-9 .,'\"-:]*$", message = "O título do livro deve conter apenas letras, números e pontuação comum.")
    private String titulo;
    private String autor;
    private String isbn;
    // A anotação @ManyToOne indica que a propriedade categoria é uma chave estrangeira que se relaciona com a tabela de categorias. Muitos livros podem estar relacionados com uma categoria.
    // Veja que no banco de dados, na tabela livro, será criado um campo categoria_id que será uma chave estrangeira para a tabela categoria.
    @ManyToOne
    private Categoria categoria;
    private String imagem;
}