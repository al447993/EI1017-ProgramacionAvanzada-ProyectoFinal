package es.uji.al447993.clasificarGavaraRamos.tables;

import es.uji.al447993.clasificarGavaraRamos.rows.Row;
import es.uji.al447993.clasificarGavaraRamos.rows.RowWithLabel;

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

    @Override
    public void addRow (Row row) {
        super.addRow(row);
        if (row instanceof RowWithLabel) {
            String label = ((RowWithLabel) row).getLabel();
            if (!labelsToIndex.containsKey(label)) {
                labelsToIndex.put(label, labelsToIndex.size());
            }
        }
    }

    @Override
    public Integer getRowCount() {
        return super.getRowCount();
    }
}