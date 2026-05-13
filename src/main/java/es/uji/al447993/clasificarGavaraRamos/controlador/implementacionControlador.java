package es.uji.al447993.clasificarGavaraRamos.controlador;

import es.uji.al447993.clasificarGavaraRamos.modelo.Modelo;
import es.uji.al447993.clasificarGavaraRamos.vista.JavaMain;
import es.uji.al447993.clasificarGavaraRamos.vista.abrirVentanaSugerir;
import javafx.scene.control.Alert;

import java.util.List;

public class implementacionControlador {
    private Modelo modelo;
    private JavaMain vista;

    public implementacionControlador(Modelo modelo, JavaMain vista) {
        this.modelo = modelo;
        this.vista = vista;

        //Actualizamos la lista de canciones
        this.vista.actualizarLista(modelo.obtenerCanciones());

        //Cuando damos click a sugerir, hay que pasar toda la información referente a distancia y algoritmo
        this.vista.getBtnSugerir().setOnAction( e -> {
            String cancion = vista.getCancion();
            String distancia = vista.getDistancia();
            String algoritmo = vista.getAlgoritmo();

            if (cancion == null || distancia == null || algoritmo == null) {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Atención");
                alerta.setHeaderText("Faltan datos");
                alerta.setContentText("Selecciona todos los datos correspondientes");
                alerta.showAndWait();
                return;
            }

            abrirVentanaSugerir ventana = new abrirVentanaSugerir();
            List<String> recomendaciones = modelo.recommend(cancion,distancia,algoritmo,1);
            ventana.actualizarLista(recomendaciones);

            ventana.getSelectorNumerico().valueProperty().addListener((obs, oldValue, newValue) -> {
                List<String> nuevasRecs = modelo.recommend(cancion,distancia,algoritmo,newValue);
                ventana.actualizarLista(nuevasRecs);
            });

            ventana.show();
        });
    }
}
