package es.uji.al447993.clasificarGavaraRamos.lecturaFicheros;

import es.uji.al447993.clasificarGavaraRamos.rows.RowWithLabel;
import es.uji.al447993.clasificarGavaraRamos.tables.TableWithLabels;

public class CSVLabeledFileReader extends FileReader {

    public CSVLabeledFileReader() {
        tabla = new TableWithLabels();
    }

    @Override
    void processData(String data) {
        String[] elementos = data.split(SEPARADOR);
        RowWithLabel row = new RowWithLabel();
        for (int i = 0; i < elementos.length - 1; i++)
            row.addData(Double.parseDouble(elementos[i]));
        row.setLabel(elementos[elementos.length - 1]);
        rows.add(row);
    }

    @Override
    void processHeaders(String headers) {
        String[] elementos = headers.split(SEPARADOR);
        for (int i = 0; i < elementos.length - 1; i++)
            tabla.addHeaders(elementos[i]);
    }
}
