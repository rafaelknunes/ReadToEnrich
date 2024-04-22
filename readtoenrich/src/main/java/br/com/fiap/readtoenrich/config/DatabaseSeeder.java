package br.com.fiap.readtoenrich.config;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.readtoenrich.model.Categoria;
import br.com.fiap.readtoenrich.model.Livro;
import br.com.fiap.readtoenrich.repository.CategoriaRepository;
import br.com.fiap.readtoenrich.repository.LivroRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner{
    
    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    LivroRepository livroRepository;

    @Override
    public void run(String... args) throws Exception {
        categoriaRepository.saveAll(
            List.of(
                Categoria.builder().id(1L).nome("Romance").dataCriacao(Date.valueOf("2024-01-23")).build(),
                Categoria.builder().id(2L).nome("Ficção").dataCriacao(Date.valueOf("2024-03-01")).build(),
                Categoria.builder().id(3L).nome("Ciência").dataCriacao(Date.valueOf("2024-02-21")).build(),
                Categoria.builder().id(4L).nome("Aventura").dataCriacao(Date.valueOf(LocalDate.now())).build()
            )
            );
        livroRepository.saveAll(
            List.of(
                Livro.builder()
                    .id(1L)
                    .titulo("O senhor dos anéis: A sociedade do anel")
                    .autor("J.R.R. Tolkien")
                    .isbn("978-0-395-19395-7")
                    .categoria(categoriaRepository.findById(1L).get())
                    .imagem("https://lojaleiturinha.vtexassets.com/arquivos/ids/156464-800-800?v=638337749713470000&width=800&height=800&aspect=true")
                    .build(),
                    Livro.builder()
                    .id(2L)
                    .titulo("O senhor dos anéis: As duas torres")
                    .autor("J.R.R. Tolkien")
                    .isbn("978-0-395-19395-7")
                    .categoria(categoriaRepository.findById(1L).get())
                    .imagem("https://lojaleiturinha.vtexassets.com/arquivos/ids/156464-800-800?v=638337749713470000&width=800&height=800&aspect=true")
                    .build(),
                    Livro.builder()
                    .id(3L)
                    .titulo("O senhor dos anéis: O retorno do rei")
                    .autor("J.R.R. Tolkien")
                    .isbn("978-0-395-19395-7")
                    .categoria(categoriaRepository.findById(1L).get())
                    .imagem("https://lojaleiturinha.vtexassets.com/arquivos/ids/156464-800-800?v=638337749713470000&width=800&height=800&aspect=true")
                    .build()
            )
            );
    }





}
