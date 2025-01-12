package br.com.catalogolivros.repository;

import br.com.catalogolivros.model.Autor;
import br.com.catalogolivros.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT a FROM Autor a WHERE a.nome = :nome")
    Optional<Autor> findAutorByNome(@Param("nome") String nome);

    @Query("SELECT l FROM Livro l WHERE l.titulo = :titulo AND l.autor.nome = :nomeAutor")
    Optional<Livro> findByTituloAndAutorNome(@Param("titulo") String titulo, @Param("nomeAutor") String nomeAutor);

    List<Livro> findByIdioma(String idioma);

    @Modifying
    @Transactional
    @Query("UPDATE Autor a SET a.birthYear = :birthYear, a.deathYear = :deathYear WHERE a.nome = :nome")
    void atualizarAutor(@Param("birthYear") Integer birthYear, @Param("deathYear") Integer deathYear, @Param("nome") String nome);
}