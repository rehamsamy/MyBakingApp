package com.example.van.mybakingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
public class RecipeDetailTest {
    String res="-Graham Cracker crumbs(2:CUP)";
    @Rule
    public ActivityTestRule<RecipeDetail> intentsTestRule=
            new ActivityTestRule<>(RecipeDetail.class);

    @Test
    public void test() {
      //  onData(anything()).inAdapterView(withId(R.id.list)).atPosition(0).perform(click());

        // Checks that the OrderActivity opens with the correct tea name displayed

        onData(anything()).inAdapterView(withId(R.id.list)).atPosition(1);
        onView(withId(R.id.quantity)).check(matches(withText(res)));




    }

    }
