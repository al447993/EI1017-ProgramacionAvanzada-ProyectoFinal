package es.uji.al447993.clasificarGavaraRamos.vista;

import es.uji.al447993.clasificarGavaraRamos.controlador.Controlador;
import es.uji.al447993.clasificarGavaraRamos.modelo.Modelo;

public interface Vista {
    void setModel(Modelo modelo);
    void setControlador(Controlador controlador);
}
