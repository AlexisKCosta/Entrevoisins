package com.openclassrooms.entrevoisins.ui.neighbour_profile;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


public class NeighbourProfileActivity extends AppCompatActivity {
    private TextView nameTxt;
    private FloatingActionButton favoriteButton;
    private ImageView avatar;
    private TextView nameTxt2;
    private TextView locationTxt;
    private TextView phoneTxt;
    private TextView webTxt;
    private TextView aboutMeTxt;
    private NeighbourApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_profile);
        mApiService = DI.getNeighbourApiService();



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /** UI components */
        nameTxt = findViewById(R.id.textViewName);
        favoriteButton = findViewById(R.id.floatingFavoriteButton);
        avatar = findViewById(R.id.imageViewAvatar);
        nameTxt2 = findViewById(R.id.textViewName2);
        locationTxt = findViewById(R.id.textViewLocation);
        phoneTxt = findViewById(R.id.textViewPhone);
        webTxt = findViewById(R.id.textViewWeb);
        aboutMeTxt = findViewById(R.id.textViewAboutMe);

        Neighbour neighbour = (Neighbour) getIntent().getSerializableExtra("neighbour");
        nameTxt.setText(neighbour.getName());
        nameTxt2.setText(neighbour.getName());
        locationTxt.setText(neighbour.getLocation());
        phoneTxt.setText(neighbour.getPhone());
        webTxt.setText(neighbour.getWebsite());
        aboutMeTxt.setText(neighbour.getAboutMe());
        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .into(avatar);

        if (neighbour.isFavorite()) {
            favoriteButton.setImageResource(R.drawable.ic_star_gold_24dp);
        }
        else favoriteButton.setImageResource(R.drawable.ic_star_border_gold_24dp);


        favoriteButton.setOnClickListener(v -> {
            if (!neighbour.isFavorite()){
                favoriteButton.setImageResource(R.drawable.ic_star_gold_24dp);
                neighbour.setFavorite(true);
            }
            else {
                favoriteButton.setImageResource(R.drawable.ic_star_border_gold_24dp);
                neighbour.setFavorite(false);
            }
            mApiService.updateNeighbour(neighbour);
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return super.onOptionsItemSelected(item);

    }
}
