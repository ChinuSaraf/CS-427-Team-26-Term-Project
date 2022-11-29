package edu.uiuc.cs427app;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;
import androidx.test.runner.AndroidJUnitRunner;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
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
public class UserSignUpLoginTest extends TestCase{

    @Test
    // test to check the sign up function w/ username and password, this will create an account with these credentials

    public void userSignUpTest() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), LoginActivity.class);

        try (final ActivityScenario<LoginActivity> scenario = ActivityScenario.launch(intent)) {
            // this test is only for username and password matched with stored info in app
            onView(withId(R.id.username)).perform(typeText("abc"));
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.password)).perform(typeText("123"));
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.buttonSignUp)).perform(click());
            Thread.sleep(1000); //wait time of 1 sec

            // checks to see that we have entered the customised details page of the user + successfully accessed the account
            onView(withId(R.id.logoutButton)).perform(click());
            Thread.sleep(1000); //wait time of 1 sec
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    // test to check the login function w/ the CORRECT username + password
    // this proves that SharedPreferences (our "database") has stored username "abc" and password "123"

    public void userLoginTest() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), LoginActivity.class);

        try (final ActivityScenario<LoginActivity> scenario = ActivityScenario.launch(intent)) {
            // this test is only for username and password matched with stored info in app
            onView(withId(R.id.username)).perform(typeText("abc")); // correct username
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.password)).perform(typeText("123")); // correct password
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.buttonLogin)).perform(click());
            Thread.sleep(1000); //wait time of 1 sec
            onView(withId(R.id.logoutButton)).perform(click());
            Thread.sleep(1000); //wait time of 1 sec
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    // test to check the login function w/ the WRONG password
    // this proves that SharedPreferences (our "database") has stored username "abc" and password "123"

    public void userLoginWrongPassword() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), LoginActivity.class);

        try (final ActivityScenario<LoginActivity> scenario = ActivityScenario.launch(intent)) {
            // this test is only for username and password matched with stored info in app
            onView(withId(R.id.username)).perform(typeText("abc")); // correct username
            Espresso.closeSoftKeyboard();
            // Using the WRONG password
            onView(withId(R.id.password)).perform(typeText("12")); // WRONG password
            Espresso.closeSoftKeyboard();

            // simulating login activity again to prove that the login function does NOT work with the wrong password
            // proves that information stored @ SharedPreferences has been compared with the inputted test information
            onView(withId(R.id.buttonLogin)).perform(click());
            Thread.sleep(1000); //wait time of 1 sec
            onView(withId(R.id.buttonLogin)).perform(click());
            Thread.sleep(1000); //wait time of 1 sec
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    // test to check the login function w/ the WRONG username
    // this proves that SharedPreferences (our "database") has stored username "abc" and password "123"

    public void userLoginWrongUsername() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), LoginActivity.class);

        try (final ActivityScenario<LoginActivity> scenario = ActivityScenario.launch(intent)) {
            // this test is only for username and password matched with stored info in app
            onView(withId(R.id.username)).perform(typeText("a")); // WRONG username
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.password)).perform(typeText("123")); // correct password
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.buttonLogin)).perform(click());
            Thread.sleep(1000); //wait time of 1 sec

            // confirms that the app does not go into the 2nd page, stays at authentication page
            onView(withId(R.id.textView1)).check(ViewAssertions.matches(ViewMatchers.withText("UserName")));
            Thread.sleep(1000); //wait time of 1 sec
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}