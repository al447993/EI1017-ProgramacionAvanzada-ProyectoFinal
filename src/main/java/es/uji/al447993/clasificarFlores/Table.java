package es.uji.al447993.clasificarFlores;

import java.util.ArrayList;
import java.util.List;

public class Table extends CSV {
    private List<String> headers;
    private List<Row> rows; //El resto de la tabla que no son headers (todas las filas)

    public Table(){
        headers = null;
        rows = null;
    }
    public Table(List<String> headers, List<Row> filas) {
        this.headers = headers;
        this.rows = filas;
    }

    public Row getRowAt(int rowNumber) {
        return rows.get(rowNumber);
    }

    //Las columnas se consiguen extrayendo el elemento en la posición indicada
    // de cada row.
    // Ej: Columna 0, elemento de cada row en la posición 0
    public List<Double> getColumAt(int columnNumber) {
        List<Double> column = new ArrayList<>();
        for ( Row row : rows) {
            column.add(row.getData().get(columnNumber));
        }
        return column;
    }
}
