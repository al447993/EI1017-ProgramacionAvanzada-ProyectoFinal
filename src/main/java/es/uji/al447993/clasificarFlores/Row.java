package es.uji.al447993.clasificarFlores;

import java.util.List;

public class Row extends Table{
    //Atributos
    private List<Double> data;

    //Constructores
    public Row() {
        data = null;
    }
    public Row(List<Double> data) {
        this.data = data;
    }

    //Métodos
    public List<Double> getData(){
        return data;
    }

    public void addData(Double cell) {
        data.add(cell);
    }
}
