package com.example.van.mybakingapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
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
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class ViewTest {

    String res="-Graham Cracker crumbs(2:CUP)";
    public static final String x="Brownies";

    @Rule
    public ActivityTestRule<MainActivity> intentsTestRule=
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void test(){
     //  onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(1);
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(1);
        onView(withId(R.id.recipe_namee)).check(matches(withText(x)));

    }

}
