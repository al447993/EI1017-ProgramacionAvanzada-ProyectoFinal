package es.uji.al447993.clasificarGavaraRamos.modelo.lecturaFicheros;

import es.uji.al447993.clasificarGavaraRamos.modelo.rows.RowWithLabel;
import es.uji.al447993.clasificarGavaraRamos.modelo.tables.TableWithLabels;

import java.util.ArrayList;
import java.util.List;

public class CSVLabeledFileReader extends FileReader<TableWithLabels> {

    public CSVLabeledFileReader(String source) {
        super(source);
    }

    public CSVLabeledFileReader() {
        super();
    }

    @Override
    protected void processHeaders(String headers) {
        String[] elementos = headers.split(",");
        List<String> headerList = new ArrayList<>();
        for (int i = 0; i < elementos.length - 1; i++) {
            headerList.add(elementos[i]);
        }
        this.tabla = new TableWithLabels(headerList, new ArrayList<>());
    }

    @Override
    protected void processData(String data) {
        String[] elementos = data.split(",");
        RowWithLabel row = new RowWithLabel();

        for (int i = 0; i < elementos.length - 1; i++) {
            row.addData(Double.parseDouble(elementos[i]));
        }
        row.setLabel(elementos[elementos.length - 1]);
        this.tabla.addRow(row);
    }
}
