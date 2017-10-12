package monopoly;


public abstract class City {
    
    String name;
    int price;
    int rent;
    int mortgage;
    Boolean owned= false;
}

class normalCity extends City{
    
    String color;
    int rent_1house;
    int rent_2house;
    int rent_3house;
    int rent_4house;
    int rent_hotel;
    int houseCost;

    public normalCity(String name, String color, int price, int rent, int rent_1house, int rent_2house, int rent_3house, int rent_4house, int rent_hotel, int mortgage, int houseCost) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.rent = rent;
        this.rent_1house = rent_1house;
        this.rent_2house = rent_2house;
        this.rent_3house = rent_3house;
        this.rent_4house = rent_4house;
        this.rent_hotel = rent_hotel;
        this.mortgage = mortgage;
        this.houseCost = houseCost;
    }
}

class RailRoad extends City{
    
    int rent2;
    int rent3;
    int rent4;

    public RailRoad(String name, int price, int rent, int rent2, int rent3, int rent4, int mortgage) {
        this.name = name;
        this.price = price;
        this.rent = rent;
        this.rent2 = rent2;
        this.rent3 = rent3;
        this.rent4 = rent4;
        this.mortgage = mortgage;
    }
}

class Company extends City{

    public Company(String name, int price, int mortgage) {
        this.name = name;
        this.price = price;
        this.mortgage = mortgage;
        
        //rent is 4 times the amount shown on dice for 1 company & 10 times if the 2 companies are owned
        this.rent = 0; 
    }
}
