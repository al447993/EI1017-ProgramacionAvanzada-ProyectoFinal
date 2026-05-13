package es.uji.al447993.clasificarGavaraRamos.modelo;

import es.uji.al447993.clasificarGavaraRamos.modelo.interfaces.Algorithms;
import es.uji.al447993.clasificarGavaraRamos.modelo.tables.Table;
import es.uji.al447993.clasificarGavaraRamos.modelo.excepciones.LikedItemNotFoundException;

import java.util.*;

public class RecSys {
    //Atributos
    private Algorithms algoritmo;
    private Map<String,Object> recommend;

    //Constructor
    public RecSys(Algorithms algorithms) {
        this.algoritmo = algorithms;
        this.recommend = new HashMap<>();
    }

    //Métodos
    public void train(Table trainData) {
        algoritmo.train(trainData);
    }

    public void initialise(Table testData, List<String> testItemNames) {
        for (int i = 0; i < testData.getRowCount(); i++) {
            recommend.put(testItemNames.get(i), algoritmo.estimate(testData.getRowAt(i).getData()));
        }
    }

    public List<String> recommend(String nameLikedItem, int numRecommendations) {
        Object gusto;

        if (recommend.containsKey(nameLikedItem))
            gusto = recommend.get(nameLikedItem);
        else
            throw new LikedItemNotFoundException("El elemento " + nameLikedItem + " no existe");

        List<String> catalogo_recommend = new ArrayList<>();
        Set<String> nombres = recommend.keySet();

        for (String nombrePosible : nombres) {
            if(recommend.get(nombrePosible).equals(gusto) && !(nombrePosible.equals(nameLikedItem))) {
                catalogo_recommend.add(nombrePosible);
            }

            if (catalogo_recommend.size() == numRecommendations)
                break;
        }

        return catalogo_recommend;
    }

}
