package com.abhi.civiladvocacyapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AboutActivity extends AppCompatActivity {
    private String civicURL = "https://developers.google.com/civic-information";

    TextView viewCopyright;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setTitle(R.string.civilAdvocacyAbout);

        viewCopyright = findViewById(R.id.txtCopyright);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String year = simpleDateFormat.format(new Date());
        viewCopyright.setText("\u00a9 "+year+", Abhishek Kumar");
    }

    public void OnGoogleCivicInfoClicked(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(civicURL));
        startActivity(i);
    }
}
