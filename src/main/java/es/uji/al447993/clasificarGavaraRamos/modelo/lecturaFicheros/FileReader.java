package es.uji.al447993.clasificarGavaraRamos.modelo.lecturaFicheros;

import es.uji.al447993.clasificarGavaraRamos.modelo.tables.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public abstract class FileReader<T extends Table> extends ReaderTemplate {
    private Scanner sc;

    public FileReader(String source) {
        this.source = source;
    }
    public FileReader() {
    }

    @Override
    void openSource(String source) throws FileNotFoundException {
        InputStream is = getClass().getResourceAsStream(source);

        // Si no lo encuentra como recurso, intentamos buscarlo como archivo físico en el disco
        if (is == null) {
            java.io.File file = new java.io.File(source);
            if (file.exists()) {
                is = new java.io.FileInputStream(file);
            }
        }

        // Si después de ambos intentos sigue siendo null, lanzamos la excepción
        if (is == null) {
            throw new FileNotFoundException("No se pudo encontrar el recurso o archivo: " + source);
        }

        // El Scanner funciona perfectamente pasándole el InputStream directamente
        this.sc = new Scanner(is);
    }

    abstract void processHeaders(String headers);

    abstract void processData(String data);

    @Override
    void closeSource() {
        sc.close();
    }

    @Override
    boolean hasMoreData() {
        return sc.hasNextLine();
    }

    @Override
    String getNextData() {
        return sc.nextLine();
    }
}
