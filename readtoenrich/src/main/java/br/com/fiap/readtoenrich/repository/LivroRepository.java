package br.com.fiap.readtoenrich.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.readtoenrich.model.Livro;

// LivroRepository: Deve ser uma interface que você definiu em algum lugar do seu código, que estende JpaRepository. 
// O JpaRepository é uma interface do Spring Data JPA que fornece métodos CRUD genéricos para trabalhar com a persistência 
// dos dados. Ao estender JpaRepository, sua interface LivroRepository herda esses métodos genéricos.

public interface LivroRepository extends JpaRepository<Livro, Long> {


}