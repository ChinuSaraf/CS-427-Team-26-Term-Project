package edu.uiuc.cs427app;

import static androidx.test.espresso.Espresso.onView;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import junit.framework.TestCase;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //to run the tests in ascending order
public class WeatherTest extends TestCase {
    @Test
    // Test for city 1: Vancouver
    // Checks to see if Vancouver on the input list = the city we get from the API query.
    public void testWeatherForVancouver() throws InterruptedException {
        //using espresso Intent to validate and stubbing of intents sent out by the WeatherActivity under ApplicationProvided
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), WeatherActivity.class);
        intent.putExtra("cityName", "Vancouver");
        intent.putExtra("username", "user1");
        try (final ActivityScenario<WeatherActivity> scenario = ActivityScenario.launch(intent)) {
            Thread.sleep(3000); //wait time of 3 sec
            onView(ViewMatchers.withId(R.id.tv_get_cityName)).check(ViewAssertions.matches(ViewMatchers.withText("Vancouver")));
            Thread.sleep(3000); //wait time of 3 sec
        }
    }

    @Test
    // Test for city 2: Chicago
    // Checks to see if Chicago on the input list = the city we get from the API query.
    public void testWeatherForChicago() throws InterruptedException {
        //using espresso Intent to validate and stubbing of intents sent out by the WeatherActivity under ApplicationProvided
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), WeatherActivity.class);
        intent.putExtra("cityName", "Chicago");
        intent.putExtra("username", "user1");
        try (final ActivityScenario<WeatherActivity> scenario = ActivityScenario.launch(intent)) {
            Thread.sleep(3000); //wait time of 3 sec
            onView(ViewMatchers.withId(R.id.tv_get_cityName)).check(ViewAssertions.matches(ViewMatchers.withText("Chicago")));
            Thread.sleep(3000); //wait time of 3 sec
        }
    }

}
