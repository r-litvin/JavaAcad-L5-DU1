package com.engeto.DU5;

import java.time.LocalDate;

public class Plant {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering)
            throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.setWatering(watering);
        this.setFrequencyOfWatering(frequencyOfWatering);

    }

    public Plant(String name, LocalDate planted, int frequencyOfWatering)  throws PlantException {
        this(name, "", planted, LocalDate.now(), frequencyOfWatering);
    }
    public Plant(String name) throws PlantException{
        this(name, "", LocalDate.now(), LocalDate.now(), 7);
    }

    //region: setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) throws PlantException {
        if (watering.isBefore(this.planted)) {
            throw new PlantException("Error setting watering date: " +
                    "cannot be earlier that planting date. \nWatering set to "
                    + watering + " but planted date is " + this.planted);
        }
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering (int frequencyOfWatering) throws PlantException{
        if (frequencyOfWatering > 0) {
            this.frequencyOfWatering = frequencyOfWatering;
        } else {
            throw new PlantException("Watering frequency set to "+frequencyOfWatering
                    +"! Must be greater than 0!");
        }

    }
    //endregion

    /**
     * metodu getWateringInfo(), která vrátí
     * název květiny,
     * datum poslední zálivky a
     * datum doporučené další zálivky.
     * (Metoda vrátí textový řetězec, obsahující požadované informace.)
     */
    public String getWateringInfo(){
        String output = "";
        output += this.name + ", watered ";
        output += this.watering + ", water again on ";
        output += this.watering.plusDays(this.frequencyOfWatering);
        return output;
    }
}
