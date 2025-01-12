package br.com.catalogolivros.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "ano_nascimento")
    private Integer birthYear;

    @Column(name = "ano_falecimento")
    private Integer deathYear;

    public Autor() {}

    public Autor(String nome, Integer birthYear, Integer deathYear) {
        this.nome = nome;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getBirthYear() { return birthYear; }
    public void setBirthYear(Integer birthYear) { this.birthYear = birthYear; }

    public Integer getDeathYear() { return deathYear; }
    public void setDeathYear(Integer deathYear) { this.deathYear = deathYear; }
}