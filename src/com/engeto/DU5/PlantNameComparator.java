package com.engeto.DU5;
import java.util.Comparator;

public class PlantNameComparator implements Comparator<Plant> {

    public int compare(Plant plant1, Plant plant2){
        return plant1.getName().compareTo(plant2.getName());
    }
}
