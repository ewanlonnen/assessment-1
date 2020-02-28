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

    //top N populated regions
    public ArrayList<Country> top_N_Region(String s_region, int numberOfContinents) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.name FROM world.country left join world.city on country.Capital = city.ID WHERE country.Region = " + "'" + s_region + "'" + " order by country.Population DESC LIMIT " + numberOfContinents;
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
    public ArrayList<Country> top_N_Countries(int limit) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.name FROM world.country left join world.city on country.Capital = city.ID order by country.Population DESC LIMIT " + limit;
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

    //top N populated countries
    public ArrayList<City> citiesByPop() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name, country.Name, city.District, city.Population from world.city left join world.country on city.CountryCode = country.Code order by city.Population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cityList = new ArrayList<City>();
            while (rset.next()) {
                City c = new City(rset.getString("city.Name"), rset.getString("country.Name"), rset.getString("city.District"),rset.getInt("city.Population"));
                cityList.add(c);
            }
            return cityList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    //top N populated countries
    public ArrayList<City> citiesByContinent(String s_continent) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name, country.Name, city.District, city.Population from world.city left join world.country on city.CountryCode = country.Code where country.Continent = " + "'" + s_continent + "'" + "order by city.Population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cityList = new ArrayList<City>();
            while (rset.next()) {
                City c = new City(rset.getString("city.Name"), rset.getString("country.Name"), rset.getString("city.District"),rset.getInt("city.Population"));
                cityList.add(c);
            }
            return cityList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    //populated districts
    public ArrayList<City> citiesByDistrict(String s_district) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name, country.Name, city.District, city.Population from world.city left join world.country on city.CountryCode = country.Code where city.District = " + "'" + s_district + "'" + "order by city.Population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cityList = new ArrayList<City>();
            while (rset.next()) {
                City c = new City(rset.getString("city.Name"), rset.getString("country.Name"), rset.getString("city.District"),rset.getInt("city.Population"));
                cityList.add(c);
            }
            return cityList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    //populated cities in region
    public ArrayList<City> citiesByRegion(String s_region) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name, country.Name, city.District, city.Population from world.city left join world.country on city.CountryCode = country.Code where country.Region = " + "'" + s_region + "'" + "order by city.Population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cityList = new ArrayList<City>();
            while (rset.next()) {
                City c = new City(rset.getString("city.Name"), rset.getString("country.Name"), rset.getString("city.District"),rset.getInt("city.Population"));
                cityList.add(c);
            }
            return cityList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }


    //populated cities in country
    public ArrayList<City> citiesByCountry(String s_country) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name, country.Name, city.District, city.Population from world.city left join world.country on city.CountryCode = country.Code where country.Name = " + "'" + s_country + "'" + "order by city.Population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cityList = new ArrayList<City>();
            while (rset.next()) {
                City c = new City(rset.getString("city.Name"), rset.getString("country.Name"), rset.getString("city.District"),rset.getInt("city.Population"));
                cityList.add(c);
            }
            return cityList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    //populated districts
    public ArrayList<City> top_N_citiesByDistrict(String s_district, int limit) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name, country.Name, city.District, city.Population from world.city left join world.country on city.CountryCode = country.Code where city.District = " + "'" + s_district + "'" + "order by city.Population desc limit " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cityList = new ArrayList<City>();
            while (rset.next()) {
                City c = new City(rset.getString("city.Name"), rset.getString("country.Name"), rset.getString("city.District"),rset.getInt("city.Population"));
                cityList.add(c);
            }
            return cityList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    //populated cities in country
    public ArrayList<City> top_N_citiesByCountry(String s_country, int limit) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name, country.Name, city.District, city.Population from world.city left join world.country on city.CountryCode = country.Code where country.Name = " + "'" + s_country + "'" + "order by city.Population desc limit " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cityList = new ArrayList<City>();
            while (rset.next()) {
                City c = new City(rset.getString("city.Name"), rset.getString("country.Name"), rset.getString("city.District"),rset.getInt("city.Population"));
                cityList.add(c);
            }
            return cityList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    //populated cities in region
    public ArrayList<City> top_N_citiesByRegion(String s_region, int limit) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name, country.Name, city.District, city.Population from world.city left join world.country on city.CountryCode = country.Code where country.Region = " + "'" + s_region + "'" + "order by city.Population desc limit " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cityList = new ArrayList<City>();
            while (rset.next()) {
                City c = new City(rset.getString("city.Name"), rset.getString("country.Name"), rset.getString("city.District"),rset.getInt("city.Population"));
                cityList.add(c);
            }
            return cityList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    //top N populated countries
    public ArrayList<City> top_N_citiesByContinent(String s_continent, int limit) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name, country.Name, city.District, city.Population from world.city left join world.country on city.CountryCode = country.Code where country.Continent = " + "'" + s_continent + "'" + "order by city.Population desc limit " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cityList = new ArrayList<City>();
            while (rset.next()) {
                City c = new City(rset.getString("city.Name"), rset.getString("country.Name"), rset.getString("city.District"),rset.getInt("city.Population"));
                cityList.add(c);
            }
            return cityList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    //top N populated countries
    public ArrayList<City> top_N_citiesByPop(int limit) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name, country.Name, city.District, city.Population from world.city left join world.country on city.CountryCode = country.Code order by city.Population desc limit " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cityList = new ArrayList<City>();
            while (rset.next()) {
                City c = new City(rset.getString("city.Name"), rset.getString("country.Name"), rset.getString("city.District"),rset.getInt("city.Population"));
                cityList.add(c);
            }
            return cityList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public static void main(String[] args) {
        // Create new Application
        TestApp a = new TestApp();
        Country country = new Country();
        City city = new City();

        // Connect to database
        a.connect();
        /*ArrayList<Country> worldPop = a.getCountryByPopulation();
        country.generateReport(worldPop);

        ArrayList<Country> continentPop = a.getCountryByContinent("Europe");
        country.generateReport(continentPop);

        ArrayList<Country> regionPop = a.getCountryByRegion("Polynesia");
        country.generateReport(regionPop);

        ArrayList<Country> NCountriesByContinent = a.top_N_Continent("Europe",3);
        country.generateReport(NCountriesByContinent);

        ArrayList<Country> NCountries = a.top_N_Countries(3);
        country.generateReport(NCountries);

        ArrayList<Country> NCountriesByRegion = a.top_N_Region("Western Europe",3);
        country.generateReport(NCountriesByRegion);
*/
/*        ArrayList<City> cityList = a.citiesByPop();
        city.generateReport(cityList);

        ArrayList<City> cityListContinent = a.citiesByContinent("Europe");
        city.generateReport(cityListContinent);

        ArrayList<City> cityListDistrict = a.citiesByDistrict("England");
        city.generateReport(cityListDistrict);

        ArrayList<City> cityListRegion = a.citiesByRegion("Caribbean");
        city.generateReport(cityListRegion);

        ArrayList<City> cityListCountry = a.citiesByCountry("France");
        city.generateReport(cityListCountry);*/

/*        ArrayList<City> topCityListDistrict = a.top_N_citiesByDistrict("England", 5);
        city.generateReport(topCityListDistrict);

        ArrayList<City> topCityListCountry = a.top_N_citiesByCountry("France", 4);
        city.generateReport(topCityListCountry);

        ArrayList<City> topCityListRegion = a.top_N_citiesByRegion("Caribbean", 15);
        city.generateReport(topCityListRegion);

        ArrayList<City> topCityListContinent = a.top_N_citiesByContinent("Europe", 9);
        city.generateReport(topCityListContinent);

        ArrayList<City> topCityList = a.top_N_citiesByPop(6);
        city.generateReport(topCityList);*/

        Country individualCountry = a.getCountry("Syria");
        //a.displayCountry(c);
        a.displayCountry(individualCountry);
        // Disconnect from database
        a.disconnect();
    }
}