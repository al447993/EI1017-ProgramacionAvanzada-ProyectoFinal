package es.uji.al447993.clasificarGavaraRamos.modelo;

import es.uji.al447993.clasificarGavaraRamos.modelo.distancia.EuclideanDistance;
import es.uji.al447993.clasificarGavaraRamos.modelo.distancia.ManhattanDistance;
import es.uji.al447993.clasificarGavaraRamos.modelo.interfaces.Distance;

import java.util.ArrayList;
import java.util.List;

public class implementacionModelo implements Modelo {

    private SongReader reader = new SongReader();

    //Lectura CSV
    @Override
    public List<String> obtenerCanciones() {
        return reader.loadSongs();
    }

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
