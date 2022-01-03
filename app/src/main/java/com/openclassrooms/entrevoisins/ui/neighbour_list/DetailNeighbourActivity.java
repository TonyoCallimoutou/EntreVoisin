package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailNeighbourActivity extends AppCompatActivity {

    @BindView(R.id.avatar_detail)
    ImageView avatar;
    @BindView(R.id.favoriteButton_detail)
    FloatingActionButton buttonFavorite;
    @BindView(R.id.name_avatar_detail)
    TextView nameAvatar;
    @BindView(R.id.name_detail)
    TextView name;
    @BindView(R.id.place_detail)
    TextView place;
    @BindView(R.id.tel_detail)
    TextView tel;
    @BindView(R.id.web_site_detail)
    TextView webSite;
    @BindView(R.id.aboutMeText_detail)
    TextView aboutMe;

    private NeighbourApiService mApiService;
    private Neighbour neighbour;
    final String EXTRA_NEIGHBOUR = "click_neighbour";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiService = DI.getNeighbourApiService();

        Neighbour neighbourCopie = (Neighbour) getIntent().getSerializableExtra(EXTRA_NEIGHBOUR);
        int i = mApiService.getNeighbours().indexOf(neighbourCopie);
        neighbour = mApiService.getNeighbours().get(i);

        buttonFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiService.setFavoriteNeighbour(neighbour);
                printFavoriteButton(neighbour.getFavorite());
            }
        });
        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {

        String neighbourWebSite = neighbour.getWebSite() == null ? "Aucune donnée" : neighbour.getWebSite();

        String mNeighbourImage = neighbour.getAvatarUrl();
        Glide.with(this).load(mNeighbourImage).into(avatar);

        name.setText(neighbour.getName());
        nameAvatar.setText(neighbour.getName());
        place.setText(neighbour.getAddress());
        tel.setText(neighbour.getPhoneNumber());
        aboutMe.setText(neighbour.getAboutMe());
        webSite.setText(neighbourWebSite);

        printFavoriteButton(neighbour.getFavorite());

    }

    public void printFavoriteButton(boolean favorite) {
        if (favorite) {
            buttonFavorite.setImageResource(R.drawable.ic_star_white_24dp);
        }
        else {
            buttonFavorite.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
    }

}