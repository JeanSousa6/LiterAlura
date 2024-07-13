package com.projetoLA.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteDados implements IConverteDados {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json , classe);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
    public <T> T obterLista(String json , Class<T> classe){
        CollectionType lista = mapper.getTypeFactory()
                .constructCollectionType(List.class , classe);
        try {
            return mapper.readValue(json, lista);
        }catch(JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public String extraiObjJSon(String json , String obj) {
        try {
            JsonNode jsonCompleto = mapper.readTree(json);
            JsonNode jsonLivro = jsonCompleto.path("results");
            return jsonLivro.toString();
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
