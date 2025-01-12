package br.com.catalogolivros.dto;

import br.com.catalogolivros.model.Livro;

public record LivroDTO(String titulo, String nomeAutor, String idioma, int numeroDownloads) {

    // Construtor que recebe um objeto Livro
    public LivroDTO(Livro livro) {
        this(livro.getTitulo(),
                livro.getAutor().getNome(),
                livro.getIdioma(),
                livro.getNumeroDownloads());
    }
}
