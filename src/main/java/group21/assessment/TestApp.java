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
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/employees?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
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

    //top N populated cities in districts
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

    //top N populated cities in country
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

    //top N populated cities in region
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

    //top N populated cities in countries
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

    //top N populated cities in world
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

// capital cities by population
    public ArrayList<Capital> capitalsByPop() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name, country.Name, city.Population FROM world.country left join world.city on country.Capital = city.ID order by city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Capital> cityList = new ArrayList<Capital>();
            while (rset.next()) {
                Capital c = new Capital(rset.getString("city.Name"), rset.getString("country.Name"),rset.getInt("city.Population"));
                cityList.add(c);
            }
            return cityList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    //capital cities by region ordered by population
    public ArrayList<Capital> capitalsByRegion(String s_region) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name, country.Name, city.Population FROM world.country left join world.city on country.Capital = city.ID WHERE country.Region = " + "'" + s_region + "'" +" order by city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Capital> cityList = new ArrayList<Capital>();
            while (rset.next()) {
                Capital c = new Capital(rset.getString("city.Name"), rset.getString("country.Name"),rset.getInt("city.Population"));
                cityList.add(c);
            }
            return cityList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital Cities");
            return null;
        }
    }

    //capital cities by continent ordered by population
    public ArrayList<Capital> capitalsByContinent(String s_continent) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name, country.Name, city.Population FROM world.country left join world.city on country.Capital = city.ID WHERE country.Continent = " + "'" + s_continent + "'" +" order by city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Capital> cityList = new ArrayList<Capital>();
            while (rset.next()) {
                Capital c = new Capital(rset.getString("city.Name"), rset.getString("country.Name"),rset.getInt("city.Population"));
                cityList.add(c);
            }
            return cityList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital Cities");
            return null;
        }
    }

    //top N capital cities by continent ordered by population
    public ArrayList<Capital> top_N_capitalsByContinent(String s_continent, int limit) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name, country.Name, city.Population FROM world.country left join world.city on country.Capital = city.ID WHERE country.Continent = " + "'" + s_continent + "'" +" order by city.Population DESC limit " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Capital> cityList = new ArrayList<Capital>();
            while (rset.next()) {
                Capital c = new Capital(rset.getString("city.Name"), rset.getString("country.Name"),rset.getInt("city.Population"));
                cityList.add(c);
            }
            return cityList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital Cities");
            return null;
        }
    }

    //top n capital cities by region ordered by population
    public ArrayList<Capital> top_N_capitalsByRegion(String s_region, int limit) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name, country.Name, city.Population FROM world.country left join world.city on country.Capital = city.ID WHERE country.Region = " + "'" + s_region + "'" +" order by city.Population DESC limit " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Capital> cityList = new ArrayList<Capital>();
            while (rset.next()) {
                Capital c = new Capital(rset.getString("city.Name"), rset.getString("country.Name"),rset.getInt("city.Population"));
                cityList.add(c);
            }
            return cityList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital Cities");
            return null;
        }
    }

    //top n capital cities ordered by population
    public ArrayList<Capital> top_N_capitalsByPop(int limit) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name, country.Name, city.Population FROM world.country left join world.city on country.Capital = city.ID order by city.Population DESC limit " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Capital> cityList = new ArrayList<Capital>();
            while (rset.next()) {
                Capital c = new Capital(rset.getString("city.Name"), rset.getString("country.Name"),rset.getInt("city.Population"));
                cityList.add(c);
            }
            return cityList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital Cities");
            return null;
        }
    }


    //population report for all countries in world
    public ArrayList<Population> populaionReportOfCountries() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "select country.Name, SUM(country.Population) as 'Country Population', sum(city.Population) as 'City Population', concat(round((sum(city.Population)/sum(country.Population) * 100),2),' %') as 'Percent in" +
                    " Cities',(sum(country.Population) - sum(city.Population)) as 'Population not in cities', concat(round((((sum(country.Population) - sum(city.Population))/sum(country.Population)) * 100),2),' %') as 'Percent not i" +
                    "n cities' from world.country left join world.city on country.Code = city.CountryCode group by country.Name order by country.Name asc;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Population> popList = new ArrayList<Population>();
            while (rset.next()) {
                Population p = new Population(rset.getString("country.Name"),rset.getLong("Country Population"),rset.getLong("City Population"), rset.getString("Percent in Cities"), rset.getLong("Population not in cities"), rset.getString("Percent not in cities"));
                popList.add(p);
            }
            return popList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population info");
            return null;
        }
    }


    //population report on all countries in region
    public ArrayList<Population> populaionReportOfCountriesByRegion(String s_region) {
            try {
        // Create an SQL statement
        Statement stmt = con.createStatement();
        // Create string for SQL statement
        String strSelect = "select country.Name, SUM(country.Population) as 'Country Population', sum(city.Population) as 'City Population', concat(round((sum(city.Population)/sum(country.Population) * 100),2),' %') as 'Percent in" +
                " Cities',(sum(country.Population) - sum(city.Population)) as 'Population not in cities', concat(round((((sum(country.Population) - sum(city.Population))/sum(country.Population)) * 100),2),' %') as 'Percent not i" +
                "n cities' from world.country left join world.city on country.Code = city.CountryCode where country.Region = " + "'" + s_region + "'"  + "group by country.Name order by country.Name asc;";
        // Execute SQL statement
        ResultSet rset = stmt.executeQuery(strSelect);
        // Return new employee if valid.
        // Check one is returned
        ArrayList<Population> popList = new ArrayList<Population>();
        while (rset.next()) {
            Population p = new Population(rset.getString("country.Name"),rset.getLong("Country Population"),rset.getLong("City Population"), rset.getString("Percent in Cities"), rset.getLong("Population not in cities"), rset.getString("Percent not in cities"));
            popList.add(p);
        }
        return popList;
    } catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println("Failed to get population info");
        return null;
    }
}


    //populaion report on all countries in continent
    public ArrayList<Population> populaionReportOfCountriesByContinent(String s_continent) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "select country.Name, SUM(country.Population) as 'Country Population', sum(city.Population) as 'City Population', concat(round((sum(city.Population)/sum(country.Population) * 100),2),' %') as 'Percent in" +
                    " Cities',(sum(country.Population) - sum(city.Population)) as 'Population not in cities', concat(round((((sum(country.Population) - sum(city.Population))/sum(country.Population)) * 100),2),' %') as 'Percent not i" +
                    "n cities' from world.country left join world.city on country.Code = city.CountryCode where country.Continent = " + "'" + s_continent + "'"  + "group by country.Name order by country.Name asc;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Population> popList = new ArrayList<Population>();
            while (rset.next()) {
                Population p = new Population(rset.getString("country.Name"),rset.getLong("Country Population"),rset.getLong("City Population"), rset.getString("Percent in Cities"), rset.getLong("Population not in cities"), rset.getString("Percent not in cities"));
                popList.add(p);
            }
            return popList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population info");
            return null;
        }
    }



    public static void main(String[] args) {
        // Create new Application
        TestApp a = new TestApp();
        Country country = new Country();
        City city = new City();
        Capital capital = new Capital();
        Population population = new Population();

        // Connect to database
        a.connect("localhost:33060");
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
        /*
        ArrayList<City> cityList = a.citiesByPop();
        city.generateReport(cityList);

        ArrayList<City> cityListContinent = a.citiesByContinent("Europe");
        city.generateReport(cityListContinent);

        ArrayList<City> cityListDistrict = a.citiesByDistrict("England");
        city.generateReport(cityListDistrict);

        ArrayList<City> cityListRegion = a.citiesByRegion("Caribbean");
        city.generateReport(cityListRegion);

        ArrayList<City> cityListCountry = a.citiesByCountry("France");
        city.generateReport(cityListCountry);*/

        /*
        ArrayList<City> topCityListDistrict = a.top_N_citiesByDistrict("England", 5);
        city.generateReport(topCityListDistrict);

        ArrayList<City> topCityListCountry = a.top_N_citiesByCountry("France", 4);
        city.generateReport(topCityListCountry);

        ArrayList<City> topCityListRegion = a.top_N_citiesByRegion("Caribbean", 15);
        city.generateReport(topCityListRegion);

        ArrayList<City> topCityListContinent = a.top_N_citiesByContinent("Europe", 9);
        city.generateReport(topCityListContinent);

        ArrayList<City> topCityList = a.top_N_citiesByPop(6);
        city.generateReport(topCityList);*/

        /*ArrayList<Capital> capitalList = a.capitalsByPop();
        capital.generateReport(capitalList);

        ArrayList<Capital> capitalListByRegion = a.capitalsByRegion("Polynesia");
        capital.generateReport(capitalListByRegion);

        ArrayList<Capital> capitalListByContinent = a.capitalsByContinent("South America");
        capital.generateReport(capitalListByContinent);*/


        /*
        ArrayList<Capital> topNcapitalList = a.top_N_capitalsByPop(5);
        capital.generateReport(topNcapitalList);

        ArrayList<Capital> topNcapitalListByContinent = a.top_N_capitalsByContinent("South America", 5);
        capital.generateReport(topNcapitalListByContinent);

        ArrayList<Capital> topNcapitalListByRegion = a.top_N_capitalsByRegion("Western Europe", 5);
        capital.generateReport(topNcapitalListByRegion);*/

        ArrayList<Population> worldPop = a.populaionReportOfCountries();
        population.generateReport(worldPop);

        ArrayList<Population> worldPopRegion = a.populaionReportOfCountriesByRegion("Western Europe");
        population.generateReport(worldPopRegion);

        ArrayList<Population> worldPopContinent = a.populaionReportOfCountriesByContinent("Asia");
        population.generateReport(worldPopContinent);



        Country individualCountry = a.getCountry("Syria");
        //a.displayCountry(c);
        a.displayCountry(individualCountry);
        // Disconnect from database
        a.disconnect();
    }
}