package com.openclassrooms.entrevoisins.utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Matcher;

public class SetFavoriteViewAction implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Set boolean favorite about a neighbour";
    }

    @Override
    public void perform(UiController uiController, View view) {

        view.findViewById(R.id.favoriteButton_detail).performClick();

    }
}
