package com.acme.a3csci3130;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.is;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static junit.framework.Assert.assertEquals;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;

import  com.acme.a3csci3130.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest{
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule=new ActivityTestRule<>(MainActivity.class);
    @Test
    public void testDelete() throws InterruptedException{
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(2).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.name)).perform(typeText(""),closeSoftKeyboard());
        Thread.sleep(1000);
        onView(withId(R.id.deleteButton)).perform(click());
        onView(withText("Operation successful !")).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }
    @Test
    public void testRead(){
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withText("Fish Shop")).check(matches(isDisplayed()));
    }
    @Test
    public void testCreate(){
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).perform(typeText("test"),closeSoftKeyboard());
        onView(withId(R.id.num)).perform(typeText("123456789"),closeSoftKeyboard());
        onView(withId(R.id.address)).perform(typeText("2333 Drive"),closeSoftKeyboard());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withText("Item successfully added!")).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }
    @Test
    public void testUpdate(){
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(2).perform(click());
        onView(withId(R.id.name)).perform(clearText());
        onView(withId(R.id.name)).perform(typeText("test1"),closeSoftKeyboard());
        onView(withId(R.id.updateButton)).perform(click());
        onView(withText("Item successfully updated !")).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

}
