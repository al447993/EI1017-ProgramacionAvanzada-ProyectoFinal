package es.uji.al447993.clasificarFlores;

import es.uji.al447993.clasificarFlores.algorithms.Algorithms;
import es.uji.al447993.clasificarFlores.tables.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecSys {
    //Atributos
    private Algorithms algoritmo;
    private Map<String,Integer> recommend;

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

    }

    public List<String> recommend(String nameLikedItem, int numRecommendations) {
        return new ArrayList<>();
    }

}
