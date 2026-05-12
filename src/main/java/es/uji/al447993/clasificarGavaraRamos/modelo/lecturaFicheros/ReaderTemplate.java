package es.uji.al447993.clasificarGavaraRamos.modelo.lecturaFicheros;

import es.uji.al447993.clasificarGavaraRamos.modelo.rows.Row;
import es.uji.al447993.clasificarGavaraRamos.modelo.tables.Table;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public abstract class ReaderTemplate<T extends Table> {
    // Ponemos todos los atributos a protected para que las clases hijas puedan acceder a ellos.
    protected T tabla;
    protected List<? extends Row> rows;
    protected String SEPARADOR;
    protected String source;

    public ReaderTemplate() {
        SEPARADOR = ",";
    }

    abstract void openSource(String source) throws FileNotFoundException;
    abstract void processHeaders(String headers);
    abstract void processData(String data);
    abstract void closeSource();
    abstract boolean hasMoreData();
    abstract String getNextData();

    //Modificar return
    public final T
    readTableFromSource(String nombreFichero) throws IOException {
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
