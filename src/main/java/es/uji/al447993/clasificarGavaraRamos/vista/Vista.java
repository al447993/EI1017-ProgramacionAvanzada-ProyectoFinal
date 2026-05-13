package es.uji.al447993.clasificarGavaraRamos.vista;

import javafx.scene.control.Button;

import java.util.List;

public interface Vista {
    void actualizarLista(List<String> canciones);
    String getDistancia();
    String getAlgoritmo();
    Button getBtnSugerir();
    String getCancion();
    void initialize();
    void show();
}
