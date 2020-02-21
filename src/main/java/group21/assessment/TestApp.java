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
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to a database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
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
                /*c.code = rset.getString("country.Code");
                c.name = rset.getString("country.Name");
                c.continent = rset.getString("country.Continent");
                c.region = rset.getString("country.Region");
                c.population = rset.getInt("country.Population");
                c.capital = rset.getString("city.Name");*/

                return c;
            }
            return null;
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
                /*c.code = rset.getString("country.Code");
                c.name = rset.getString("country.Name");
                c.continent = rset.getString("country.Continent");
                c.region = rset.getString("country.Region");
                c.population = rset.getInt("country.Population");
                c.capital = rset.getString("city.Name");*/
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
            String strSelect = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.name  FROM country, city WHERE country.Capital = city.ID and country.Continent = " + "'" + s_continent + "'" + " ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Country> countryList = new ArrayList<Country>();
            while (rset.next()) {
                Country c = new Country(rset.getString("country.Code"), rset.getString("country.Name"), rset.getString("country.Continent"),rset.getString("country.Region"),rset.getInt("country.Population"),rset.getString("city.Name"));
                /*c.code = rset.getString("country.Code");
                c.name = rset.getString("country.Name");
                c.continent = rset.getString("country.Continent");
                c.region = rset.getString("country.Region");
                c.population = rset.getInt("country.Population");
                c.capital = rset.getString("city.Name");*/
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

    public static void main(String[] args) {
        // Create new Application
        TestApp a = new TestApp();


        // Connect to database
        a.connect();
        ArrayList<Country> c = a.getCountryByPopulation();
        for (Country country : c) {
            String country_string =
                    String.format("%-5s %-30s %-20s %-20s %-15s %-15s", country.code, country.name, country.continent, country.region, country.population, country.capital);
            System.out.println(country_string);
        }

        ArrayList<Country> c1 = a.getCountryByContinent("Europe");
        for (Country country : c1) {
            String country_string =
                    String.format("%-5s %-30s %-20s %-20s %-15s %-15s", country.code, country.name, country.continent, country.region, country.population, country.capital);
            System.out.println(country_string);
        }
        Country individualCountry = a.getCountry("Syria");
        //a.displayCountry(c);
        a.displayCountry(individualCountry);
        // Disconnect from database
        a.disconnect();
    }
}