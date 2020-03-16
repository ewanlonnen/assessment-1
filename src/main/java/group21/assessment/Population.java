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

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public long getCountryPop() {
        return countryPop;
    }

    public void setCountryPop(long countryPop) {
        this.countryPop = countryPop;
    }

    public long getCityPop() {
        return cityPop;
    }

    public void setCityPop(long cityPop) {
        this.cityPop = cityPop;
    }

    public String getCityPopPercent() {
        return cityPopPercent;
    }

    public void setCityPopPercent(String cityPopPercent) {
        this.cityPopPercent = cityPopPercent;
    }

    public long getNonCityPop() {
        return nonCityPop;
    }

    public void setNonCityPop(long nonCityPop) {
        this.nonCityPop = nonCityPop;
    }

    public String getNonCityPopPercent() {
        return nonCityPopPercent;
    }

    public void setNonCityPopPercent(String nonCityPopPercent) {
        this.nonCityPopPercent = nonCityPopPercent;
    }

    public void generateReport(ArrayList<Population> popList){
        for (Population population : popList) {
            String population_string =
                    String.format("%-30s %-30s %-20s %-30s %-30s %-20s", population.getCountryName(), population.getCountryPop(), population.getCityPop(), population.getCityPopPercent(), population.getNonCityPop(), population.getNonCityPopPercent());
            System.out.println(population_string);
        }
    }
}
