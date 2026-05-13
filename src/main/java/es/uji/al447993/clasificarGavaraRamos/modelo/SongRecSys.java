package es.uji.al447993.clasificarGavaraRamos.modelo;

import es.uji.al447993.clasificarGavaraRamos.modelo.interfaces.Algorithms;
import es.uji.al447993.clasificarGavaraRamos.modelo.algorithms.KNN;
import es.uji.al447993.clasificarGavaraRamos.modelo.algorithms.KMeans;
import es.uji.al447993.clasificarGavaraRamos.modelo.interfaces.Distance;
import es.uji.al447993.clasificarGavaraRamos.modelo.lecturaFicheros.CSVLabeledFileReader;
import es.uji.al447993.clasificarGavaraRamos.modelo.lecturaFicheros.CSVUnlabeledFileReader;
import es.uji.al447993.clasificarGavaraRamos.modelo.tables.Table;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

class SongRecSys {
    private RecSys recsys;

    public SongRecSys(String method, Distance distancia) throws Exception {
        String ruta = "recsys/";

        // File names (could be provided as arguments to the constructor to be more general)
        Map<String,String> filenames = new HashMap<>();
        filenames.put("knn"+"train",ruta+"songs_train.csv");
        filenames.put("knn"+"test",ruta+"songs_test.csv");
        filenames.put("kmeans" +"train",ruta+"songs_train_withoutnames.csv");
        filenames.put("kmeans" +"test",ruta+"songs_test_withoutnames.csv");

        // Algorithms
        Map<String, Algorithms> algorithms = new HashMap<>();
        algorithms.put("knn",new KNN(distancia));
        algorithms.put("kmeans",new KMeans(15, 200, 4321,distancia));

        CSVLabeledFileReader labeledReader = new CSVLabeledFileReader();
        CSVUnlabeledFileReader unlabeledReader = new CSVUnlabeledFileReader();

        Map<String, Table> tables = new HashMap<>();
        tables.put("knn" + "train", labeledReader.readTableFromSource(filenames.get("knn" + "train")));
        tables.put("knn" + "test", labeledReader.readTableFromSource(filenames.get("knn" + "test")));
        tables.put("kmeans" + "train", unlabeledReader.readTableFromSource(filenames.get("kmeans" + "train")));
        tables.put("kmeans" + "test", unlabeledReader.readTableFromSource(filenames.get("kmeans" + "test")));

        // Names of items
        List<String> names = readNames(ruta+"songs_test_names.csv");

        // Start the RecSys
        this.recsys = new RecSys(algorithms.get(method));
        this.recsys.train(tables.get(method+"train"));
        this.recsys.initialise(tables.get(method+"test"), names);

    }

    private List<String> readNames(String fileOfItemNames) throws IOException, URISyntaxException {
        List<String> names = new ArrayList<>();

        InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream(fileOfItemNames);
        // PRIMERO comprobar null
        if (is == null) {
            throw new FileNotFoundException(
                    "No se encontró: " + fileOfItemNames
            );
        }
        // DESPUÉS crear BufferedReader
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(is))) {

            String line;

            while ((line = br.readLine()) != null) {

                if (!line.trim().isEmpty()) {
                    names.add(line);
                }
            }
        }

        return names;
    }

    public List<String> getRecomendaciones(String cancion, int num) {
        return this.recsys.recommend(cancion,num);
    }
}