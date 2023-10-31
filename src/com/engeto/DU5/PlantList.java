package com.engeto.DU5;


import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PlantList {
    private List<Plant> plantList = new ArrayList<>();

    public PlantList(List<Plant> inputList){
        this.plantList.addAll(inputList);
    }

    public PlantList() {}

    public List<Plant> getPlantList(){
        return new ArrayList<>(this.plantList);
    }

    public int getNumberOfPlants(){
        return this.plantList.size();
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
                    "\nRequired Plant No. "+index+ " but plantList only has "+ plantList.size()+ " items.");
        }
        return this.plantList.get(index);
    }

    public void remove(Plant plant) throws PlantException{ //optionally write also remove(int index) ?
        if (this.plantList.contains(plant)){
            this.plantList.remove(plant);
        } else {
            throw new PlantException("Error removing Plant from list. Plant '"
                    +plant+"' does not exist!");
        }
    }

    public void readFromFile(String fileName) throws PlantException{
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                try { //are nested try {} catch (){} a good idea?
                    parseLine(line);
                } catch (PlantException exception){
                    throw new PlantException("Could not parse line: "+exception.getLocalizedMessage());
                }

            }
        } catch (FileNotFoundException exception){
            throw new PlantException("File: '"+fileName+"' not found! "+ exception.getLocalizedMessage());
        }
    }

    private void parseLine(String string) throws PlantException{
        String[] lineItems = string.split("\t");
        if (lineItems.length != 5){
            throw new PlantException("Input file format error: incorrect number of items on line. " +
                    "Line is: "+string);
        }
        try {
            //data order in the supplied file seems to be:
            //name, description, watering frequency, watering date, planted date
            // ??
            String plantName = lineItems[0].trim();
            String plantDescription = lineItems[1].trim();
            int freqWatering = Integer.parseInt(lineItems[2].trim());
            LocalDate watering = LocalDate.parse(lineItems[3].trim());
            LocalDate planted = LocalDate.parse(lineItems[4].trim());

            this.addPlant(new Plant(plantName, plantDescription, planted, watering, freqWatering));

        } catch (DateTimeParseException exc){ //from LocalDate.parse
            throw new PlantException("Could not read dates from file line: "+string
                    +"\n"+exc.getLocalizedMessage());
        } catch (NumberFormatException exc){ //from Integer.parseInt
            throw new PlantException("Could not parse watering frequency from file on line: "+string
                    +"\n"+exc.getLocalizedMessage());
        }
    }

    public void saveToFile(String filename){
        List<String> toPrint =
                this.getPlantList().stream().map(Plant::stringToFile).toList();
        try (PrintWriter outputWriter = new PrintWriter(new BufferedWriter(new FileWriter(filename)))){
            for (String newLine : toPrint){
                outputWriter.println(newLine);
            }
        } catch (IOException exc){
            System.err.println("Could not write file "+filename+" in savetoFile.\n"
                    +exc.getLocalizedMessage());
        }
    }

    public void saveToFileUsingStream(String filename){
        //List<String> toPrint =
                //this.getPlantList().stream().map(Plant::stringToFile).toList();
        try (PrintWriter outputWriter = new PrintWriter(new BufferedWriter(new FileWriter(filename)))){
            this.getPlantList().stream().map(Plant::stringToFile).toList().forEach(outputWriter::println);
        } catch (IOException exc) {
            System.err.println("Could not write file " + filename + " in savetoFile.\n"
                    + exc.getLocalizedMessage());
        }
    }

    public void sortPlantListByName() {
        Collections.sort(this.plantList, new PlantNameComparator());
    }

    public void sortPlantListByWatering(){
        Collections.sort(this.plantList, new PlantWateringDateComparator());
        //could implement if (){} else {} for reverse sorting here
        //or write another method with reverse sort
    }
}
