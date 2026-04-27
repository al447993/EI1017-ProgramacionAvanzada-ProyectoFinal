package es.uji.al447993.clasificarGavaraRamos.lecturaFicheros;

import es.uji.al447993.clasificarGavaraRamos.tables.Table;

import java.util.Scanner;

public abstract class FileReader<T extends Table> extends ReaderTemplate{
    private Scanner sc;
    private T tabla;

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
        if (sc.hasNextLine())
            return true;
        return false;
    }

    @Override
    String getNextData() {
        return sc.nextLine();
    }
}
