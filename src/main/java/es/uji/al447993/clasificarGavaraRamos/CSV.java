package es.uji.al447993.clasificarGavaraRamos;

import es.uji.al447993.clasificarGavaraRamos.rows.Row;
import es.uji.al447993.clasificarGavaraRamos.rows.RowWithLabel;
import es.uji.al447993.clasificarGavaraRamos.tables.Table;
import es.uji.al447993.clasificarGavaraRamos.tables.TableWithLabels;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class CSV {
    private static final String SEPARADOR = ",";

    public Table readTable(String nombreFichero) throws IOException {

        String ref;
        try {
            ref = getClass().getClassLoader().getResource(nombreFichero).toURI().getPath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        Table tabla;
        try(BufferedReader br = new BufferedReader(new FileReader(ref))) {
            List<Row> rows = new ArrayList<Row>();

            String linea = br.readLine();
            List<String> headers = new ArrayList<String>(Arrays.asList(linea.split(SEPARADOR)));

            while((linea = br.readLine()) != null) {
                String[] elementos = linea.split(SEPARADOR);
                Row row = new Row();
                for (String elemento : elementos) {
                    row.addData(Double.parseDouble(elemento));
                }
                rows.add(row);
            }
            tabla = new Table(headers,rows);
        }

        return tabla;

    }

    public TableWithLabels readTableWithLabels(String nombreFichero) throws IOException {

        String ref;
        try {
            ref = getClass().getClassLoader().getResource(nombreFichero).toURI().getPath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        TableWithLabels tabla;
        try(BufferedReader br = new BufferedReader(new FileReader(ref))) {
            List<String> headers = new ArrayList<String>();
            List<RowWithLabel> rows = new ArrayList<RowWithLabel>();

            String linea = br.readLine();
            String[] elementos = linea.split(SEPARADOR);
            for (int i = 0; i < elementos.length - 1; i++)
                headers.add(elementos[i]);

            while((linea = br.readLine()) != null) {
                elementos = linea.split(SEPARADOR);
                RowWithLabel row = new RowWithLabel();
                for (int i = 0; i < elementos.length - 1; i++)
                    row.addData(Double.parseDouble(elementos[i]));
                row.setLabel(elementos[elementos.length - 1]);
                rows.add(row);

            }
            tabla = new TableWithLabels(headers,rows);
        }

        return tabla;
    }
}