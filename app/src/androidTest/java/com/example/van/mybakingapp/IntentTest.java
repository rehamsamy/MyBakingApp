package com.example.van.mybakingapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.util.EnumSet.allOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class IntentTest {
    String res="-Graham Cracker crumbs(2:CUP)";

    @Rule
    IntentsTestRule<MainActivity> testRule =
            new IntentsTestRule<>(MainActivity.class);

    @Before
   public void beforeTest() {
        Instrumentation.ActivityResult result = createImageCaptureActivityResultStub();

        intending(not(isInternal())).respondWith(result);

    }

    @Test
   public void test() {
        onData(anything()).inAdapterView(withId(R.id.list)).atPosition(0).perform(click());
        onView(withId(R.id.quantity)).check(matches(withText(res)));

        // intended(allOf(hasExtra(Intent.)));
    }

    private Instrumentation.ActivityResult createImageCaptureActivityResultStub() {

        Bundle bundle = new Bundle();
        bundle.putParcelable("array", (Parcelable) MainActivity.ingredients);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        return new Instrumentation.ActivityResult(Activity.RESULT_OK, intent);
    }
}
