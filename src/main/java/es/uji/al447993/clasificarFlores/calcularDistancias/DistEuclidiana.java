package es.uji.al447993.clasificarFlores.calcularDistancias;

import java.util.List;

public class DistEuclidiana {

    //Atributos
    private List<Double> sample;
    private List<Double> rowData;

    //Constructores
    public DistEuclidiana(List<Double> sample, List<Double> rowData){
        this.sample = sample;
        this.rowData = rowData;
    }

    //Métodos
    public double calcularDistEucli() {
        double suma = 0.0;

        for (int j = 0; j < sample.size(); j++) {
            double diff = sample.get(j) - rowData.get(j);
            suma += diff * diff;
        }
        return Math.sqrt(suma);
    }

}
