package es.uji.al447993.clasificarGavaraRamos.modelo;

import es.uji.al447993.clasificarGavaraRamos.modelo.distancia.EuclideanDistance;
import es.uji.al447993.clasificarGavaraRamos.modelo.distancia.ManhattanDistance;
import es.uji.al447993.clasificarGavaraRamos.modelo.interfaces.Distance;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class implementacionModelo implements Modelo {

    //Lectura CSV
    @Override
    public List<String> obtenerCanciones() {
        // Leemos directamente desde el recurso que usa tu lógica de negocio
        List<String> songs = new ArrayList<>();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("recsys/songs_test_names.csv");
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) songs.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return songs;
    }

    @Override
    public List<String> recommend(String cancion, String dist, String alg, int num) {
        try {
            Distance distancia;
            if (dist.contains("Euclidean"))
                distancia = new EuclideanDistance();
            else
                distancia = new ManhattanDistance();

            SongRecSys sistema = new SongRecSys(alg.toLowerCase(),distancia);
            return sistema.getRecomendaciones(cancion,num);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
