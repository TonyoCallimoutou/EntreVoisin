package com.openclassrooms.entrevoisins.utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Matcher;

public class DetailViewActionOnAvatar implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Get detail about neighbour";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.item_list_avatar);
        // Maybe check for null
        button.performClick();
    }
}