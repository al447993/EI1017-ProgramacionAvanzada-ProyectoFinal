package es.uji.al447993.clasificarGavaraRamos.modelo.algorithms;

import es.uji.al447993.clasificarGavaraRamos.modelo.interfaces.Algorithms;
import es.uji.al447993.clasificarGavaraRamos.modelo.interfaces.Distance;
import es.uji.al447993.clasificarGavaraRamos.modelo.rows.Row;
import es.uji.al447993.clasificarGavaraRamos.modelo.tables.Table;
import es.uji.al447993.clasificarGavaraRamos.modelo.excepciones.InvalidClusterNumberException;

import java.util.*;

public class KMeans implements Algorithms<Table, List<Double>, Integer> {
    private Table data;
    private int numClusters;
    private int numIterations;
    private long seed;
    private List<List<Double>> centroides;
    private Distance distancia;

    public KMeans(int numClusters, int numIterations, long seed, Distance distancia) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.centroides = new ArrayList<>();
        this.distancia = distancia;

    }

    @Override
    public void train(Table datos) {
        if (numClusters > datos.getRowCount())
            throw new InvalidClusterNumberException(datos.getRowCount(), numClusters);

        Random random = new Random(seed);

        centroides.clear();

        Set<Integer> representantes = new HashSet<>();

        while (representantes.size() < numClusters) {
            representantes.add(random.nextInt(datos.getRowCount()));
        }

        // Inicializar centroides
        for (int index : representantes) {
            centroides.add(new ArrayList<>(datos.getRowAt(index).getData()));
        }

        // Iteraciones KMeans
        for (int i = 0; i < numIterations; i++) {

            // IMPORTANTE:
            // crear grupos nuevos en cada iteración
            Map<Integer, List<Row>> grupos = new HashMap<>();

            for (int k = 0; k < numClusters; k++) {
                grupos.put(k, new ArrayList<>());
            }

            // Asignar puntos al centroide más cercano
            for (int j = 0; j < datos.getRowCount(); j++) {

                Row actual = datos.getRowAt(j);

                int mejorGrupo = -1;
                double minDist = Double.MAX_VALUE;

                for (int k = 0; k < numClusters; k++) {

                    double dist = distancia.calculateDistance(
                            actual.getData(),
                            centroides.get(k)
                    );

                    if (dist < minDist) {
                        minDist = dist;
                        mejorGrupo = k;
                    }
                }

                grupos.get(mejorGrupo).add(actual);
            }

            // Recalcular centroides
            for (int k = 0; k < numClusters; k++) {

                List<Row> puntos = grupos.get(k);

                if (puntos.isEmpty())
                    continue;

                List<Double> nuevoCentroide = new ArrayList<>();

                int numColumns = puntos.get(0).getData().size();

                for (int c = 0; c < numColumns; c++) {

                    double suma = 0;

                    for (Row row : puntos) {
                        suma += row.getData().get(c);
                    }

                    nuevoCentroide.add(suma / puntos.size());
                }

                centroides.set(k, nuevoCentroide);

            }
        }
    }

    @Override
    public Integer estimate(List<Double> dato) {
        int mejorGrupo = -1;
        double minDist = Double.MAX_VALUE;

        //Buscamos el centroide más cercano
        for (int k = 0; k < numClusters; k++) {
            double dist = distancia.calculateDistance(dato, centroides.get(k));

            if (dist < minDist) {
                minDist = dist;
                mejorGrupo = k;
            }
        }
        return mejorGrupo;
    }


}
