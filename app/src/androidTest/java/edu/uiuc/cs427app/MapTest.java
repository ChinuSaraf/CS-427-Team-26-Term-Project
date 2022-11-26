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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MapTest extends TestCase {
    @Test
    public void testLocationsForNYC() throws InterruptedException {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), MapActivity.class);
        intent.putExtra("cityName", "NYC");
        intent.putExtra("username", "user1");
        try (final ActivityScenario<MapActivity> scenario = ActivityScenario.launch(intent)) {
            Thread.sleep(3000);
            onView(ViewMatchers.withId(R.id.city_name)).check(ViewAssertions.matches(ViewMatchers.withText("City Name: New York, NY, USA")));
            onView(ViewMatchers.withId(R.id.latitude)).check(ViewAssertions.matches(ViewMatchers.withText("Latitude: 40.7127753")));
            onView(ViewMatchers.withId(R.id.longitude)).check(ViewAssertions.matches(ViewMatchers.withText("Longitude: -74.0059728")));
            Thread.sleep(3000);
        }
    }

    @Test
    public void testLocationsForChicago() throws InterruptedException {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), MapActivity.class);
        intent.putExtra("cityName", "Chicago");
        intent.putExtra("username", "user1");
        try (final ActivityScenario<MapActivity> scenario = ActivityScenario.launch(intent)) {
            Thread.sleep(3000);
            onView(ViewMatchers.withId(R.id.city_name)).check(ViewAssertions.matches(ViewMatchers.withText("City Name: Chicago, IL, USA")));
            onView(ViewMatchers.withId(R.id.latitude)).check(ViewAssertions.matches(ViewMatchers.withText("Latitude: 41.8781136")));
            onView(ViewMatchers.withId(R.id.longitude)).check(ViewAssertions.matches(ViewMatchers.withText("Longitude: -87.6297982")));
            Thread.sleep(3000);
        }
    }
}