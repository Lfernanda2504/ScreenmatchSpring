package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.DatosEpisodio;
import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporada;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new  Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE="https://www.omdbapi.com/?t=";
    private final String API_KEY="&apikey=1398b381";
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraElMenu(){
        System.out.println("Ingresa el nombre de la serie que deseas buscar");
        var tituloSerie = teclado.nextLine();
        //datos generales de la serie
        var json = consumoApi.obtenerDatos(URL_BASE + tituloSerie.replace(" ", "+") +API_KEY);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);

        //busca los datos de las temporadas
        List<DatosTemporada> temporadas= new ArrayList<>();
        for (int i = 1; i <= datos.totalDeTemporadas() ; i++) {
            json =consumoApi.obtenerDatos(URL_BASE +tituloSerie.replace(" ", "+")+"&Season="+i+API_KEY);
            var datosTemporadas =conversor.obtenerDatos(json, DatosTemporada.class);
            temporadas.add(datosTemporadas);
        }
        //temporadas.forEach(System.out::println);
        //Mostrar solo el titulo para las temporadas
//        for (int i = 0; i < datos.totalDeTemporadas(); i++) {
//            List <DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            for (int j = 0; j < episodiosTemporada.size(); j++) {
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
    }
}
