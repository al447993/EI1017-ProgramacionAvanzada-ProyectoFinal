package es.uji.al447993.clasificarFlores;

import java.util.List;

public class TableWithLabels extends Table{
    //Atributos
    private List<RowWithLabel> rows;

    @Override
    public RowWithLabel getRowAt(int rowNumber) {
        return rows.get(rowNumber);
    }
}
