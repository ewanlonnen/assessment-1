package org.example;

import group21.assessment.CountryChecker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AppTest
{
    static CountryChecker app;

    @BeforeAll
    static void init()
    {
        app = new CountryChecker(); //creating a test to ensure that the app can create an instance
        //app.connect();
    }

    @Test
    void getCountryTestNull()
    {
        app.getCountry(null); //creating a test to ensure that the program will report that there is no country
    }

    @Test
    void getCountry()
    {
        String testCountry = "Syria"; //creating a test to ensure that the program will report the correct country
        app.displayCountry(app.getCountry(testCountry));
    }

    //@AfterAll
    //static void end(){
    //    app.disconnect();
    //}
}