package com.projetoLA.demo.principal;

import com.projetoLA.demo.model.*;
import com.projetoLA.demo.service.ConsumoAPI;
import com.projetoLA.demo.service.ConverteDados;
import com.projetoLA.demo.service.LivrosServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class Principal {

    private final Scanner leitura = new Scanner(System.in);
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConverteDados converteDados = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    @Autowired
    private LivrosServico livrosServico;


    public Principal(LivrosServico livrosServico){
        this.livrosServico = livrosServico;
    }


    public void exibeMenu() throws InterruptedException {
            var menu = """
                    Escolha o número de sua opção:
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma 
                    0 - Sair                    
                    """;
            int opcao = -1;

            while(opcao != 0){
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivro();
                    break;
                case 2:
                    buscarTodosOsLivros();
                    break;
                case 3:
                    buscarTodosAutores();
                    break;
                case 4:
                    buscarAutoresVivosPorPeriodo();
                    break;
                case 5:
                    buscarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    Thread.sleep(4000);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        }
    }


    private DadosLivros getDadosLivros() {
        System.out.println("Insira o nome do livro: ");
        String livroTitulo = leitura.nextLine();

        var livroJson = consumoAPI.obterDados(ENDERECO + livroTitulo.toLowerCase().replace(" " , "%20"));
        DadosResultados dadosResultados = converteDados.obterDados(livroJson , DadosResultados.class);

        if (dadosResultados != null && dadosResultados.resultados() != null && !dadosResultados.resultados().isEmpty()) {
            DadosLivros dadosLivros = dadosResultados.resultados().get(0);
            return dadosLivros;
        }
        else {
            System.out.println("Nenhum resultado encontrado ou dadosResultados é nulo.");
            return null;
        }

    }

    private void buscarLivro() {
        DadosLivros dadosLivros = getDadosLivros();
        Livro livro = new Livro(dadosLivros);

        var primeiroAutor = dadosLivros.autores().get(0);
        DadosAutor dadosAutor = new DadosAutor(primeiroAutor.nome() , primeiroAutor.anoNascimento(), primeiroAutor.anoFalecimento());
        Autor autor = new Autor(dadosAutor);

        livro.setAutor(autor);
        livrosServico.salvarLivro(livro);
        System.out.println(livro);
    }

    private void buscarTodosOsLivros(){
        var livros = livrosServico.getTodosOsLivros();
        if(!livros.isEmpty()){
            livros.forEach(System.out::println);
        }
        else{
            System.out.println("Você não tem livros registrados, use opção 1");
        }
    }

    private void buscarTodosAutores(){
        var autores = livrosServico.getTodosOsLivros();
        if(!autores.isEmpty()){
            autores.forEach(System.out::println);
        }
        else {
            System.out.println("Você não registrou nenhum autor, use opção 1");
        }
    }

    private void buscarAutoresVivosPorPeriodo(){
        System.out.println("Insira o ano de busca: ");
        String year = leitura.nextLine();

        var autoresVivosPorPeriodo = livrosServico.getAutoresVivosPorPeriodo(year);
        if(!autoresVivosPorPeriodo.isEmpty()){
            autoresVivosPorPeriodo.forEach(System.out::println);
        }
        else{
            System.out.println("Sem autores registrados nesse ano. ");
        }
    }

    private void buscarLivrosPorIdioma(){
        var livrosPorLinguagem = livrosServico.getLivrosPorLinguagem();
        livrosPorLinguagem.forEach(System.out::println);
    }


}