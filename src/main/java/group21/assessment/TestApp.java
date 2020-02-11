package group21.assessment;
import java.sql.*;

public class TestApp
{
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
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

        public Country getCountry()
        {
            try
            {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "SELECT Name, Continent, Population "
                                + "FROM country ";
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Return new employee if valid.
                // Check one is returned
                if (rset.next())                {
                    Country c = new Country();
                    c.name = rset.getString("Name");
                    c.Continent = rset.getString("Continent");
                    c.Population = rset.getInt("Population");
                    return c;
                }
                else
                    return null;
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("Failed to get employee details");
                return null;
            }
        }
    public void displayCountry(Country c)
    {
        if (c != null)
        {
            System.out.println(
                    c.name + " "
                            + c.Continent + " "
                            + c.Population + "\n");
        }
    }
    public static void main(String[] args)
    {
        // Create new Application
        TestApp a = new TestApp();


        // Connect to database
        a.connect();
        Country c = a.getCountry();
        a.displayCountry(c);
        // Disconnect from database
        a.disconnect();
    }
}