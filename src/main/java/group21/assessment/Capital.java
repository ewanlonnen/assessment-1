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

    public void generateReport(ArrayList<Capital> capitalList){
        for (Capital capital : capitalList) {
            String capital_string =
                    String.format("%-30s %-30s %-20s", capital.name, capital.country, capital.population);
            System.out.println(capital_string);
        }
    }
}
