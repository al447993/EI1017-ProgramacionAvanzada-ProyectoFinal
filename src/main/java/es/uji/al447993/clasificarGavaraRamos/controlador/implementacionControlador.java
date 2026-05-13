package es.uji.al447993.clasificarGavaraRamos.controlador;

import es.uji.al447993.clasificarGavaraRamos.modelo.Modelo;
import es.uji.al447993.clasificarGavaraRamos.vista.Vista;
import es.uji.al447993.clasificarGavaraRamos.vista.implementacionVista;
import es.uji.al447993.clasificarGavaraRamos.vista.abrirVentanaSugerir;

import java.util.List;

public class implementacionControlador {
    private Modelo modelo;
    private Vista vista;

    public implementacionControlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;

        //Actualizamos la lista de canciones
        this.vista.actualizarLista(modelo.obtenerCanciones());

        //Cuando damos click a sugerir, hay que pasar toda la información referente a distancia y algoritmo
        this.vista.getBtnSugerir().setOnAction( e -> {
            String cancion = vista.getCancion();
            String distancia = vista.getDistancia();
            String algoritmo = vista.getAlgoritmo();

            abrirVentanaSugerir ventana = new abrirVentanaSugerir();

            List<String> recomendaciones = this.modelo.recommend(cancion, distancia, algoritmo, 1);
            ventana.actualizarLista(recomendaciones);

            ventana.getSelectorNumerico().valueProperty().addListener((obs, oldValue, newValue) -> {
                // Si el valor es null o no ha cambiado
                if (newValue == null || newValue.equals(oldValue)) return;

                // Platform.runLater sirve para que cuando pulsemos para aumentar el número de recomendaciones
                // mostradas por pantalla, este no explote, y vaya de uno en uno (si no, de 1 pasaba a 7)
                javafx.application.Platform.runLater(() -> {
                    List<String> nuevasRecs = modelo.recommend(cancion, distancia, algoritmo, newValue);
                    ventana.actualizarLista(nuevasRecs);
                });
            });

            ventana.show();
        });
    }
}
