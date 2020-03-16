package group21.assessment;


import java.util.ArrayList;

public class Capital {

    private String name;

    private String country;

    private int population;


    public Capital(String Name, String Country, int Population) {
        name = Name;
        country = Country;
        population = Population;
    }
    public Capital() {
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

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void generateReport(ArrayList<Capital> capitalList){
        for (Capital capital : capitalList) {
            String capital_string =
                    String.format("%-30s %-30s %-20s", capital.getName(), capital.getCountry(), capital.getPopulation());
            System.out.println(capital_string);
        }
    }
}
