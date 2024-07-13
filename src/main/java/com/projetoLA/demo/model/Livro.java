package com.projetoLA.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne
    private Autor autor;
    private String idioma;
    private Integer numeroDownload;
    private DadosLivros dadosLivros;

    public Livro() {}

    public Livro(DadosLivros dadosLivros){
        this.titulo = dadosLivros.titulo();
        Autor autor = new Autor(dadosLivros.autores().get(0));
        this.autor = autor;
        this.idioma = dadosLivros.idioma().get(0);
        this.numeroDownload = dadosLivros.numeroDownloads();
    }

    public Livro(Long id, String titulo, Autor autor , String idioma, Integer numeroDownload){
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.numeroDownload = numeroDownload;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDownload() {
        return numeroDownload;
    }

    public void setNumeroDownload(Integer numeroDownload) {
        this.numeroDownload = numeroDownload;
    }
    public String toString(){
        return "----------Livro-----------" + '\'' +
                "Título:   " + titulo +  '\'' +
                "Autor:     "  + autor + '\'' +
                "Idioma:    " + idioma + '\'' +
                "Número de Downloads: " + numeroDownload + '\'' +
                "-----------------------------" +  '\'' ;
    }

}
