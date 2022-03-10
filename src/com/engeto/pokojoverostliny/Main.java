package com.engeto.pokojoverostliny;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        PlantsList test = new PlantsList();
        try {
            test.loadPlantsFromFile("kvetiny-spatne-frekvence.txt");
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }
        for (int i = 0; i < test.size(); i++) {
            System.out.println(test.getPlant(i).getWateringInfo());
        }
        test.addPlant(new PlantInfo("Kaktus"));
        test.addPlant(new PlantInfo("Agave", LocalDate.now(), 5));
        test.removePlant(1);

        try { test.exportPlantsToFile("vystup.txt");
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}

