package monopoly;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Board {

    ArrayList<normalCity> normalCities = new ArrayList<>(); // 22 cities
    ArrayList<RailRoad> railRoads = new ArrayList<>(); // 4 railroads
    ArrayList<Company> companies = new ArrayList<>(); // 2 companies

    public Board() {
        initializeAllCities();

        //for testing reading files
        for (int i = 0; i < normalCities.size(); i++) {
            System.out.println(normalCities.get(i).name);
        }
        for (int i = 0; i < railRoads.size(); i++) {
            System.out.println(railRoads.get(i).name);
        }
        for (int i = 0; i < companies.size(); i++) {
            System.out.println(companies.get(i).name);
        }
    }

    private void initializeAllCities() {
        String fileCities = ".//src//text_files//cities.txt";
        String fileCompanies = ".//src//text_files//companies.txt";
        String fileRailroads = ".//src//text_files//railroads.txt";

        //reading cities file
        try (BufferedReader br = new BufferedReader(new FileReader(fileCities))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] cityValues = line.split(",");

                String name = cityValues[0];
                
                
                //int position = Integer.parseInt(cityValues[1]);
                int position = 0; //fix this david pls
                
                
                
                String color = cityValues[1];
                int colorID = Integer.parseInt(cityValues[3]);
                int price = Integer.parseInt(cityValues[2]);
                int rent = Integer.parseInt(cityValues[3]);
                int rent1 = Integer.parseInt(cityValues[4]);
                int rent2 = Integer.parseInt(cityValues[5]);
                int rent3 = Integer.parseInt(cityValues[6]);
                int rent4 = Integer.parseInt(cityValues[7]);
                int rent_h = Integer.parseInt(cityValues[8]);
                int mortgage = Integer.parseInt(cityValues[9]);
                int houseCost = Integer.parseInt(cityValues[10]);

                normalCity city = new normalCity(name, position, color, colorID, price, rent, rent1, rent2, rent3, rent4, rent_h, mortgage, houseCost);
                normalCities.add(city);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //reading railroads file
        try (BufferedReader br = new BufferedReader(new FileReader(fileRailroads))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] railroadValue = line.split(",");

                String name = railroadValue[0];
                int position = Integer.parseInt(railroadValue[1]);
                int price = Integer.parseInt(railroadValue[1]);
                int rent = Integer.parseInt(railroadValue[2]);
                int rent2 = Integer.parseInt(railroadValue[3]);
                int rent3 = Integer.parseInt(railroadValue[4]);
                int rent4 = Integer.parseInt(railroadValue[5]);
                int mortgage = Integer.parseInt(railroadValue[6]);

                RailRoad railroad = new RailRoad(name, position, price, rent, rent2, rent3, rent4, mortgage);
                railRoads.add(railroad);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //reading companies file
        try (BufferedReader br = new BufferedReader(new FileReader(fileCompanies))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] companyValue = line.split(",");

                String name = companyValue[0];
                int position = Integer.parseInt(companyValue[1]);
                int price = Integer.parseInt(companyValue[1]);
                int mortgage = Integer.parseInt(companyValue[2]);

                Company company = new Company(name, position, price, mortgage);
                companies.add(company);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
