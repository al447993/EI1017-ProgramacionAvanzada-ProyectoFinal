package es.uji.al447993.clasificarGavaraRamos.modelo;

import java.util.List;

public interface Modelo {

    //Lectura CSV
    List<String> obtenerCanciones();
    List<String> recommend(String cancion, String dist, String alg, int num);
}
