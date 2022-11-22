package edu.uiuc.cs427app;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

/**
 * https://github.com/android/testing-samples
 */

/**
 * Team 1 Tests:
 * User Sign Up- LoginActivity (Username, Password, Sign Up Button)
 * User Login- LoginActivity
 * User Logout- LoginActivity
 * Weather of City (x2)- WeatherActivity
 */

@RunWith(AndroidJUnit4.class)

public class M5T1Test {

    // Launches a given activity (MainActivity in example) before test starts + closes after test
    // MainActivity + LoginActivity + MapActivity + WeatherActivity + DetailsActivity (?)
    @Rule
    public ActivityScenarioRule<LoginActivity> activityScenarioRule
            = new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void userSignUpTest() {

        // Input: Username, Password --> Sign Up Button (Click)

        // Testing whether or not username & password has been saved/ added to the database?
        // Username & password has been saved locally (@LoginActivity.java).

        // Test to see if username & password EXISTS on local.
        // Assert if R.id.password/ username exists

        // Checking assertions w/ Toast output? (Espresso <-> Toast) --> https://www.browserstack.com/guide/test-toast-message-using-espresso
        // Checking assertions w/ physical Text outputs in UI (Modify LoginActivity.java)
        // Create "MASTER" username & password to use as test variables (Modify LoginActivity.java)
    }

    @Test
    public void userLoginTest() {

        // Input: Username, Password --> Login Button (Click)

        // For the check, you should identify an "expected" output or behavior
        // and then compare the resulting behavior with that.

        // As you mentioned, one possibility is to check if after login,
        // the user lands in the correct page.

        // Test to see if login failed/ login succeeded.
        // Validate if app is on dashboard page.
        // Check/ Match --> "Team #26 - " + userName
    }

    @Test
    public void userLogoutTest() {

        // Input: Logout Button (Click)
        // Test to see if user has logged out.

        // Press the logout button. --> button = onClickLogout (@ ln 24)
        onView(withId(R.id.button)).perform(click());

        // Validate if app is currently on authentication page.
        // Check to see if "Team #26 Weather App" | "CS427 Project App" is displayed.
        // @ activity_login.xml --> android:text="CS427 Project App"
        // welcomeText --> textView1 (UserName on Authentication Page)
        onView(withId(R.id.textView1)).check(matches(withText("UserName")));
    }
}
