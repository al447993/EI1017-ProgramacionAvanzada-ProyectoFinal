package es.uji.al447993.clasificarFloresRamos.rows;

import java.util.List;

public class RowWithLabel extends Row {
    //Atributos
    private String label;

    //Constructores
    public RowWithLabel() {
        super();
        label = null;
    }
    public RowWithLabel(List<Double> data, String label) {
        super(data);
        this.label = label;
    }

    //Métodos
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
}
