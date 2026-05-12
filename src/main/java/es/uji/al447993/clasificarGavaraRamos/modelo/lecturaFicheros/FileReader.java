package es.uji.al447993.clasificarGavaraRamos.modelo.lecturaFicheros;

import es.uji.al447993.clasificarGavaraRamos.modelo.tables.Table;

import java.io.File;
import java.io.FileNotFoundException;
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
        File file = null;

        // 1. Intentar como recurso directo (lo que usabas antes)
        java.net.URL resource = getClass().getClassLoader().getResource(source);
        if (resource != null) {
            file = new File(resource.getPath());
        }

        // 2. Si falla, intentar buscarlo dentro de la carpeta de recursos del sistema
        if (file == null || !file.exists()) {
            // Intentamos añadir el prefijo de resources si el test manda una ruta relativa
            String resourcePath = "src/main/resources/" + source;
            file = new File(resourcePath);
        }

        // 3. Si sigue fallando, intentar la ruta tal cual (ruta absoluta o relativa al proyecto)
        if (!file.exists()) {
            file = new File(source);
        }

        // Verificación final
        if (!file.exists()) {
            throw new FileNotFoundException("No se pudo encontrar el archivo en: " + source +
                    ". Ruta absoluta intentada: " + file.getAbsolutePath());
        }

        this.sc = new Scanner(file);
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
