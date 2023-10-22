package com.engeto.DU5;
import java.util.Comparator;

public class PlantWateringDateComparator implements Comparator<Plant> {

    public int compare(Plant plant1, Plant plant2){
        return plant1.getWatering().compareTo(plant2.getWatering());
    }
}
