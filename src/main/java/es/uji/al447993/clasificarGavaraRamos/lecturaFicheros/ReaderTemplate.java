package es.uji.al447993.clasificarGavaraRamos.lecturaFicheros;

import es.uji.al447993.clasificarGavaraRamos.tables.Table;

public abstract class ReaderTemplate<T extends Table> {
    abstract void openSource(String source);
    abstract void processHeaders(String headers);
    abstract void processData(String data);
    abstract void closeSource();
    abstract boolean hasMoreData();
    abstract String getNextData();

    //Modificar return
    public final T readTableFromSource(String nombreFichero){
        openSource(nombreFichero);
        processHeaders(nombreFichero);
        processData(nombreFichero);
        closeSource();
        return null;
    }
}
