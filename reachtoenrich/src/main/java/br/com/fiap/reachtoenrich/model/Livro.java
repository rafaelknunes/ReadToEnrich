package br.com.fiap.reachtoenrich.model;

public record Livro(String titulo, String autor, String isbn, String categoria, String imagem) {

    public Livro(String titulo, String autor, String isbn, String categoria, String imagem){
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.categoria = categoria;
        this.imagem = imagem;
    }

} 