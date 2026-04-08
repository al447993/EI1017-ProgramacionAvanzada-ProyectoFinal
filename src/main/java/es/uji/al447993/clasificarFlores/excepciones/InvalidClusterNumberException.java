package es.uji.al447993.clasificarFlores.excepciones;

public class InvalidClusterNumberException extends  RuntimeException{
    private int numClusters;

    public InvalidClusterNumberException(Integer rowCount, Integer numClusters) {
        super("No hay suficientes clusters (" + numClusters + ") para todos los datos disponibles (" + rowCount + ")");
        this.numClusters = numClusters;
    }

    public InvalidClusterNumberException() {
        super("Número de clusters inválidos para el conjunto de datos");
    }

    public Integer getNumberOfClusters() {
        return numClusters;
    }
}
