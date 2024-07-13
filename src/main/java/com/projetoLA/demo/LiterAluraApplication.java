package com.projetoLA.demo;

import com.projetoLA.demo.model.DadosAutor;
import com.projetoLA.demo.model.DadosResultados;
import com.projetoLA.demo.principal.Principal;
import com.projetoLA.demo.repository.LivroRepository;
import com.projetoLA.demo.service.ConverteDados;
import com.projetoLA.demo.model.DadosLivros;
import com.projetoLA.demo.service.ConsumoAPI;
import com.projetoLA.demo.service.ConverteDados;
import com.projetoLA.demo.service.LivrosServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private LivrosServico livrosServico;

	public static void main(String[] args)
	{
		SpringApplication.run(LiterAluraApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal(livrosServico);
		principal.exibeMenu();

	}
}


//		ConverteDados converteDados = new ConverteDados();
//		DadosLivros dadosLivros = converteDados.obterDados(json, DadosLivros.class);
//		System.out.println(dadosLivros);
//		DadosAutor autor = conversor.obterDados(json , DadosAutor.class);
//		System.out.println(autor.toString());




//ConsumoAPI consumoAPI = new ConsumoAPI();
//		var json = consumoAPI.obterDados("https://gutendex.com/books/?search=machado+de+assis");
//		//System.out.println(json);
//		ConverteDados conversor = new ConverteDados();
//		DadosLivros livros = conversor.obterDados(json, DadosLivros.class);
//		System.out.println(livros);
