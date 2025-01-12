package br.com.catalogolivros.principal;

import br.com.catalogolivros.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal implements CommandLineRunner {

    @Autowired
    private LivroService livroService;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("""
                    Escolha o número de sua opção:
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar livros em um determinado idioma
                    0 - Sair
                    """);

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println("Digite o título do livro:");
                    String titulo = scanner.nextLine();
                    System.out.println(livroService.buscarLivroPorTitulo(titulo));
                }
                case 2 -> livroService.listarLivros().forEach(System.out::println);
                case 3 -> livroService.listarNomesAutores().forEach(System.out::println);
                case 4 -> {
                    System.out.println("Digite o idioma (es, en, fr, pt):");
                    String idioma = scanner.nextLine();
                    try {
                        livroService.listarPorIdioma(idioma).forEach(System.out::println);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}