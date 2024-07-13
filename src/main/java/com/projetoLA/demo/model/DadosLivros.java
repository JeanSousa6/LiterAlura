package com.projetoLA.demo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;

import java.util.List;

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivros(
        @JsonAlias("title") String titulo,
        @ElementCollection
        @JsonAlias("authors") List<DadosAutor> autores,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count") Integer numeroDownloads
) {
}
