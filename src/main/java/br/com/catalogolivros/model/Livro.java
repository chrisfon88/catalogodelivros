package br.com.catalogolivros.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idioma;

    @Column(name = "numero_downloads")
    private int numeroDownloads;

    @ManyToOne
    @JoinColumn(name = "autor_id", referencedColumnName = "id")
    private Autor autor;

    public Livro() {}

    public Livro(String titulo, String idioma, int numeroDownloads, Autor autor) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.numeroDownloads = numeroDownloads;
        this.autor = autor;
    }

    // Getters
    public String getTitulo() { return titulo; }
    public String getIdioma() { return idioma; }
    public int getNumeroDownloads() { return numeroDownloads; }
    public Autor getAutor() { return autor; }
}