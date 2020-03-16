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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void generateReport(ArrayList<City> cityList){
        for (City city : cityList) {
            String city_string =
                    String.format("%-30s %-30s %-20s %-20s", city.getName(), city.getCountry(), city.getDistrict(), city.getPopulation());
            System.out.println(city_string);
        }
    }
}
