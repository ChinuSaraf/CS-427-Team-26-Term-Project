package edu.uiuc.cs427app;

import static androidx.test.espresso.Espresso.onView;

import android.content.Intent;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ChicagoMapTest {
    @Rule
    public ActivityScenarioRule<MapActivity> activityScenarioRule
            = new ActivityScenarioRule<>(
            new Intent(
                    InstrumentationRegistry.getInstrumentation().getTargetContext(),
                    MapActivity.class)
                    .putExtra("cityName", "Chicago")
                    .putExtra("username", "user1"));

    @Test
    public void testLocationsForChicago() throws InterruptedException {
        Thread.sleep(2000);
        onView(ViewMatchers.withId(R.id.city_name)).check(ViewAssertions.matches(ViewMatchers.withText("City Name: Chicago, IL, USA")));
        onView(ViewMatchers.withId(R.id.latitude)).check(ViewAssertions.matches(ViewMatchers.withText("Latitude: 41.8781136")));
        onView(ViewMatchers.withId(R.id.longitude)).check(ViewAssertions.matches(ViewMatchers.withText("Longitude: -87.6297982")));
    }
}