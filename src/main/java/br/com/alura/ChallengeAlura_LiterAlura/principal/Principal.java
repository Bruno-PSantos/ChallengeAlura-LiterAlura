package br.com.alura.ChallengeAlura_LiterAlura.principal;

import br.com.alura.ChallengeAlura_LiterAlura.model.*;
import br.com.alura.ChallengeAlura_LiterAlura.repository.AutorRepository;
import br.com.alura.ChallengeAlura_LiterAlura.repository.LivroRepository;
import br.com.alura.ChallengeAlura_LiterAlura.service.ConsumoAPI;
import br.com.alura.ChallengeAlura_LiterAlura.service.ConverteDados;

import java.util.*;

public class Principal {

    private Scanner entrada = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books?search=";

    private LivroRepository livroRepositorio;
    private AutorRepository autorRepositorio;

    public Principal (LivroRepository livroRepositorio, AutorRepository autorRepositorio) {
        this.livroRepositorio = livroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                    -----+*+-----
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determindado ano
                    5 - Listar livros em um determinado idioma
                                    
                    0 - Sair
                    -----+*+-----                            
                    Digite o número da opção desejada: """;

            System.out.println(menu);
            opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroNaWeb();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEmDeterminadoAno();
                    break;
                case 5:
                    listarLivrosEmDeterminadoIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }


    private void salvarDados(DadosResultado dados) {
        DadosLivro dadosLivro = dados.results().getFirst();
        DadosAutor dadosAutor = dadosLivro.authors().getFirst();

        List<Livro> livroJaCadastrado = livroRepositorio.encontrarLivroPeloTitulo(dadosLivro.title());

        if (livroJaCadastrado.isEmpty()) {
            Optional<Autor> autorCadastrado = autorRepositorio.findByNome(dadosAutor.name());

            List<Livro> livrosAutor = new ArrayList<>();

            if (autorCadastrado.isPresent()) {
                Livro livro = new Livro(dadosLivro);

                Autor autor = autorCadastrado.get();
                livrosAutor.add(livro);
                autor.setLivros(livrosAutor);

                autorRepositorio.save(autor);
                System.out.println(livro + "\n");
            } else {
                Autor autor = new Autor(dadosAutor);
                Livro livro = new Livro(dadosLivro);

                livrosAutor.add(livro);
                livro.setAutor(autor);

                livroRepositorio.save(livro);
                System.out.println(livro + "\n");
            }
        } else {
            System.out.println("\nLivro já cadastrado!\n");
        }
    }

    private void buscarLivroNaWeb() {
        System.out.println("Insira o nome do livro que você deseja procurar: ");
        var nomeLivro = entrada.nextLine();

        var json = consumoAPI.obterDados(ENDERECO + nomeLivro.toLowerCase().replace(" ", "%20"));

        DadosResultado dados = converteDados.obterDados(json, DadosResultado.class);
        if (dados.results().isEmpty()) {
            System.out.println("\nLivro não encontrado!\n");
        } else {
            salvarDados(dados);
        }
    }

    private void listarLivrosRegistrados() {
        List<Livro> livros = livroRepositorio.findAll();
        if (!livros.isEmpty()) {
            System.out.println("\n+-----+LIVROS+------+");
            livros.forEach(System.out::println);
            System.out.println("\n+-------------------+\n");
        } else {
            System.out.println("\nNenhum livro registrado ainda!\n");
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepositorio.findAll();
        if (!autores.isEmpty()) {
            System.out.println("\n+-----+AUTORES+------+");
            autores.forEach(System.out::println);
            System.out.println("\n+--------------------+\n");
        } else {
            System.out.println("\nNenhum autor registrado ainda!\n");
        }
    }

    private void listarAutoresVivosEmDeterminadoAno() {
        System.out.println("Insira o ano que deseja pesquisar: ");
        var ano = entrada.nextLine();

        List<Autor> autores = autorRepositorio.encontrarAutoresVivosEmDeterminadoAno(ano);
        if (!autores.isEmpty()) {
            System.out.println("\n+--------+*+--------+\nAutores vivios em " + ano + ": ");
            autores.forEach(System.out::println);
            System.out.println("\n+-------------------+\n");
        } else {
            System.out.println("\nNenhum autor vivo nesse ano!\n");
        }
    }

    private void listarLivrosEmDeterminadoIdioma() {
        String idioma = "";
        System.out.println("\n1 - Espanhol\n2 - Inglês\n3 - Francês\n4 - Português\nInsira o idioma para realizar a busca: ");

        var opcao = entrada.nextLine();

        switch (opcao) {
            case "1":
                idioma = "es";
                break;
            case "2":
                idioma = "en";
                break;
            case "3":
                idioma = "fr";
                break;
            case "4":
                idioma = "pt";
                break;
            default:
                System.out.println("\nOpção não encontrada!\n");
                return;
        }

        List<Livro> livros = livroRepositorio.findByIdiomasContains(idioma);
        if (!livros.isEmpty()) {
            System.out.println("\n+--LIVROS FILTRADOS--+");
            livros.forEach(System.out::println);
            System.out.println("\n+--------------------+\n");
        } else {
            System.out.println("\nNão há livros registrados nesse idioma!\n");
        }
    }
}
