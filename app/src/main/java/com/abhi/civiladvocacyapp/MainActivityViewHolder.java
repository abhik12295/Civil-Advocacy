package com.abhi.civiladvocacyapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivityViewHolder extends RecyclerView.ViewHolder {
    TextView txtOfficeName;
    TextView txtOfficialNameAndParty;
    ImageView imgOfficial;
    public MainActivityViewHolder(@NonNull View itemView) {
        super(itemView);
        this.txtOfficeName = itemView.findViewById(R.id.txtOfficeName);
        this.txtOfficialNameAndParty = itemView.findViewById(R.id.txtOfficialNameAndParty);
        this.imgOfficial = itemView.findViewById((R.id.imgPhotoOfficial));
    }
}
