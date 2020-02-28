package org.example;

import group21.assessment.TestApp;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static TestApp app;

    @BeforeAll
    static void init()
    {
        app = new TestApp(); //creating a test to ensure that the app can create an instance
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