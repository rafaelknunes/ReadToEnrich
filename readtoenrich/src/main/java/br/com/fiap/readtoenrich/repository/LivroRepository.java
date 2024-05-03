package br.com.fiap.readtoenrich.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.readtoenrich.model.Livro;

// LivroRepository: Deve ser uma interface que você definiu em algum lugar do seu código, que estende JpaRepository. 
// O JpaRepository é uma interface do Spring Data JPA que fornece métodos CRUD genéricos para trabalhar com a persistência 
// dos dados. Ao estender JpaRepository, sua interface LivroRepository herda esses métodos genéricos.

public interface LivroRepository extends JpaRepository<Livro, Long> {

    //JPQL - Java Persistence Query Language

    // Inclui query para buscar por título do livro mas deixando explícito a query
    @Query("SELECT m FROM Livro m WHERE m.titulo LIKE %:titulo%")
    Page<Livro> findByTitulo(@Param("titulo") String titulo, Pageable pageable);

}