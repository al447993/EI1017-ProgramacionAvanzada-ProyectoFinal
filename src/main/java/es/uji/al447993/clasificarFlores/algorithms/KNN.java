package es.uji.al447993.clasificarFlores.algorithms;

import es.uji.al447993.clasificarFlores.rows.RowWithLabel;
import es.uji.al447993.clasificarFlores.tables.TableWithLabels;
import es.uji.al447993.clasificarFlores.calcularDistancias.EuclideanDistance;

import java.util.List;

public class KNN implements Algorithms<TableWithLabels,List<Double>,Integer> {

    private TableWithLabels data;

    //Almacenamiento de los datos
    @Override
    public void train(TableWithLabels data) {
        this.data = data;
    }

    @Override
    public Integer estimate(List<Double> sample) {

        double minDist = Double.MAX_VALUE;
        int bestIndex = Integer.MIN_VALUE;

        for (int i = 0; i < data.getRowCount(); i++) {

            List<Double> rowData = data.getRowAt(i).getData();
            EuclideanDistance dist = new EuclideanDistance(sample, rowData);
            double distancia = dist.calcularDistEucli();

            if (distancia < minDist) {
                minDist = distancia;
                bestIndex = i;
            }
        }

        RowWithLabel nearest = data.getRowAt(bestIndex);
        return data.getLabelAsInteger(nearest.getLabel());
    }


}