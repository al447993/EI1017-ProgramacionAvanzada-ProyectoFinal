package es.uji.al447993.clasificarGavaraRamos.tables;

import es.uji.al447993.clasificarGavaraRamos.rows.Row;

import java.util.ArrayList;
import java.util.List;

public class Table {
    //Atributos protected para que TableWithLabels pueda acceder a ellos
    protected List<String> headers;
    private List<Row> rows; //El resto de la tabla que no son headers (todas las filas)

    //Constructores
    public Table() {
        headers = new ArrayList<>();
        rows = new ArrayList<>();
    }

    public Table(List<String> headers, List<? extends Row> filas) {
        this.headers = new ArrayList<>(headers);
        this.rows = new ArrayList<>(filas);
        //En filas lo hacemos así para poder utilizar la clase hija de Row
    }

    //Métodos
    public Row getRowAt(int rowNumber) {
        return rows.get(rowNumber);
    }

    //Las columnas se consiguen extrayendo el elemento en la posición indicada
    // de cada row.
    // Ej: Columna 0, elemento de cada row en la posición 0
    public List<Double> getColumnAt(int columnNumber) {
        List<Double> column = new ArrayList<>();
        for (Row row : rows) {
            column.add(row.getData().get(columnNumber));
        }
        return column;
    }

    public Integer getRowCount() {
        return (Integer) rows.size();
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void addRow(Row row) {
        this.rows.add(row);
    }

    public void addHeaders(String header) {
        headers.add(header);
    }
}