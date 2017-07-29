package michael.com.myapplication;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Mikhail on 7/28/17.
 */


@RunWith(AndroidJUnit4.class)
public class MainActivityTests {


    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testAddButton(){
        onView(withId(R.id.buttonAdd))
                .check(matches(isClickable()))
                .perform(click());
        onView(withId(R.id.button5)).check(matches(isDisplayed()));

    }

    @Test
    public void testMultiplyButton(){
        onView(withId(R.id.buttonMult))
                .check(matches(isClickable()))
                .perform(click());
        onView(withId(R.id.button5)).check(matches(isDisplayed()));

    }

}
