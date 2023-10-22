import com.engeto.DU5.Plant;
import com.engeto.DU5.PlantException;
import com.engeto.DU5.PlantList;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        PlantList myTestPlants = new PlantList();
        testPlant01(myTestPlants);
        testPlantList01(myTestPlants);
        PlantList readPlantList = new PlantList();
        String fileName = "kvetiny.txt";
        try {
            readPlantList.readFromFile(fileName);
            System.out.println("readPlantList contains "+readPlantList.getNumberOfPlants()+" plants!");
        } catch (PlantException exc){
            System.err.println("Could not read file "+fileName+ "\n"+exc.getLocalizedMessage());
        }

        System.out.println("End OK.");


    }
    private static void testPlant01(PlantList plantList){
        //test that Plant class works
        //add all test cases to PlantList variable for another test
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
        // adding plants works, tested in testPlant01
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