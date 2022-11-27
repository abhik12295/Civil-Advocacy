package com.abhi.civiladvocacyapp;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.squareup.picasso.Picasso;

public class PhotoDetailActivity extends AppCompatActivity {
    private static final String TAG = "PhotoActivity";
    private Picasso picasso;
    ImageView imagePhotoPhoto;
    ImageView imgPartyPhoto;
    Officials officials;
    String location;
    String officeName;
    ConstraintLayout constraintLayout;
    TextView viewLocationPhoto;
    TextView viewOfficePhoto;
    TextView viewNamePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        getSupportActionBar().setTitle(R.string.civilAdvocacyAbout);
        picasso = Picasso.get();
        initializeControls();
        LoadData();
    }

    private void initializeControls() {
        constraintLayout = findViewById((R.id.constraintLayout));
        viewLocationPhoto = findViewById(R.id.viewLocationPhoto);
        viewNamePhoto = findViewById(R.id.viewNamePhoto);
        imagePhotoPhoto = findViewById(R.id.imagePhotoPhoto);
        viewOfficePhoto = findViewById(R.id.viewOfficePhoto);
        imgPartyPhoto = findViewById(R.id.imgPartyPhoto);
    }

    private void LoadData(){
        officials = (Officials) getIntent().getSerializableExtra("officialData");
        officeName = getIntent().getStringExtra("officeName").toString();
        location = getIntent().getStringExtra("location");

        viewLocationPhoto.setText(location);
        viewOfficePhoto.setText(officeName);
        viewNamePhoto.setText(officials.getOfficialsName());
        if(officials.getOfficialsParty().toUpperCase().contains("DEMOCRATIC")){
            constraintLayout.setBackgroundColor(Color.BLUE);
            imgPartyPhoto.setVisibility(View.VISIBLE);
            imgPartyPhoto.setImageResource(R.drawable.dem_logo);
        }
        else if(officials.getOfficialsParty().toUpperCase().contains("REPUBLICAN")){
            constraintLayout.setBackgroundColor(Color.RED);
            imgPartyPhoto.setVisibility(View.VISIBLE);
            imgPartyPhoto.setImageResource(R.drawable.rep_logo);
        }
        else{
            constraintLayout.setVisibility(View.INVISIBLE);
            imgPartyPhoto.setBackgroundColor(Color.BLACK);
            imgPartyPhoto.setVisibility(View.INVISIBLE);
        }
        if(officials.getOfficialsPhotoUrl()!=null && !officials.getOfficialsPhotoUrl().isEmpty()) {
            loadRemoteImage(officials.getOfficialsPhotoUrl());
        }
    }

    private void loadRemoteImage(String imageURL) {
        try {
            Log.d(TAG, "loadRemoteImage: ");
            picasso.load(imageURL)
                    .error(R.drawable.missing)
                    .placeholder(R.drawable.brokenimage)
                    .into(imagePhotoPhoto);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "loadRemoteImage: ", e);
        }
    }
}
