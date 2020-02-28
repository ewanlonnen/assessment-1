package group21.assessment;

import java.util.ArrayList;

public class City {

    public String name;

    public String country;

    public String district;

    public int population;

    public City(String Name, String Country, String District, int Population) {
        name = Name;
        country = Country;
        district = District;
        population = Population;
    }

    public City(){

    }

    public void generateReport(ArrayList<City> cityList){
        for (City city : cityList) {
            String city_string =
                    String.format("%-30s %-30s %-20s %-20s", city.name, city.country, city.district, city.population);
            System.out.println(city_string);
        }
    }
}
