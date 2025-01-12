package br.com.catalogolivros.controller;

import br.com.catalogolivros.dto.LivroDTO;
import br.com.catalogolivros.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping("/titulo/{titulo}")
    public LivroDTO buscarPorTitulo(@PathVariable String titulo) {
        return livroService.buscarLivroPorTitulo(titulo);
    }

    @GetMapping
    public List<LivroDTO> listarTodos() {
        return livroService.listarLivros();
    }

    @GetMapping("/autores")
    public List<String> listarAutores() {
        return livroService.listarNomesAutores();
    }

    @GetMapping("/idioma/{idioma}")
    public List<LivroDTO> listarPorIdioma(@PathVariable String idioma) {
        return livroService.listarPorIdioma(idioma);
    }
}
