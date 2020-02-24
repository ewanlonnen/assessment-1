package group21.assessment;

import java.util.ArrayList;

public class Country {

    public String code;

    public String name;

    public String continent;

    public String region;

    public int population;

    public String capital;

    Country(String Code, String Name, String Continent,String Region,int Population,String Capital){
        code = Code;
        name = Name;
        continent = Continent;
        region = Region;
        population = Population;
        capital = Capital;
    }
    Country(){

    }

    public void generateReport(ArrayList<Country> regionPop){
        for (Country country : regionPop) {
            String country_string =
                    String.format("%-5s %-30s %-20s %-20s %-15s %-15s", country.code, country.name, country.continent, country.region, country.population, country.capital);
            System.out.println(country_string);
        }
    }

}

