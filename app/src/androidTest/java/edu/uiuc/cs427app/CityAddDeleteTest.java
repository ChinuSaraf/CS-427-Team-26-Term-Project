package edu.uiuc.cs427app;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;

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
public class CityAddDeleteTest extends TestCase {
    @Test
    //test to check the Logout function
    public void userLogoutTest() {
        //using espresso Intent to validate and stubbing of intents sent out by the MainActivity under ApplicationProvided
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), MainActivity.class);
        intent.putExtra("username", "admin");
        intent.putExtra("theme", "1");
        //using try & catch to customize the error handling
        //also helps to debug
        try (final ActivityScenario<MainActivity> scenario = ActivityScenario.launch(intent)) {
            Thread.sleep(3000); //wait time of 3 sec
            onView(withId(R.id.logoutButton)).perform(ViewActions.click());
            onView(withId(R.id.textView1)).check(ViewAssertions.matches(ViewMatchers.withText("UserName")));
            Thread.sleep(3000); //wait time of 3 sec
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    //test to check the add city function
    public void checkIsCityAdded() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), MainActivity.class);
        intent.putExtra("username", "admin");
        intent.putExtra("theme", "1");
        try (final ActivityScenario<MainActivity> scenario = ActivityScenario.launch(intent)) {
            onView(withId(R.id.textName)).perform(typeText("chicago"));
            Espresso.closeSoftKeyboard();
            Thread.sleep(3000); //wait time of 3 sec
            onView(withId(R.id.insertButton)).perform(click());
            Thread.sleep(3000); //wait time of 3 sec
            onView(withText("chicago")).check(matches(isDisplayed()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    //test to check the delete city function
    public void checkIsCityDeleted() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), MainActivity.class);
        intent.putExtra("username", "admin");
        intent.putExtra("theme", "1");
        try (final ActivityScenario<MainActivity> scenario = ActivityScenario.launch(intent)) {
            Thread.sleep(3000); //wait time of 3 sec
            onView(withId(R.id.deleteButton)).perform(click());
            onView(withText("chicago")).check(doesNotExist());
            Thread.sleep(3000); //wait time of 3 sec
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}