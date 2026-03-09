package es.uji.al447993.clasificarFlores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.ArrayList;

public class CSV {

    public Table readTable(String nombreFichero) throws IOException {

        String ref;
        try {
            ref = getClass().getClassLoader().getResource(nombreFichero).toURI().getPath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        Table tabla;
        try(BufferedReader br = new BufferedReader(new FileReader(ref))) {
            List<String> headers = new ArrayList<String>();
            List<Row> rows = new ArrayList<Row>();

            String linea;
            int numLinea = 0;

            while((linea = br.readLine()) != null) {
                String[] elementos = linea.split(",");
                Row row = new Row();
                for (String elemento : elementos) {
                    if (numLinea == 0)
                        headers.add(elemento);
                    else
                        row.addData(Double.parseDouble(elemento));
                }
                if (numLinea != 0)
                    rows.add(row);
                numLinea++;
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


            String linea;
            int numLinea = 0;

            while((linea = br.readLine()) != null) {
                String[] elementos = linea.split(",");
                RowWithLabel row = new RowWithLabel();
                for (int i = 0; i < elementos.length - 1; i++) {
                    if (numLinea == 0)
                        headers.add(elementos[i]);
                    else
                        row.addData(Double.parseDouble(elementos[i]));
                }
                if (numLinea != 0) {
                    row.setLabel(elementos[elementos.length - 1]);
                    rows.add(row);
                }
                numLinea++;

            }
            tabla = new TableWithLabels(headers,rows);
        }

        return tabla;
    }
}
