package br.com.catalogolivros.service;

import br.com.catalogolivros.dto.GutendexResponse;
import br.com.catalogolivros.dto.LivroDTO;
import br.com.catalogolivros.model.Autor;
import br.com.catalogolivros.model.Livro;
import br.com.catalogolivros.repository.AutorRepository;
import br.com.catalogolivros.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private static final String GUTENDEX_API_URL = "https://gutendex.com/books";
    private final RestTemplate restTemplate;
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository, AutorRepository autorRepository, RestTemplate restTemplate) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.restTemplate = restTemplate;
    }

    public LivroDTO buscarLivroPorTitulo(String titulo) {
        try {
            String url = GUTENDEX_API_URL + "?search=" + URLEncoder.encode(titulo, StandardCharsets.UTF_8);
            var response = restTemplate.getForObject(url, GutendexResponse.class);

            if (response == null || response.getResults().isEmpty()) {
                System.out.println("Livro não encontrado. Tente outro título.");
                return null;
            }

            var livroData = response.getResults().get(0);
            var authorData = livroData.getAuthors().get(0);

            String nomeAutor = authorData.getName();
            Integer birthYear = authorData.getBirthYear() != null ? authorData.getBirthYear() : 0;
            Integer deathYear = authorData.getDeathYear() != null ? authorData.getDeathYear() : 0;

            Autor autor = autorRepository.findByNome(nomeAutor)
                    .orElseGet(() -> {
                        Autor novoAutor = new Autor(nomeAutor, birthYear, deathYear);
                        System.out.println("Novo autor salvo: " + novoAutor.getNome() +
                                " (Ano de Nascimento: " + (birthYear != 0 ? birthYear : "Desconhecido") +
                                ", Ano de Falecimento: " + (deathYear != 0 ? deathYear : "Desconhecido") + ")");
                        return autorRepository.save(novoAutor);
                    });

            if (!birthYear.equals(autor.getBirthYear()) || !deathYear.equals(autor.getDeathYear())) {
                autor.setBirthYear(birthYear);
                autor.setDeathYear(deathYear);
                autorRepository.save(autor);
                System.out.println("Autor atualizado: " + autor.getNome() +
                        " (Ano de Nascimento: " + (autor.getBirthYear() != 0 ? autor.getBirthYear() : "Desconhecido") +
                        ", Ano de Falecimento: " + (autor.getDeathYear() != 0 ? autor.getDeathYear() : "Desconhecido") + ")");
            }

            Livro livroExistente = livroRepository.findByTituloAndAutorNome(livroData.getTitle(), nomeAutor).orElse(null);
            if (livroExistente != null) {
                System.out.println("Livro já existente no banco de dados.");
                return new LivroDTO(livroExistente);
            }

            Livro livro = new Livro(livroData.getTitle(), livroData.getLanguages().get(0), livroData.getDownloadCount(), autor);
            livroRepository.save(livro);
            System.out.println("Livro salvo: " + livro.getTitulo());
            return new LivroDTO(livro);

        } catch (Exception e) {
            System.err.println("Erro ao buscar o livro: " + e.getMessage());
            return null;
        }
    }

    public List<LivroDTO> listarLivros() {
        return livroRepository.findAll().stream().map(LivroDTO::new).collect(Collectors.toList());
    }

    public List<String> listarNomesAutores() {
        return autorRepository.findAll().stream().map(Autor::getNome).distinct().collect(Collectors.toList());
    }

    public List<LivroDTO> listarPorIdioma(String idioma) {
        List<Livro> livros = livroRepository.findByIdioma(idioma);
        if (livros.isEmpty()) {
            throw new RuntimeException("Não existe livros nesse idioma no banco de dados.");
        }
        return livros.stream().map(LivroDTO::new).collect(Collectors.toList());
    }
}