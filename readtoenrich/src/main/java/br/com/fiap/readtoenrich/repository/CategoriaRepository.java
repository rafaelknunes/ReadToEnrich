package br.com.fiap.readtoenrich.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// Importa Pageable para fazer paginação. Do data Domain
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.fiap.readtoenrich.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

    // Filtro por nome: Criado automaticamente pelo Spring Data
    Page<Categoria> findByCategoriaNome(String categoria, Pageable pageable);

    // JPQL - Usado para fazer consultas no banco de dados com SQL
    // Filtra por mês da data de criação
    @Query("SELECT c FROM Categoria c WHERE MONTH(c.dataCriacao) = :mes")
    Page<Categoria> findByMes(@Param("mes") Integer mes, Pageable pageable);

    // Filtra por nome da categoria e mês
    @Query("SELECT c FROM Categoria c WHERE c.categoria.nome = ?1 AND MONTH(c.dataCriacao) = ?2")
    Page<Categoria> findByCategoriaNomeMes(@Param("categoria") String categoria, @Param ("mes") Integer mes, Pageable pageable);

}
