package group21.assessment;

import java.util.ArrayList;

public class Country {

    private String code;

    private String name;

    private String continent;

    private String region;

    private int population;

    private String capital;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Country(String Code, String Name, String Continent, String Region, int Population, String Capital){
        code = Code;
        name = Name;
        continent = Continent;
        region = Region;
        population = Population;
        capital = Capital;
    }
    public Country(){

    }

    public void generateReport(ArrayList<Country> regionPop){
        for (Country country : regionPop) {
            String country_string =
                    String.format("%-5s %-30s %-20s %-20s %-15s %-15s", country.getCode(), country.getName(), country.getContinent(), country.getRegion(), country.getPopulation(), country.getCapital());
            System.out.println(country_string);
        }
    }

}

