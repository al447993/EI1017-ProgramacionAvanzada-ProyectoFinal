package es.uji.al447993.clasificarGavaraRamos.modelo;

import es.uji.al447993.clasificarGavaraRamos.modelo.interfaces.Algorithms;
import es.uji.al447993.clasificarGavaraRamos.modelo.algorithms.KNN;
import es.uji.al447993.clasificarGavaraRamos.modelo.algorithms.KMeans;
import es.uji.al447993.clasificarGavaraRamos.modelo.interfaces.Distance;
import es.uji.al447993.clasificarGavaraRamos.modelo.lecturaFicheros.CSVLabeledFileReader;
import es.uji.al447993.clasificarGavaraRamos.modelo.lecturaFicheros.CSVUnlabeledFileReader;
import es.uji.al447993.clasificarGavaraRamos.modelo.tables.Table;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

class SongRecSys {
    private RecSys recsys;
    private Distance distancia;

    public SongRecSys(Distance distancia) {
        this.distancia = distancia;
    }

    SongRecSys(String method) throws Exception {
        String sep = System.getProperty("file.separator");
        String ruta = "recsys";

        // File names (could be provided as arguments to the constructor to be more general)
        Map<String,String> filenames = new HashMap<>();
        filenames.put("knn"+"train",ruta+sep+"songs_train.csv");
        filenames.put("knn"+"test",ruta+sep+"songs_test.csv");
        filenames.put("kmeans" +"train",ruta+sep+"songs_train_withoutnames.csv");
        filenames.put("kmeans" +"test",ruta+sep+"songs_test_withoutnames.csv");

        // Algorithms
        Map<String, Algorithms> algorithms = new HashMap<>();
        algorithms.put("knn",new KNN(distancia));
        algorithms.put("kmeans",new KMeans(15, 200, 4321,distancia));

        // Tables
        Map<String, Table> tables = new HashMap<>();
        CSVLabeledFileReader labeledReader = new CSVLabeledFileReader();
        CSVUnlabeledFileReader unlabeledReader = new CSVUnlabeledFileReader();

        tables.put("knn" + "train", labeledReader.readTableFromSource(filenames.get("knn" + "train")));
        tables.put("knn" + "test", labeledReader.readTableFromSource(filenames.get("knn" + "test")));
        tables.put("kmeans" + "train", unlabeledReader.readTableFromSource(filenames.get("kmeans" + "train")));
        tables.put("kmeans" + "test", unlabeledReader.readTableFromSource(filenames.get("kmeans" + "test")));

        // Names of items
        List<String> names = readNames(ruta+sep+"songs_test_names.csv");

        // Start the RecSys
        this.recsys = new RecSys(algorithms.get(method));
        this.recsys.train(tables.get(method+"train"));
        this.recsys.initialise(tables.get(method+"test"), names);

        // Given a liked item, ask for a number of recomendations
        String liked_name = "Lootkemia";
        List<String> recommended_items = this.recsys.recommend(liked_name,5);

        // Display the recommendation text (to be replaced with graphical display with JavaFX implementation)
        reportRecommendation(liked_name,recommended_items);
    }

    private List<String> readNames(String fileOfItemNames) throws IOException, URISyntaxException {
        String path = getClass().getClassLoader().getResource(fileOfItemNames).toURI().getPath();

        List<String> names = new ArrayList<>();
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNextLine()) {
            names.add(scanner.nextLine());
        }
        scanner.close();
        return names;
    }

    private void reportRecommendation(String liked_name, List<String> recommended_items) {
        System.out.println("If you liked \""+liked_name+"\" then you might like:");
        for (String name : recommended_items)
        {
            System.out.println("\t * "+name);
        }
    }

    public static void main(String[] args) throws Exception {
        new SongRecSys("knn");
        new SongRecSys("kmeans");
    }
}