package group21.assessment;

import java.sql.*;
import java.util.ArrayList;

public class TestApp {
    /**
     * Connection to MySQL database.
     */
    public Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect(String location)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }


    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    //get single countries
    public Country getCountry(String cName) {
        if (cName == null)
        {
            System.out.println("No employees");
        }
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.name FROM country, city WHERE country.Capital = city.ID and country.Name = " + "'" + cName + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            //ArrayList<Country> countryList = new ArrayList<Country>();
            while (rset.next()) {
                Country c = new Country(rset.getString("country.Code"), rset.getString("country.Name"), rset.getString("country.Continent"),rset.getString("country.Region"),rset.getInt("country.Population"),rset.getString("city.Name"));
                return c;
            }
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    //get all countries
    public ArrayList<Country> getAllCountries() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.name FROM world.country left join world.city on country.Capital = city.ID";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Country> countryList = new ArrayList<Country>();
            while (rset.next()) {
                Country c = new Country(rset.getString("country.Code"), rset.getString("country.Name"), rset.getString("country.Continent"),rset.getString("country.Region"),rset.getInt("country.Population"),rset.getString("city.Name"));
                countryList.add(c);
            }
            return countryList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    //get all countries and order by population
    public ArrayList<Country> getCountryByPopulation() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.name FROM world.country left join world.city on country.Capital = city.ID order by country.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Country> countryList = new ArrayList<Country>();
            while (rset.next()) {
                Country c = new Country(rset.getString("country.Code"), rset.getString("country.Name"), rset.getString("country.Continent"),rset.getString("country.Region"),rset.getInt("country.Population"),rset.getString("city.Name"));
                countryList.add(c);
            }
            return countryList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    //get all countries in a single continent and order by population
    public ArrayList<Country> getCountryByContinent(String s_continent) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.name FROM world.country left join world.city on country.Capital = city.ID WHERE country.Continent = " + "'" + s_continent + "'" + " order by country.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Country> countryList = new ArrayList<Country>();
            while (rset.next()) {
                Country c = new Country(rset.getString("country.Code"), rset.getString("country.Name"), rset.getString("country.Continent"),rset.getString("country.Region"),rset.getInt("country.Population"),rset.getString("city.Name"));
                countryList.add(c);
            }
            return countryList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public ArrayList<Country> getCountryByRegion(String s_region) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.name FROM world.country left join world.city on country.Capital = city.ID WHERE country.Region = " + "'" + s_region + "'" + " order by country.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Country> countryList = new ArrayList<Country>();
            while (rset.next()) {
                Country c = new Country(rset.getString("country.Code"), rset.getString("country.Name"), rset.getString("country.Continent"),rset.getString("country.Region"),rset.getInt("country.Population"),rset.getString("city.Name"));
                countryList.add(c);
            }
            return countryList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public ArrayList<Country> top_N_Continent(String s_continent, int numberOfContinents) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.name FROM world.country left join world.city on country.Capital = city.ID WHERE country.Continent = " + "'" + s_continent + "'" + " order by country.Population DESC LIMIT " + numberOfContinents;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Country> countryList = new ArrayList<Country>();
            while (rset.next()) {
                Country c = new Country(rset.getString("country.Code"), rset.getString("country.Name"), rset.getString("country.Continent"),rset.getString("country.Region"),rset.getInt("country.Population"),rset.getString("city.Name"));
                countryList.add(c);
            }
            return countryList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public void displayCountry(Country c) {
        if (c != null) {
            System.out.println(
                   c.code + " "
                            + c.name + " "
                            + c.continent + " "
                            + c.region + " "
                            + c.population + " "
                            + c.capital +"\n");
        }
    }

    //top N populated countries
    public ArrayList<Country> topPopulatedCountries(int limit){
        TestApp temp = new TestApp();
        ArrayList<Country> countries = temp.getCountryByPopulation();
        ArrayList<Country> topCountries = new ArrayList<Country>();
        for (int i = 0; i < limit; i++){
            topCountries.add(countries.get(i));
        }
        return topCountries;
    }

    public static void main(String[] args) {
        // Create new Application
        TestApp a = new TestApp();
        Country c = new Country();

        // Connect to database
        a.connect("localhost:33060");
        ArrayList<Country> worldPop = a.getCountryByPopulation();
        c.generateReport(worldPop);

        ArrayList<Country> continentPop = a.getCountryByContinent("Europe");
        c.generateReport(continentPop);

        ArrayList<Country> regionPop = a.getCountryByRegion("Polynesia");
        c.generateReport(regionPop);

        ArrayList<Country> NCountries = a.top_N_Continent("Europe",3);
        c.generateReport(NCountries);


        c.generateReport(a.topPopulatedCountries(6));

        Country individualCountry = a.getCountry("Syria");
        //a.displayCountry(c);
        a.displayCountry(individualCountry);
        // Disconnect from database
        a.disconnect();
    }
}