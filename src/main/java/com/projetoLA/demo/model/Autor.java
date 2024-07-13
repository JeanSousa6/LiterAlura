package com.projetoLA.demo.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String name;
    private Year birth_year;
    private Year death_year;

    @OneToMany(mappedBy = "autor" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    List<Livro> livros = new ArrayList<>();

    public Autor(){}

    public Autor(DadosAutor dadosAutor){
        String[] autor = dadosAutor.nome().split(",");
        this.name = dadosAutor.nome();
        this.birth_year = Year.of(dadosAutor.anoNascimento());
        this.death_year = Year.of(dadosAutor.anoFalecimento());
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Year getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(Year birth_year) {
        this.birth_year = birth_year;
    }

    public Year getDeath_year() {
        return death_year;
    }

    public void setDeath_year(Year death_year) {
        this.death_year = death_year;
    }

    public Long getId() {
        return Id;
    }


    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(Livro livro) {
        this.livros.add(livro);
        livro.setAutor(this);
    }

    public List<String> todosOsLivros(){
        return livros.stream().map(Livro::getTitulo).collect(Collectors.toList());
    }

    public String toString(){
        return
                "Autor: " + name +
                "Data de nascimento: " + birth_year + '\'' +
                "Ano de falecimento: " + death_year + '\'' ;

    }


}
//        "livros: " + livros.stream()
//                .map(l -> l.getTitulo()).collect(Collectors.toList()) +
//                '\'';