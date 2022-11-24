package edu.uiuc.cs427app;

import static androidx.test.espresso.Espresso.onView;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MapTest {
    @Rule
    public ActivityScenarioRule<MapActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MapActivity.class);
}