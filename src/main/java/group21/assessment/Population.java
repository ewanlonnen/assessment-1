package group21.assessment;

import java.util.ArrayList;

public class Population {

    private String countryName;

    private long countryPop;

    private long cityPop;

    private String cityPopPercent;

    private long nonCityPop;

    private String nonCityPopPercent;

    public Population(String countryName, long countryPop, long cityPop, String cityPopPercent, long nonCityPop, String getNonCityPopPercent) {
        this.countryName = countryName;
        this.countryPop = countryPop;
        this.cityPop = cityPop;
        this.cityPopPercent = cityPopPercent;
        this.nonCityPop = nonCityPop;
        this.nonCityPopPercent = getNonCityPopPercent;
    }
    public Population(){

    }

    public void generateReport(ArrayList<Population> popList){
        for (Population population : popList) {
            String population_string =
                    String.format("%-30s %-30s %-20s %-30s %-30s %-20s", population.countryName, population.countryPop, population.cityPop, population.cityPopPercent, population.nonCityPop, population.nonCityPopPercent);
            System.out.println(population_string);
        }
    }
}
