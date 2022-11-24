package edu.uiuc.cs427app;

import static androidx.test.espresso.Espresso.onView;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void userLogoutTest() {

        // Input: Logout Button (Click)
        // Test to see if user has logged out.

        // Press the logout button. --> button = onClickLogout (@ ln 24)
        onView(ViewMatchers.withId(R.id.logoutButton)).perform(ViewActions.click());

        // Validate if app is currently on authentication page.
        // Check to see if "Team #26 Weather App" | "CS427 Project App" is displayed.
        // @ activity_login.xml --> android:text="CS427 Project App"
        // welcomeText --> textView1 (UserName on Authentication Page)
        onView(ViewMatchers.withId(R.id.textView1)).check(ViewAssertions.matches(ViewMatchers.withText("UserName")));
    }
}