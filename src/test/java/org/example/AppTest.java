package org.example;

import group21.assessment.TestApp;
import group21.assessment.City;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static TestApp app;
    City c = new City();

    @BeforeAll
    static void init()
    {
        app = new TestApp();
        app.connect("localhost:33060");
    }

    @Test
    void getCountryTestNull()
    {
        app.getCountry(null);
    }

    @Test
    void getCountry()
    {
        String testCountry = "Syria";
        app.displayCountry(app.getCountry(testCountry));
    }

    @Test
    void top_N_citiesByDistrict()
    {
        ArrayList<City> list = app.top_N_citiesByDistrict("England", 5);
        c.generateReport(list);
    }

    @AfterAll
    static void end(){
        app.disconnect();
    }
}