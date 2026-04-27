package es.uji.al447993.clasificarGavaraRamos.lecturaFicheros;

import es.uji.al447993.clasificarGavaraRamos.rows.Row;
import es.uji.al447993.clasificarGavaraRamos.tables.Table;

import java.util.List;
import java.util.Scanner;

public abstract class ReaderTemplate<T extends Table> {
    // Ponemos todos los atributos a protected para que las clases hijas puedan acceder a ellos.
    protected T tabla;
    protected List<? extends Row> rows;
    protected List<String> headers;
    protected String SEPARADOR;

    public ReaderTemplate() {
        SEPARADOR = ",";
    }

    abstract void openSource(String source);
    abstract void processHeaders(String headers);
    abstract void processData(String data);
    abstract void closeSource();
    abstract boolean hasMoreData();
    abstract String getNextData();

    //Modificar return
    public final T readTableFromSource(String nombreFichero){
        // Abrimos el fichero
        openSource(nombreFichero);

        //Procesamos las cabeceras
        if (hasMoreData())
            processHeaders(getNextData());


        //Procesamos las filas
        while (hasMoreData())
            processData(getNextData());

        closeSource();
        return tabla;
    }
}
