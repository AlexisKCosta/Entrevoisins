package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.SystemClock;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;

/**
 * Created by Alexis Costa on 10/01/2020.
 */
@RunWith(JUnit4.class)
public class ListNeighbourPagerAdapterTest {

    private final int FAVORITE_ITEM_COUNT = 0;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule<>(ListNeighbourActivity.class);


    @Test
    public void isThere_OnlyFavorite_InFavoriteList() {
        //Add a neighbour in favorite
        onView(CoreMatchers.allOf(withId(R.id.list_neighbours),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.floatingFavoriteButton))
                .perform(click());

        //Check that the favorite item list = FAVORITE_ITEM_COUNT + 1
        onView(withId(R.id.profile_activity))
                .perform(pressBack());
        onView(withId(R.id.main_content))
                .perform(swipeLeft());
        SystemClock.sleep(1000);
        onView(CoreMatchers.allOf(withId(R.id.list_neighbours),isDisplayed()))
                .check(withItemCount(FAVORITE_ITEM_COUNT+1));


    }
}