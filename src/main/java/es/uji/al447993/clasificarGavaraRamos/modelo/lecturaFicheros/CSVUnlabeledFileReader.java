package es.uji.al447993.clasificarGavaraRamos.modelo.lecturaFicheros;

import es.uji.al447993.clasificarGavaraRamos.modelo.rows.Row;
import es.uji.al447993.clasificarGavaraRamos.modelo.tables.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVUnlabeledFileReader extends FileReader {
    public CSVUnlabeledFileReader(String source) {
        super(source);
    }

    public CSVUnlabeledFileReader() {
        super();
    }
    @Override
    protected void processHeaders(String headers) {
        List<String> headerList = new ArrayList<>(Arrays.asList(headers.split(",")));
        this.tabla = new Table(headerList, new ArrayList<>());
    }

    @Override
    protected void processData(String data) {
        String[] elementos = data.split(",");
        Row row = new Row();
        for (String elemento : elementos) {
            row.addData(Double.parseDouble(elemento));
        }
        this.tabla.addRow(row); // Asumiendo que Table tiene addRow
    }


}
