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
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by Mikhail on 7/28/17.
 */

@RunWith(AndroidJUnit4.class)
public class GuessActivityTest {

    @Rule
    public ActivityTestRule<GuessActivity> activityRule =
            new ActivityTestRule<>(GuessActivity.class);

    @Test
    public void testRewardsText() {
        onView(withId(R.id.textView)).check(matches(withText("Rewards: 0")));
    }

    @Test
    public void testWrongAnswer() {
        onView(withId(R.id.button1)).perform(click());
        onView(withText("Your answer is wrong!")).check(matches(isDisplayed()));
    }

    @Test
    public void testButtons() {
        onView(withId(R.id.button1)).check(matches(isClickable()));
        onView(withId(R.id.button2)).check(matches(isClickable()));
        onView(withId(R.id.button3)).check(matches(isClickable()));
        onView(withId(R.id.button4)).check(matches(isClickable()));
        onView(withId(R.id.button5)).check(matches(not(isClickable())));
    }
}
