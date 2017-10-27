package monopoly;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Board {

    ArrayList<Location> allCities = new ArrayList<>();

    public Board() {
        initializeAllCities();
        Card.CreateAllCards();

        //sorting
        allCities.sort(new Comparator<Location>() {
            @Override
            public int compare(Location a, Location b) {
                if (a.position < b.position) {
                    return -1;
                } else {
                    return 1;
                }
            }

        });
        //for testing reading files
        for (int i = 0; i < allCities.size(); i++) {
            System.out.println(allCities.get(i).name);
        }
        System.out.println(allCities.size());
    }

    private void initializeAllCities() {
        String fileLocations = "./src/text_files/otherlocations.txt";
        String fileCities = "./src/text_files/cities.txt";
        String fileCompanies = "./src/text_files/companies.txt";
        String fileRailroads = "./src/text_files/railroads.txt";

        //reading cities file
        try (BufferedReader br = new BufferedReader(new FileReader(fileCities))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] cityValues = line.split(",");

                String name = cityValues[0];
                int position = Integer.parseInt(cityValues[1]);
                int colorID = Integer.parseInt(cityValues[2]);
                int price = Integer.parseInt(cityValues[3]);
                int rent = Integer.parseInt(cityValues[4]);
                int rent1 = Integer.parseInt(cityValues[5]);
                int rent2 = Integer.parseInt(cityValues[6]);
                int rent3 = Integer.parseInt(cityValues[7]);
                int rent4 = Integer.parseInt(cityValues[8]);
                int rent_h = Integer.parseInt(cityValues[9]);
                int mortgage = Integer.parseInt(cityValues[10]);
                int houseCost = Integer.parseInt(cityValues[11]);

                normalCity city = new normalCity(name, position, colorID, price, rent, rent1, rent2, rent3, rent4, rent_h, mortgage, houseCost);

                allCities.add(city);
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
                int price = Integer.parseInt(railroadValue[2]);
                int rent = Integer.parseInt(railroadValue[3]);
                int rent2 = Integer.parseInt(railroadValue[4]);
                int rent3 = Integer.parseInt(railroadValue[5]);
                int rent4 = Integer.parseInt(railroadValue[6]);
                int mortgage = Integer.parseInt(railroadValue[7]);

                RailRoad railroad = new RailRoad(name, position, price, rent, rent2, rent3, rent4, mortgage);
                allCities.add(railroad);
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
                int price = Integer.parseInt(companyValue[2]);
                int mortgage = Integer.parseInt(companyValue[3]);

                Company company = new Company(name, position, price, mortgage);
                allCities.add(company);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //reading other locations
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocations))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] locationValue = line.split(",");
                int position = Integer.parseInt(locationValue[0]);
                String name = locationValue[1];
                String type = locationValue[2];

                Location location = new Location(position, name, type);
                allCities.add(location);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
