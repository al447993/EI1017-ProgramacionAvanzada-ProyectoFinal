package es.uji.al447993.clasificarGavaraRamos.modelo;

import es.uji.al447993.clasificarGavaraRamos.vista.Vista;

public class implementacionModelo implements Modelo {

    private Vista view;

    public implementacionModelo() {
        this.view = null;
    }

    public void setView(Vista view) {
        this.view = view;
    }

}
