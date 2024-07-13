package com.projetoLA.demo.service;

import com.projetoLA.demo.model.Autor;
import com.projetoLA.demo.model.Livro;
import com.projetoLA.demo.repository.AutorRepository;
import com.projetoLA.demo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class LivrosServico {
    @Autowired
    AutorRepository autorRepository;
    @Autowired
    LivroRepository livroRepository;

    public void salvarLivro(Livro livro){

        Livro livroSalvo = livroRepository.getByTitulo(livro.getTitulo());

         if(livroSalvo == null){
             Autor autor = livro.getAutor();
             Autor autorSalvo = autorRepository.findByNameContainingIgnoreCase(autor.getName());
             if(autorSalvo != null){
                 livro.setAutor(autorSalvo);
                 autorSalvo.setLivros(livro);
             }
             else{
                 Autor autorSalvoAntes = autorRepository.save(autor);
                 livro.setAutor(autorSalvoAntes);
                 autorSalvoAntes.setLivros(livro);
             }

             livroRepository.save(livro);
             System.out.println("Livro salvo com sucesso!");

         }else {
             System.out.println("Esse livro já foi salvo anteriormente.");
         }

    }

    public List<Livro> getTodosOsLivros(){
        return livroRepository.findAll();
    }
    public List<Autor> getTodosOsAutores(){
        return autorRepository.findAll();
    }

    public List<Autor> getAutoresVivosPorPeriodo(String year){
        return autorRepository.autoresVivosPorPeriodo(year);
    }

    public List<Livro> getLivrosPorLinguagem(){
        Scanner leitura = new Scanner(System.in);
        List<String> idiomas = livroRepository.idiomas();
        System.out.println("\n Idiomas disponíveis: ");
                if(idiomas.size() == 1){
                    idiomas.forEach(System.out::print);
                }
                else{
                    idiomas.forEach(l -> System.out.println(l + ", "));
                }
        System.out.println(" ");
        System.out.print("\n Escolha o idioma de busca dos livros: ");
        String idioma = leitura.nextLine();
        if(!idiomas.contains(idioma)) {
            System.out.println("Nenhum registro encontrado.");
        }
        return livroRepository.livrosPorIdioma(idioma);
    }
}
