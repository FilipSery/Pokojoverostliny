package com.engeto.pokojoverostliny;

import java.time.LocalDate;

public class PlantInfo {
    private String plantName;
    private String notes;
    private LocalDate planted;
    private LocalDate lastWatering;
    private int frequencyOfWatering;

    public PlantInfo(String plantName, String notes, LocalDate planted, LocalDate lastWatering, int frequencyOfWatering) {
        this.plantName = plantName;
        this.notes = notes;
        this.planted = planted;
        this.lastWatering = lastWatering;
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public PlantInfo(String notes, LocalDate lastWatering) {
        this.notes = "";
        this.lastWatering = LocalDate.now();
    }

    public PlantInfo(String notes, LocalDate planted, LocalDate lastWatering, int frequencyOfWatering) {
        this.notes = "";
        this.planted = LocalDate.now();
        this.lastWatering = LocalDate.now();
        this.frequencyOfWatering = 7;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
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

    public LocalDate getLastWatering() {
        return lastWatering;
    }

    public void setLastWatering(LocalDate lastWatering) throws PlantException {
        LocalDate date = getPlanted();
        if (lastWatering.isBefore(date)) {
            throw new PlantException("Date of last watering (" + lastWatering +
                    ") can't be before date of planting (" + date + ").");}
        this.lastWatering = lastWatering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering <=0) throw new PlantException(
                "Watering frequency must be at least one day, you entered: "+ frequencyOfWatering);
        this.frequencyOfWatering = frequencyOfWatering;
    }
}
