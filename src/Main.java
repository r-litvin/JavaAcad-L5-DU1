import com.engeto.DU5.Plant;
import com.engeto.DU5.PlantException;
import com.engeto.DU5.PlantList;

import java.time.LocalDate;

/**
 * Java Academy Homework #5
 * * Exceptions
 * * reading & writing text files
 *
 * task12: read data from supplied file 'kvetiny.txt'
 * task13: write watering info on screen
 * task14: add two plants, remove one plant
 * task15: save plant list to file
 * task16: try loading the file back
 * task17 & task18: add comparator for Plants so we can sort by name
 * task19 & task20 - sort by watering status
 * task21 - works correctly for damaged file 'kvetiny-spatne-datum.txt'
 * task22 - works correctly for damaged file 'kvetiny-spatne-frekvence.txt'
 */

public class Main {
    public static void main(String[] args) {
        PlantList myTestPlants = new PlantList();
        testPlant01(myTestPlants); //tests Plant class
        testPlantList01(myTestPlants); //tests PlantList class
        //task12
        PlantList readPlantList = new PlantList();
        readFromFile(readPlantList, "kvetiny.txt"); //tests reading from file in PlantList
        //task13
        printWateringStatus(readPlantList);
        //task14
        testtask14(readPlantList, myTestPlants);
        //task15
        readPlantList.saveToFile("kvetiny-out.txt");
        //task16
        testtask16();
        //task17 & task18
        readPlantList.sortPlantListByName();
        System.out.println("Watering status after sorting by name:");
        printWateringStatus(readPlantList);
        //task19 & task20 - sort by watering status
        readPlantList.sortPlantListByWatering();
        System.out.println("Watering status after sorting by watering date");
        printWateringStatus(readPlantList);
        //task21
        testtask21();
        //task22
        testtask22();

        System.out.println("End OK.");
    }

    private static void testtask22() {
        System.out.println("\ntask22:");
        PlantList mixedPlantList = new PlantList();
        readFromFile(mixedPlantList, "kvetiny-spatne-frekvence.txt");
        printWateringStatus(mixedPlantList);
        System.out.println("End task22.\n");
    }
    private static void testtask21() {
        System.out.println("\ntask21:");
        PlantList mixedPlantList = new PlantList();
        readFromFile(mixedPlantList, "kvetiny-spatne-datum.txt");
        printWateringStatus(mixedPlantList);
        System.out.println("End task21.\n");
    }

    private static void testtask16() {
        System.out.println("\ntask16:");
        PlantList mixedPlantList = new PlantList();
        readFromFile(mixedPlantList, "kvetiny-out.txt");
        printWateringStatus(mixedPlantList);
        System.out.println("End task16.");
    }

    private static void testtask14(PlantList readPlantList, PlantList myTestPlants) {
        //task14
        try {
            readPlantList.addPlant(myTestPlants.getPlant(1));
            readPlantList.addPlant(myTestPlants.getPlant(0));
        } catch (PlantException exc){
            System.err.println("Error adding plants in testtask14: "+exc.getLocalizedMessage());
        }
        try {
            readPlantList.remove(readPlantList.getPlant(0));
        } catch (PlantException exception){
            System.err.println("Error removing plant in testtask14: "+exception.getLocalizedMessage());
        }

    }

    private static void printWateringStatus(PlantList readPlantList) {
        System.out.println("Watering status:");
        readPlantList.getPlantList().stream().forEach(plant
                -> System.out.println(plant.getWateringInfo()));
        System.out.println("\n");
    }

    private static void readFromFile(PlantList readPlantList, String fileName) {
        try {
            readPlantList.readFromFile(fileName);
            System.out.println("PlantList from file '"+fileName+"' contains "+ readPlantList.getNumberOfPlants()+" plants!");
        } catch (PlantException exc){
            System.err.println("Could not read file '"+fileName+ "'\n"+exc.getLocalizedMessage());
        }
    }

    private static void testPlant01(PlantList plantList){
        //test that Plant class works
        //adds all test cases to PlantList variable for another test
        try {
            Plant firstPlant = new Plant("Bamboo"
                    , "from OBI"
                    , LocalDate.of(2021, 5, 12)
                    , LocalDate.now()
                    , 5);
            plantList.addPlant(firstPlant);
            //Short constructors work
            Plant secondPlant = new Plant("Prickly cactus",
                    LocalDate.of(2019, 8, 1),
                    14);
            plantList.addPlant(secondPlant);
            Plant thirdPlant = new Plant("Small bamboo");
            plantList.addPlant(thirdPlant);
            //getters work
            Plant copyPlant = new Plant(secondPlant.getName(),
                    secondPlant.getNotes(),
                    secondPlant.getPlanted(),
                    secondPlant.getWatering(),
                    secondPlant.getFrequencyOfWatering());
            plantList.addPlant(copyPlant);
            //setters work
            thirdPlant.setName("Bamboo orientalis");
            thirdPlant.setPlanted(LocalDate.of(2023, 10, 10));
            thirdPlant.setNotes("Grows well in 2023");
            thirdPlant.setWatering(LocalDate.now());
            thirdPlant.setFrequencyOfWatering(6);
            System.out.println("Third plant info: "+thirdPlant.getWateringInfo());
            //thirdPlant.setFrequencyOfWatering(0); //works as of 2023-10-22
            //thirdPlant.setWatering(LocalDate.of(1982, 4,4)); //works as of 2023-10-22

        } catch (PlantException exception) {
            System.err.println("Error setting up Plants: "+exception.getLocalizedMessage());
        }

        System.out.println("testPlant01 done.");
    }

    private static void testPlantList01(PlantList plantList){
        String myName = "testPlantList01";
        //getNumberOfPlants works?
        System.out.println(myName+": plantList has size: "+plantList.getNumberOfPlants());
        // adding plants works, is tested in testPlant01
        int testIndex = 0;
        try {
            //getPlant works?
            Plant testPlant = plantList.getPlant(testIndex);
            System.out.println(testPlant.getWateringInfo());
        } catch (PlantException exception){
            System.err.println(myName+" has trouble getting Plant #"+testIndex
                    + " while plantList has size "+plantList.getNumberOfPlants()+"! "+
                    exception.getLocalizedMessage());
        }
        //test PlantList.remove
        try {
            Plant testPlant = new Plant("rotten cactus",
                    "", LocalDate.of(1999, 9, 19),
                    LocalDate.now().minusDays(1), 14);
            plantList.addPlant(testPlant); //comment out if you want to check the Exception
            plantList.remove(testPlant);
        } catch (PlantException exception){
            System.err.println("Error removing testPlant: "+exception.getLocalizedMessage());
        }

        System.out.println(myName+" done.");
    }


}