package es.uji.al447993.clasificarGavaraRamos.algorithms;

import es.uji.al447993.clasificarGavaraRamos.interfaces.Algorithms;
import es.uji.al447993.clasificarGavaraRamos.interfaces.Distance;
import es.uji.al447993.clasificarGavaraRamos.rows.RowWithLabel;
import es.uji.al447993.clasificarGavaraRamos.tables.TableWithLabels;

import java.util.List;

public class KNN implements Algorithms<TableWithLabels,List<Double>,Integer> {

    private TableWithLabels data;
    private Distance distancia;

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
            double dist = 0;
            dist = distancia.calculateDistance(sample, rowData);

            if (dist < minDist) {
                minDist = dist;
                bestIndex = i;
            }
        }

        RowWithLabel nearest = data.getRowAt(bestIndex);
        return data.getLabelAsInteger(nearest.getLabel());
    }
}