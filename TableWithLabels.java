package es.uji.al447993.clasificarFlores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TableWithLabels extends Table {

    private Map<String,Integer> labelsToIndex;

    public TableWithLabels(List<String> headers, List<RowWithLabel> filas) {
        super(headers, filas);
        labelsToIndex = new HashMap<>();
        // Inicializamos el mapa con las etiquetas del CSV
        for (RowWithLabel row : filas) {
            if (!labelsToIndex.containsKey(row.getLabel())) {
                labelsToIndex.put(row.getLabel(), labelsToIndex.size());
            }
        }
    }

    @Override
    public RowWithLabel getRowAt(int rowNumber) {
        return (RowWithLabel) super.getRowAt(rowNumber);
    }

    public Integer getLabelAsInteger(String label) {
        return labelsToIndex.get(label);
    }
}
