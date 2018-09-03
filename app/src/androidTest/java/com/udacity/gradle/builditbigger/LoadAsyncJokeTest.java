package com.udacity.gradle.builditbigger;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class LoadAsyncJokeTest {
    private static final String EMPTY_STRING = "";
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validateRecipeDetail() {
        Espresso.onView(ViewMatchers.withId(R.id.buttonJokeFromApi)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.joke_text_view)).check(matches(not(withText(EMPTY_STRING))));
    }
}