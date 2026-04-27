package es.uji.al447993.clasificarGavaraRamos.lecturaFicheros;

import es.uji.al447993.clasificarGavaraRamos.tables.Table;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class FileReader<T extends Table> extends ReaderTemplate{
    private Scanner sc;

    public FileReader() {
        rows = new ArrayList<>();
        headers = new ArrayList<>();
    }

    @Override
    void openSource(String source) {
        sc = new Scanner(getClass().getClassLoader().getResourceAsStream(source));
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
