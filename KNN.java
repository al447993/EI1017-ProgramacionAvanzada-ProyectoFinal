package es.uji.al447993.clasificarFlores;

import java.util.List;

public class KNN {

    private TableWithLabels data;

    public void train(TableWithLabels data) {
        this.data = data;
    }

    public Integer estimate(List<Double> sample) {

        double minDist = Double.MAX_VALUE;
        int bestIndex = -1;

        for (int i = 0; i < data.getRowCount(); i++) {

            RowWithLabel row = data.getRowAt(i);
            List<Double> rowData = row.getData();

            double suma = 0.0;

            for (int j = 0; j < sample.size(); j++) {
                double diff = sample.get(j) - rowData.get(j);
                suma += diff * diff;
            }

            double distancia = Math.sqrt(suma);

            if (distancia < minDist) {
                minDist = distancia;
                bestIndex = i;
            }
        }

        RowWithLabel nearest = data.getRowAt(bestIndex);
        return data.getLabelAsInteger(nearest.getLabel());
    }
}