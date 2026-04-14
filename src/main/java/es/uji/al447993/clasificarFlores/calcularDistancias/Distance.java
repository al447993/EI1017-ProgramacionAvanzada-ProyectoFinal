package es.uji.al447993.clasificarFlores.calcularDistancias;

import java.util.List;

public interface Distance {
    double calculateDistance(List<Double> p, List<Double> q);
}
