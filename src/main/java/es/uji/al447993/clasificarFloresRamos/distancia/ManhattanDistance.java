package es.uji.al447993.clasificarFloresRamos.distancia;

import es.uji.al447993.clasificarFloresRamos.interfaces.Distance;

import java.util.List;

public class ManhattanDistance implements Distance{

    @Override
    public double calculateDistance(List<Double> p, List<Double> q) {
        double suma = 0.0;

        for (int j = 0; j < p.size(); j++) {
            double diff = p.get(j) - q.get(j);
            suma += diff;
        }

        return suma;
    }
}
