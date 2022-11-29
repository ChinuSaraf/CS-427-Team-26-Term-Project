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
    // test to check the sign up function

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
            onView(withId(R.id.logoutButton)).perform(click());
            Thread.sleep(1000); //wait time of 1 sec
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    // test to check the login function

    public void userLoginTest1() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), LoginActivity.class);

        try (final ActivityScenario<LoginActivity> scenario = ActivityScenario.launch(intent)) {
            // this test is only for username and password matched with stored info in app
            onView(withId(R.id.username)).perform(typeText("abc"));
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.password)).perform(typeText("123"));
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
    // test to check the login function

    public void userLoginTest2() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), LoginActivity.class);

        try (final ActivityScenario<LoginActivity> scenario = ActivityScenario.launch(intent)) {
            // this test is only for username and password matched with stored info in app
            onView(withId(R.id.username)).perform(typeText("abc"));
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.password)).perform(typeText("12"));
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.buttonLogin)).perform(click());
            Thread.sleep(1000); //wait time of 1 sec

            // TODO: how to check we are still in the login page?
            // one possible way is to check the login button below
            onView(withId(R.id.buttonLogin)).perform(click());
            Thread.sleep(1000); //wait time of 1 sec
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}