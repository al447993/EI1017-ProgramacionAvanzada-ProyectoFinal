package es.uji.al447993.clasificarGavaraRamos.lecturaFicheros;

import es.uji.al447993.clasificarGavaraRamos.rows.Row;
import es.uji.al447993.clasificarGavaraRamos.tables.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVUnlabeledFileReader extends FileReader{
    private List<Row> rows;
    private List<String> headers;

    public CSVUnlabeledFileReader() {
        rows = new ArrayList<>();
    }

    @Override
    void processData(String data) {
        String[] elementos = data.split(",");
        Row row = new Row();
        for (String elemento : elementos) {
            row.addData(Double.parseDouble(elemento));
        }
        rows.add(row);
    }

    @Override
    void processHeaders(String headers) {
        this.headers = new ArrayList<String>(Arrays.asList(headers.split(",")));
    }


}
