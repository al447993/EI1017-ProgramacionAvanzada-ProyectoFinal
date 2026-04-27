package es.uji.al447993.clasificarGavaraRamos.lecturaFicheros;

import es.uji.al447993.clasificarGavaraRamos.tables.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class FileReader<T extends Table> extends ReaderTemplate {
    private Scanner sc;

    public FileReader() {
        rows = new ArrayList<>();
        headers = new ArrayList<>();
    }

    @Override
    void openSource(String source) {
        try {
            // Esto busca el archivo en la carpeta real de tu disco duro
            File file = new File(source);
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            // Si no encuentra el archivo, aquí te dirá por qué
            System.err.println("No se encontró el archivo en: " + source);
            e.printStackTrace();
        }
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
