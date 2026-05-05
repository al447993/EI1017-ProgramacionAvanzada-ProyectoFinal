package es.uji.al447993.clasificarGavaraRamos.vista;

import es.uji.al447993.clasificarGavaraRamos.controlador.Controlador;
import es.uji.al447993.clasificarGavaraRamos.modelo.Modelo;

public class implementacionVista implements Vista {

    private Modelo modelo;
    private Controlador controlador;

    @Override
    public void setModel(Modelo modelo) {
        this.modelo = modelo;
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
}
