package es.uji.al447993.clasificarFloresRamos.algorithms;

import es.uji.al447993.clasificarFloresRamos.tables.Table;

public interface Algorithms<T extends Table,V,U>{
    //V=lista y U=Tipo de dato_que_devuelve_estimate

    void train(T datos);

    U estimate(V datos);

}
