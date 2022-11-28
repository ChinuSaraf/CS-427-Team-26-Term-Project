package edu.uiuc.cs427app;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //to run the tests in ascending order

public class UserSignUpLoginTest extends TestCase{
    @Test
    // test to check the login function

    public void userLoginTest() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), LoginActivity.class);
        intent.putExtra("username", "admin");
        intent.putExtra("password", "123456");
        intent.putExtra("theme", "1");

        try (final ActivityScenario<LoginActivity> scenario = ActivityScenario.launch(intent)) {
            // this test is only for username and password matched with stored info in app
            Thread.sleep(2000); //wait time of 3 sec
            onView(withId(R.id.username)).perform(typeText("abc"));
//                    .perform(scrollTo())
//                    .perform(click());
            Thread.sleep(2000); //wait time of 3 sec
            Espresso.closeSoftKeyboard();
            Thread.sleep(2000); //wait time of 3 sec
            onView(withId(R.id.password)).perform(typeText("123"), closeSoftKeyboard());
//            Espresso.closeSoftKeyboard();
//            onView(withId(R.id.textView1)).perform(typeText("admin"), ViewActions.closeSoftKeyboard());
//            onView(withId(R.id.textView2)).perform(typeText("123456"), ViewActions.closeSoftKeyboard());
            Thread.sleep(2000); //wait time of 3 sec
            onView(withId(R.id.buttonSignUp)).perform(click());
            Thread.sleep(2000); //wait time of 3 sec
            onView(withId(R.id.buttonSignUp)).perform(ViewActions.click());
            // the text works well or not -> not sure.
//            onView(withId(R.id.logoutButton)).perform(ViewActions.click());
            Thread.sleep(5000); //wait time of 3 sec
            onView(withId(R.id.logoutButton)).check(ViewAssertions.matches(ViewMatchers.withText("@string/logoutButton")));
            Thread.sleep(2000); //wait time of 3 sec
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    // test to check the signup function

    public void userSignUpTest() {


    }

}
