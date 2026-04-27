package es.uji.al447993.clasificarGavaraRamos.lecturaFicheros;

import es.uji.al447993.clasificarGavaraRamos.rows.Row;
import es.uji.al447993.clasificarGavaraRamos.tables.Table;

import java.util.ArrayList;
import java.util.Arrays;

public class CSVUnlabeledFileReader extends FileReader {

    public CSVUnlabeledFileReader(String s) {
        tabla = new Table();
    }

    @Override
    void processData(String data) {
        String[] elementos = data.split(",");
        Row row = new Row();
        for (String elemento : elementos) {
            row.addData(Double.parseDouble(elemento));
        }
        tabla.addRow(row);
    }

    // Lo modificamos por el separados split, que elimina los espacios para que no den errores.

    @Override
    void processHeaders(String headers) {
        // Dividimos la línea por la coma
        String[] elementos = headers.split(SEPARADOR);

        for (String elemento : elementos) {
            // .trim() es la clave: convierte " Dollars" en "Dollars"
            tabla.addHeaders(elemento.trim());
        }
    }


}
