package com.engeto.pokojoverostliny;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Paths;



public class PlantsList {
    ArrayList<PlantInfo> plantsList = new ArrayList <>();

    public void addPlant (PlantInfo plant) {
        plantsList.add(plant);
    }
    public PlantInfo getPlant(int plantNumber){
        return plantsList.get(plantNumber);
    }
    public void removePlant(int plantNumber) {
        plantsList.remove(plantNumber);
    }
    public int size () {
        return plantsList.size();
    }
    public void loadPlantsFromFile (String fileName) throws PlantException {
        String delimiter = "\t";
        try (Scanner s = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (s.hasNextLine()) {
                String fromFile = s.nextLine();
                try {
                    this.addPlant(PlantInfo.plantParse(fromFile, delimiter));
                } catch (PlantException e) {
                    throw new PlantException
                            ("Unavailable file (" + fileName + "), " + e.getLocalizedMessage());
                }
            }
        } catch (FileNotFoundException e) {
            throw new PlantException("Input file: "+fileName+" wasn't found"+e.getLocalizedMessage());

        }
    }
    public void exportPlantsToFile (String fileName) throws PlantException {
        String delimiter = "\t";
        int numberOfLine = 0;
        try (PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (PlantInfo plant : plantsList) {
                String plantFile = plant.plantFileOutput(delimiter);
                w.println(plantFile);
                numberOfLine++;
            }
        } catch (IOException e) {
            throw new PlantException("Couldn't write to file: " + fileName + e.getLocalizedMessage());
        }
    }
}
