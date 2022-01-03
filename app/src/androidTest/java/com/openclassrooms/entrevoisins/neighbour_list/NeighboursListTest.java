
package com.openclassrooms.entrevoisins.neighbour_list;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.utils.DetailViewActionOnAvatar;
import com.openclassrooms.entrevoisins.utils.DetailViewActionOnName;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(Matchers.allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(Matchers.allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(Matchers.allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(Matchers.allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT-1));

        ITEMS_COUNT -=1;
    }

    /**
     * When we create an item
     */
    @Test
    public void myNeighboursList_addAction() {

        onView(Matchers.allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        onView(ViewMatchers.withId(R.id.add_neighbour)).perform(ViewActions.click());
        onView(ViewMatchers.withId(R.id.name)).perform(typeText("Bob"));
        pressBack();
        onView(ViewMatchers.withId(R.id.create)).perform(ViewActions.click());
        // Then : the number of element is 13
        onView(Matchers.allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT+1));

        ITEMS_COUNT +=1;
    }

    /**
     * Go to Detail Activity
     */
    @Test
    public void myNeighboursList_DetailActivity() {

        onView(Matchers.allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, new DetailViewActionOnAvatar()));

        // Test Nom utilisateur
        onView(ViewMatchers.withId(R.id.detail_neighbours))
                .check(matches(isDisplayed()));

        pressBack();

        onView(Matchers.allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DetailViewActionOnName()));

        // Test Nom utilisateur
        onView(ViewMatchers.withId(R.id.detail_neighbours))
                .check(matches(isDisplayed()));
    }

    /**
     * Name in Detail Activity
     */
    @Test
    public void myNeighboursList_DetailName() {

        onView(Matchers.allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, new DetailViewActionOnAvatar()));

        // Test Nom utilisateur
        onView(ViewMatchers.withId(R.id.name_detail))
                .check(matches(withText("Chloé")));

        pressBack();

        onView(Matchers.allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DetailViewActionOnName()));

        // Test Nom utilisateur
        onView(ViewMatchers.withId(R.id.name_detail))
                .check(matches(withText("Jack")));
    }

    /**
     * list of favorite neighbour
     */
    @Test
    public void myNeighboursList_Favorite() {
        onView(Matchers.allOf(ViewMatchers.withId(R.id.list_neighbours), Matchers.not(isDisplayed())))
                .check(withItemCount(0));
        onView(Matchers.allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DetailViewActionOnAvatar()));

        onView(ViewMatchers.withId(R.id.favoriteButton_detail)).perform(ViewActions.click());


        pressBack();

        onView(Matchers.allOf(ViewMatchers.withId(R.id.list_neighbours), Matchers.not(isDisplayed())))
                .check(withItemCount(1));
    }
}