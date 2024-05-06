package com.aluracursos.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//@JsonIgnoreProperties // ignora los datos que no estan mapeados
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosSerie(
        //@jsonAlias solo nos permite leer los datos que viene de la API
        @JsonAlias("Title")String titulo,
       @JsonAlias("totalSeasons") Integer totalDeTemporadas,
        @JsonAlias("imdbRating") String evaluacion
        //@JsonProperty permite leer y  enviar datos en ese modelo

) {
}
