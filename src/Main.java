import com.engeto.DU5.Plant;
import com.engeto.DU5.PlantException;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        TestPlant01();
        System.out.println("End OK.");


    }
    private static void TestPlant01(){
        //Plant class works
        try {
            Plant firstPlant = new Plant("Bamboo"
                    , "from OBI"
                    , LocalDate.of(2021, 5, 12)
                    , LocalDate.now()
                    , 5);
            //Short constructors work
            Plant secondPlant = new Plant("Prickly cactus",
                    LocalDate.of(2019, 8, 1),
                    14);
            Plant thirdPlant = new Plant("Small bamboo");
            //getters work
            Plant copyPlant = new Plant(secondPlant.getName(),
                    secondPlant.getNotes(),
                    secondPlant.getPlanted(),
                    secondPlant.getWatering(),
                    secondPlant.getFrequencyOfWatering());
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

        System.out.println("TestPlant01 done.");
    }
}