package com.engeto.pokojoverostliny;

import java.time.DateTimeException;
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

    public PlantInfo(String plantName, LocalDate planted, int frequencyOfWatering) {
        this.plantName = plantName;
        this.notes = "";
        this.planted = planted;
        this.lastWatering = LocalDate.now();
        this.frequencyOfWatering = frequencyOfWatering;

    }

    public PlantInfo(String plantName) {
        this.plantName = plantName;
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
    public static PlantInfo plantParse (String text, String delimiter) throws PlantException{
        delimiter = "\t";
        String [] data = text.split(delimiter);

        String nameOfPlant = data [0];
        String notesAboutPlant = data [1];


        try {
            int frequencyOfWatering = Integer.parseInt(data[2]);
            LocalDate lastWatering = LocalDate.parse(data[3]);
            LocalDate planted = LocalDate.parse(data[4]);

            return new PlantInfo(nameOfPlant, notesAboutPlant, planted, lastWatering, frequencyOfWatering);
        }
        catch (DateTimeException e) {throw new PlantException("Wrong date was inserted" +e.getLocalizedMessage()); }
        catch (NumberFormatException e) {throw new PlantException("Wrong frequency of watering was inserted, "+e.getLocalizedMessage());}

    }
    public String plantFileOutput (String delimiter) {
        return getPlantName() + delimiter + getNotes() + delimiter + getFrequencyOfWatering() +delimiter+ getLastWatering()+delimiter+getPlanted();
    }
    public String getWateringInfo(){
        return "Plant name: "+getPlantName()+", date of last watering: "+lastWatering+", next recommended watering: "+ lastWatering.plusDays(getFrequencyOfWatering());
    }
}
