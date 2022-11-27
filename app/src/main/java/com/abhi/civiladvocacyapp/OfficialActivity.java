package com.abhi.civiladvocacyapp;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.squareup.picasso.Picasso;

public class OfficialActivity extends AppCompatActivity {
    private static final String TAG = "OfficialActivity";
    Officials officials;
    ScrollView scrollView;
    ConstraintLayout constraintLayout;
    TextView viewAddressLabelOfficial;
    TextView viewAddressOfficial;
    ImageView imgPhotoOfficial;
    TextView viewLocationOfficial;
    TextView viewOfficeOfficial;
    TextView viewNameOfficial;
    TextView viewPartyOfficial;
    ImageView imgTwitterOfficial;
    ImageView imgYoutubeOfficial;
    TextView viewPhoneLabelOfficial;
    TextView viewPhoneOfficial;
    TextView viewEmailLabelOfficial;
    TextView viewEmailOfficial;
    TextView viewWebsiteLabelOfficial;
    TextView viewWebsiteOfficial;
    String location;
    String officeName;
    ImageView imgPartyOfficial;
    ImageView imgFacebookOfficial;
    private Picasso picasso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_official);
        getSupportActionBar().setTitle(R.string.civilAdvocacyAbout);
        picasso = Picasso.get();
        setViews();
        getData();
    }

    private void setViews() {
        scrollView = findViewById(R.id.scrollView);
        constraintLayout = findViewById((R.id.constraintLayout));
        viewLocationOfficial = findViewById(R.id.viewLocationOfficial);
        viewOfficeOfficial = findViewById(R.id.viewOfficeOfficial);
        imgFacebookOfficial = findViewById(R.id.imgFacebookOfficial);
        imgTwitterOfficial = findViewById(R.id.imgTwitterOfficial);
        imgYoutubeOfficial = findViewById(R.id.imgYoutubeOfficial);
        viewNameOfficial = findViewById(R.id.viewNameOfficial);
        viewPartyOfficial = findViewById(R.id.viewPartyOfficial);
        viewAddressLabelOfficial = findViewById(R.id.viewAddressLabelOfficial);
        viewAddressOfficial = findViewById(R.id.viewAddressOfficial);
        imgPhotoOfficial = findViewById(R.id.imgPhotoOfficial);
        imgPartyOfficial = findViewById(R.id.imgPartyOfficial);
        viewPhoneLabelOfficial = findViewById(R.id.viewPhoneLabelOfficial);
        viewPhoneOfficial = findViewById(R.id.viewPhoneOfficial);
        viewEmailLabelOfficial = findViewById(R.id.viewEmailLabelOfficial);
        viewEmailOfficial = findViewById(R.id.viewEmailOfficial);
        viewWebsiteLabelOfficial = findViewById(R.id.viewWebsiteLabelOfficial);
        viewWebsiteOfficial = findViewById(R.id.viewWebsiteOfficial);
    }

    @SuppressLint("SetTextI18n")
    private void getData() {
        officials = (Officials) getIntent().getSerializableExtra("officialData");
        officeName = getIntent().getStringExtra("officeName").toString();
        location = getIntent().getStringExtra("location");
        viewLocationOfficial.setText(location);
        viewOfficeOfficial.setText(officeName);
        viewNameOfficial.setText(officials.getOfficialsName());
        if(officials.getOfficialsParty().toUpperCase().contains("DEMOCRATIC")){
            viewPartyOfficial.setVisibility(View.VISIBLE);
            viewPartyOfficial.setText(" ( " + officials.getOfficialsParty()+" )");
            scrollView.setBackgroundColor(Color.BLUE);
            imgPartyOfficial.setVisibility(View.VISIBLE);
            imgPartyOfficial.setImageResource(R.drawable.dem_logo);
        }
        else if(officials.getOfficialsParty().toUpperCase().contains("REPUBLICAN")){
            viewPartyOfficial.setVisibility(View.VISIBLE);
            viewPartyOfficial.setText(" ( " + officials.getOfficialsParty()+" )");
            scrollView.setBackgroundColor(Color.RED);
            imgPartyOfficial.setVisibility(View.VISIBLE);
            imgPartyOfficial.setImageResource(R.drawable.rep_logo);
        }
        else{
            viewPartyOfficial.setVisibility(View.VISIBLE);
            viewPartyOfficial.setText(" ( " + officials.getOfficialsParty() +" )");
            scrollView.setBackgroundColor(Color.BLACK);
            imgPartyOfficial.setVisibility(View.INVISIBLE);
        }
        if(officials.getOfficialsPhotoUrl()!=null && !officials.getOfficialsPhotoUrl().isEmpty()) {
            loadRemoteImage(officials.getOfficialsPhotoUrl());
        }
        if(officials.getOfficialsAddress()!=null && !officials.getOfficialsAddress().isEmpty()){
            viewAddressLabelOfficial.setVisibility(View.VISIBLE);
            viewAddressOfficial.setVisibility(View.VISIBLE);
            viewAddressOfficial.setText(officials.getOfficialsAddress() + " " + officials.getOfficialsCity() + ", " + officials.getOfficialsState() + " " + officials.getOfficialsZip());
        }
        else{
            viewAddressLabelOfficial.setVisibility(View.INVISIBLE);
            viewAddressOfficial.setVisibility(View.INVISIBLE);
        }
        if(officials.getOfficialsPhone()!=null && !officials.getOfficialsPhone().isEmpty()){
            viewPhoneLabelOfficial.setVisibility(View.VISIBLE);
            viewPhoneOfficial.setVisibility(View.VISIBLE);
            viewPhoneOfficial.setText(officials.getOfficialsPhone());
        }
        else{
            viewPhoneLabelOfficial.setVisibility(View.INVISIBLE);
            viewPhoneOfficial.setVisibility(View.INVISIBLE);
        }
        if(officials.getOfficialsEmail()!=null && !officials.getOfficialsEmail().isEmpty()){
            viewEmailLabelOfficial.setVisibility(View.VISIBLE);
            viewEmailOfficial.setVisibility(View.VISIBLE);
            viewEmailOfficial.setText(officials.getOfficialsEmail());
        }
        else{
            viewEmailLabelOfficial.setVisibility(View.INVISIBLE);
            viewEmailOfficial.setVisibility(View.INVISIBLE);
        }
        if(officials.getOfficialsUrl()!=null && !officials.getOfficialsUrl().isEmpty()){
            viewWebsiteLabelOfficial.setVisibility(View.VISIBLE);
            viewWebsiteOfficial.setVisibility(View.VISIBLE);
            viewWebsiteOfficial.setText(officials.getOfficialsUrl());
        }
        else{
            viewWebsiteLabelOfficial.setVisibility(View.INVISIBLE);
            viewWebsiteOfficial.setVisibility(View.INVISIBLE);
        }
        imgFacebookOfficial.setVisibility(View.GONE);
        imgTwitterOfficial.setVisibility(View.GONE);
        imgYoutubeOfficial.setVisibility(View.GONE);
        if (officials.getOfficialsChannels()!=null && !officials.getOfficialsChannels().isEmpty()) {
            if(officials.getOfficialsChannels().size()>0){
                for(int i=0;i<officials.getOfficialsChannels().size();i++){
                    Channels channels = officials.getOfficialsChannels().get(i);
                    if(channels.getChannels_id()!=null && !channels.getChannels_id().isEmpty()){
                        if(channels.getChannels_type().toUpperCase().contains("FACEBOOK")){
                            imgFacebookOfficial.setVisibility(View.VISIBLE);
                        }
                        if(channels.getChannels_type().toUpperCase().contains("TWITTER")){
                            imgTwitterOfficial.setVisibility(View.VISIBLE);
                        }
                        if(channels.getChannels_type().toUpperCase().contains("YOUTUBE")){
                            imgYoutubeOfficial.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        }
    }

    public void OnAddressClicked(View view) {
        try {
            if(officials!=null && !officials.getOfficialsAddress().isEmpty()){
                String address = officials.getOfficialsAddress() + " " + officials.getOfficialsCity() + ", " + officials.getOfficialsState() + " " + officials.getOfficialsZip();
                Uri map = Uri.parse("geo:0,0?q=" + Uri.encode(address));
                Intent intent = new Intent(Intent.ACTION_VIEW, map);
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(OfficialActivity.this, R.string.noApplicationOfficial, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "OnAddressClicked: ",e );
        }
    }

    private void loadRemoteImage(String imageURL) {
        try {
            Log.d(TAG, "loadRemoteImage: "+imageURL);
            picasso.load(imageURL)
                    .error(R.drawable.missing)
                    .placeholder(R.drawable.brokenimage)
                    .into(imgPhotoOfficial);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "loadRemoteImage: ", e);
        }
    }

    public void OnPartyLogoClicked(View view) {
        if(officials!=null && !officials.getOfficialsParty().isEmpty()){
            String party_url="";
            if (officials.getOfficialsParty().toUpperCase().contains("REPUBLICAN")) {
                party_url = "https://www.gop.com/";
            }
            else if (officials.getOfficialsParty().toUpperCase().contains("DEMOCRATIC")) {
                party_url = "https://democrats.org/";
            }
            openUrl(party_url);
        }
    }

    public void OnImageClicked(View view) {
        if (officials != null && officials.getOfficialsPhotoUrl()!=null && !officials.getOfficialsPhotoUrl().isEmpty()) {
            Intent intent = new Intent(this, PhotoDetailActivity.class);
            intent.putExtra("officeName",officeName);
            intent.putExtra("officialData", officials);
            intent.putExtra("location", location);
            startActivity(intent);
        } else {
            Toast.makeText(OfficialActivity.this, R.string.noPhotoOfficials, Toast.LENGTH_SHORT).show();
        }
    }


    public void OnPhoneClicked(View view) {
        try {
            if(officials!=null && !officials.getOfficialsPhone().isEmpty()){
                String mobile = officials.getOfficialsPhone();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + mobile));
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(OfficialActivity.this, R.string.noCallApplicationOfficials, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "OnPhoneClicked: ", e);
        }
    }



    public void OnWebsiteClicked(View view) {
        if(officials!=null && !officials.getOfficialsUrl().isEmpty()){
            openUrl(officials.getOfficialsUrl());
        }
    }

    public void OnEmailClicked(View view) {
        try {
            if (officials!=null && !officials.getOfficialsEmail().isEmpty()) {
                String[] getEmail = new String[]{officials.getOfficialsEmail()};
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, getEmail);
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(OfficialActivity.this, R.string.noEmailClientOfficials, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "OnEmailClicked: ", e);
        }
    }

    public void OnFacebookClicked(View view) {
        String FB_URL = "";
        String final_URL="";
        Channels channels = null;
        PackageManager packageManager = getPackageManager();
        try {
            if (officials != null && officials.getOfficialsChannels().size() > 0) {
                for (int i = 0; i < officials.getOfficialsChannels().size(); i++) {
                    channels = officials.getOfficialsChannels().get(i);
                    if (channels.getChannels_id() != null && !channels.getChannels_id().isEmpty()) {
                        if (channels.getChannels_type().toUpperCase().contains("FACEBOOK")) {
                            FB_URL = "https://www.facebook.com/" + channels.getChannels_id();
                        }
                    }
                }
            }
            if(!FB_URL.isEmpty()) {
                int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
                if (versionCode >= 3002850) {
                    final_URL = "fb://facewebmodal/f?href=" + FB_URL;
                } else {
                    final_URL = "fb://page/" + channels.getChannels_id();
                }
            }
        }
        catch (PackageManager.NameNotFoundException e) {
            final_URL = FB_URL;
        }
        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        facebookIntent.setData(Uri.parse(final_URL));
        startActivity(facebookIntent);

    }

    public void OnTwitterClicked(View view) {
        Intent intent = null;
        String twitterID="";
        try {
            if (officials != null && officials.getOfficialsChannels().size() > 0) {
                for (int i = 0; i < officials.getOfficialsChannels().size(); i++) {
                    Channels channels = officials.getOfficialsChannels().get(i);
                    if (channels.getChannels_id() != null && !channels.getChannels_id().isEmpty()) {
                        if (channels.getChannels_type().toUpperCase().contains("TWITTER")) {
                            twitterID = channels.getChannels_id();
                        }
                    }
                }
            }
            if(!twitterID.isEmpty()) {
                getPackageManager().getPackageInfo("com.twitter.android", 0);
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + twitterID));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        }
        catch (Exception e) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + twitterID));
        }
        startActivity(intent);
    }

    public void OnYoutubeClicked(View view) {
        Intent intent = null;
        String youtubeID="";
        try {
            if (officials != null && officials.getOfficialsChannels().size() > 0) {
                for (int i = 0; i < officials.getOfficialsChannels().size(); i++) {
                    Channels channels = officials.getOfficialsChannels().get(i);
                    if (channels.getChannels_id() != null && !channels.getChannels_id().isEmpty()) {
                        if (channels.getChannels_type().toUpperCase().contains("YOUTUBE")) {
                            youtubeID = channels.getChannels_id();
                        }
                    }
                }
            }
            if(!youtubeID.isEmpty()) {
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setPackage("com.google.android.youtube");
                intent.setData(Uri.parse("https://www.youtube.com/" + youtubeID));
                startActivity(intent);
            }
        }
        catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/" + youtubeID)));
        }
    }

    public void openUrl(String url) {
        if (url != null && !url.isEmpty()) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
    }
}
