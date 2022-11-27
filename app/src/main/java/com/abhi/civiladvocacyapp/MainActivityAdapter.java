package com.abhi.civiladvocacyapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityViewHolder> {
    private static final String TAG = "MainActivityAdapter";
    private Picasso picasso;
    private final MainActivity mainActivity;
    private final CivicInformation civicInformation;
    MainActivityAdapter(MainActivity mainActivity, CivicInformation civicInformation)
    {
        this.mainActivity = mainActivity;
        this.civicInformation = civicInformation;
    }

    @NonNull
    @Override
    public MainActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_row, parent, false);
        itemView.setOnClickListener(mainActivity);
        return new MainActivityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MainActivityViewHolder holder, final int position) {

        try {
            Officials officials = civicInformation.getOfficials().get(position);
            picasso = Picasso.get();
            holder.txtOfficialNameAndParty.setText(officials.getOfficialsName() + " (" + officials.getOfficialsParty() + ") ");

            for(int i=0;i<civicInformation.getOffices().size();i++) {
                Offices offices = civicInformation.getOffices().get(i);
                if(offices.getOffices_officialIndices().contains(position)){
                    holder.txtOfficeName.setText(offices.getOffices_name());
                    break;
                }
            }

            //added and called photos of the officials
            if(officials.getOfficialsPhotoUrl()!=null && !officials.getOfficialsPhotoUrl().isEmpty()) {
                try {
                    picasso.load(officials.getOfficialsPhotoUrl()).placeholder(R.drawable.brokenimage).error(R.drawable.missing).into(holder.imgOfficial);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, "loadRemoteImage: ", e);
                }
           }
            else{
                holder.imgOfficial.setImageResource(R.drawable.missing);
            }
        }
        catch (Exception e){
            Log.e(TAG, "onBindViewHolder: ", e);
        }
    }

    @Override
    public int getItemCount() {
        try{
            Log.d(TAG, "getItemCount: "+civicInformation.getOfficials().size());
            return civicInformation.getOfficials().size();
        }
        catch (Exception e) {
            Log.e(TAG, "getItemCount: ", e);
        }
        return 0;
    }

}
