package com.engeto.DU5;

import java.util.ArrayList;
import java.util.List;

public class PlantList {
    private List<Plant> plantList;

    public void PlantList(){
        this.plantList = new ArrayList<>();
    }
    public void PlantList(List<Plant> inputList){
        this.plantList.addAll(inputList);
    }

    public List<Plant> getPlantList(){
        return new ArrayList<>(this.plantList);
    }

    public void addPlant (Plant newPlant){
        this.plantList.add(newPlant);
    }

    public Plant getPlant(int index) throws PlantException{
        if (index < 0){
            throw new PlantException("getPlant error: index lower than 0!");
        }
        if (index > plantList.size()-1){
            throw new PlantException("getPlant error: index larger than number of plants in list! " +
                    "\nRequired Plant No. "+index+1+ " but plantList only has "+ plantList.size()+ " items.");
        }
        return this.plantList.get(index);
    }

    public void remove(Plant plant){
        this.plantList.remove(plant);
    }
}
