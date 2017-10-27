package monopoly;

class Location {

    int position;
    String name;
    String type;

    public Location(int position, String name, String type) {
        this.position = position;
        this.name = name;
        this.type = type;
    }

}

public abstract class City extends Location {

    int owner;
    int price;
    int rent;
    int mortgage;
    int OverallRent;
    Boolean owned = false;

    public City(int owner, int price, int rent, int mortgage, int OverallRent, int position, String name, String type) {
        super(position, name, type);
        this.owner = owner;
        this.price = price;
        this.rent = rent;
        this.mortgage = mortgage;
        this.OverallRent = OverallRent;
    }

}

class normalCity extends City {

    int colorID;
    int rent_1house;
    int rent_2house;
    int rent_3house;
    int rent_4house;
    int rent_hotel;
    int houseCost;

    public normalCity(String name, int position, int colorID, int price, int rent, int rent_1house, int rent_2house, int rent_3house, int rent_4house, int rent_hotel, int mortgage, int houseCost) {
        super(-1, price, rent, mortgage, 0, position, name, "city");
        this.colorID = colorID;
        this.rent_1house = rent_1house;
        this.rent_2house = rent_2house;
        this.rent_3house = rent_3house;
        this.rent_4house = rent_4house;
        this.rent_hotel = rent_hotel;
        this.houseCost = houseCost;
        OverallRent = this.rent + this.rent_1house + this.rent_2house + this.rent_3house + this.rent_4house + this.rent_hotel;
    }
}

class RailRoad extends City {

    int rent2;
    int rent3;
    int rent4;

    public RailRoad(String name, int position, int price, int rent, int rent2, int rent3, int rent4, int mortgage) {
        super(-1, price, rent, mortgage, 0, position, name, "railroad");
        this.rent2 = rent2;
        this.rent3 = rent3;
        this.rent4 = rent4;
        OverallRent = rent + rent2 + rent3 + rent4;
    }
}

class Company extends City {

    public Company(String name, int position, int price, int mortgage) {
        //rent is 4 times the amount shown on dice for 1 company & 10 times if the 2 companies are owned
        super(-1, price, 0, mortgage, 0, position, name, "company");

    }
}
