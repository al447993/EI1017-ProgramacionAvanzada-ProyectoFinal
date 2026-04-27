package es.uji.al447993.clasificarGavaraRamos.algorithms;

import es.uji.al447993.clasificarGavaraRamos.distancia.EuclideanDistance;
import es.uji.al447993.clasificarGavaraRamos.distancia.ManhattanDistance;
import es.uji.al447993.clasificarGavaraRamos.interfaces.Algorithms;
import es.uji.al447993.clasificarGavaraRamos.interfaces.Distance;
import es.uji.al447993.clasificarGavaraRamos.rows.Row;
import es.uji.al447993.clasificarGavaraRamos.tables.Table;
import es.uji.al447993.clasificarGavaraRamos.excepciones.InvalidClusterNumberException;

import java.util.*;

public class KMeans implements Algorithms<Table, List<Double>, Integer> {
    private Table data;
    private int numClusters;
    private int numIterations;
    private long seed;
    private List<List<Double>> centroides;
    private Distance distancia;

    public KMeans(int numClusters, int numIterations, long seed) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.centroides = new ArrayList<>();
        //this.distancia = new EuclideanDistance();
        this.distancia = new ManhattanDistance();

    }

    @Override
    public void train(Table datos) {
        if (numClusters > datos.getRowCount())
            throw new InvalidClusterNumberException(datos.getRowCount(), numClusters);

        Random random = new Random(seed);
        Set<Integer> representantes = new HashSet<>();
        this.centroides = centroides;
        Map<Integer, List<Row>> grupos = new HashMap<>();
        int num = 0;

        //Elegimos muestras al azar igual a los grupos que me marca que tengo
        while (representantes.size() < numClusters) {
            representantes.add(random.nextInt(datos.getRowCount()));
            grupos.put(++num, new ArrayList<>());
        }
        num = 0;
        for (int numRepresentante : representantes) {
            Row row = datos.getRowAt(numRepresentante);
            grupos.get(++num).add(row);
            centroides.add(row.getData());
        }
        //Empezamos a guardar filas de la tabla en sus respectivos grupos
        for (int i = 0; i < numIterations; i++) {

            //Recorremos la tabla fila por fila
            for (int j = 0; j < datos.getRowCount(); j++) {
                Row actual = datos.getRowAt(j); //fila actual
                int mejorGrupo = -1;
                double minDist = Double.MAX_VALUE;

                //Buscamos el centroide más cercano
                for (int k = 0; k < numClusters; k++) {
                    //En KNN ya tenemos el metodo para calcular distancias, por lo que lo cogemos
                    //de allí.
                    double dist = distancia.calculateDistance(actual.getData(), centroides.get(k));

                    if (dist < minDist) {
                        minDist = dist;
                        mejorGrupo = k;
                    }
                }

                //Añadimos este al grupo
                grupos.get(mejorGrupo + 1).add(actual);
                //Le sumamos uno al grupo porque nuestros grupos van de 1 a numClusters
                // (no empiezan por el 0) y por tanto, el número de grupo del mapa siempre
                // será ese número + 1.
                //Cabe matizar que las posiciones de los centroides sí van de 0 a numClusters - 1


                //Calculamos el nuevo centroide de ese grupo

                List<Row> puntos = grupos.get(mejorGrupo + 1);

                List<Double> sumas = new ArrayList<>();

                //Para ver el número de columnas, tengo que acceder al data de Row
                int numColumns = puntos.get(0).getData().size();

                for (int k = 0; k < numColumns; k++) {
                    double sumaColumna = 0;

                    for (Row row : puntos)
                        sumaColumna += row.getData().get(k);

                    sumas.add(sumaColumna / puntos.size());
                }

                centroides.set(mejorGrupo, sumas);

            }

        }

    }


    @Override
    public Integer estimate(List<Double> dato) {
        int mejorGrupo = -1;
        double minDist = Double.MAX_VALUE;

        //Buscamos el centroide más cercano
        for (int k = 0; k < numClusters; k++) {
            //En KNN ya tenemos el metodo para calcular distancias, por lo que lo cogemos
            //de allí.
            double dist = distancia.calculateDistance(dato, centroides.get(k));

            if (dist < minDist) {
                minDist = dist;
                mejorGrupo = k;
            }
        }
        return mejorGrupo;
    }


}
