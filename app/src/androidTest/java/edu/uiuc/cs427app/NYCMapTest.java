package edu.uiuc.cs427app;

import static androidx.test.espresso.Espresso.onView;

import android.content.Intent;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class NYCMapTest {
    @Rule
    public ActivityScenarioRule<MapActivity> activityScenarioRule
            = new ActivityScenarioRule<>(
            new Intent(
                    InstrumentationRegistry.getInstrumentation().getTargetContext(),
                    MapActivity.class)
                    .putExtra("cityName", "NYC")
                    .putExtra("username", "user1"));

    @Test
    public void testLocationsForNYC() throws InterruptedException {
        Thread.sleep(2000);
        onView(ViewMatchers.withId(R.id.city_name)).check(ViewAssertions.matches(ViewMatchers.withText("City Name: New York, NY, USA")));
        onView(ViewMatchers.withId(R.id.latitude)).check(ViewAssertions.matches(ViewMatchers.withText("Latitude: 40.7127753")));
        onView(ViewMatchers.withId(R.id.longitude)).check(ViewAssertions.matches(ViewMatchers.withText("Longitude: -74.0059728")));
    }
}