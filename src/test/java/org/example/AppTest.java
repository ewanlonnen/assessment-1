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
        app = new TestApp();
        //app.connect("localhost:33060");
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

    @AfterAll
    static void end(){
        app.disconnect();
    }
}