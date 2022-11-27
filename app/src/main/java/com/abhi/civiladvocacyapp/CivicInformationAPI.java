package com.abhi.civiladvocacyapp;

import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CivicInformationAPI implements Runnable {
    private static final String TAG = "CivicInformationAPI";
    private static final String URL = "https://www.googleapis.com/civicinfo/v2/representatives";
    private static final String API_key = "AIzaSyDi9tCAwiVBmB04XzItumSF1esmRaC4Ts0";
    private final MainActivity mActivity;
    private final String address;

    public CivicInformationAPI(MainActivity mActivity, String address) {
        this.mActivity = mActivity;
        this.address = address;
    }

    @Override
    public void run() {
        Uri.Builder builder = Uri.parse(URL).buildUpon();
        builder.appendQueryParameter("key", API_key);
        builder.appendQueryParameter("address", address);

        String urlToUse = builder.build().toString();
        Log.d(TAG, "run: " + urlToUse.toString());
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(
                        Request.Method.GET,
                        urlToUse,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                CivicInformation civicInformation = parseJSON(response.toString());
                                mActivity.runOnUiThread(() -> mActivity.updateData(civicInformation));
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e(TAG, "onErrorResponse: ", error);
                            }
                        });
        RequestQueue requestQueue = Volley.newRequestQueue(mActivity);
        requestQueue.add(jsonObjectRequest);
    }

    private CivicInformation parseJSON(String s) {
        try {
            JSONObject objMain = new JSONObject(s);

            JSONObject normalizedInput = objMain.getJSONObject("normalizedInput");
            String normalizedInput_line1=normalizedInput.getString("line1");
            String normalizedInput_city=normalizedInput.getString("city");
            String normalizedInput_state=normalizedInput.getString("state");
            String normalizedInput_zip=normalizedInput.getString("zip");

            JSONArray offices=objMain.getJSONArray("offices");
            ArrayList<Offices> lstObjOffices = new ArrayList<>();
            for(int i=0;i<offices.length();i++){
                JSONObject objOffice=(JSONObject) offices.get(i);
                String objOffice_name = objOffice.has("name")?objOffice.getString("name"):"";

                JSONArray objOfficeOfficialIndices = objOffice.getJSONArray("officialIndices");
                ArrayList<Integer> lastOfficialIndices = new ArrayList<>();
                for(int j=0;j<objOfficeOfficialIndices.length();j++){
                    lastOfficialIndices.add(objOfficeOfficialIndices.getInt(j));
                }
                lstObjOffices.add(new Offices(objOffice_name,lastOfficialIndices));
            }

            //Fetching Officials Data
            JSONArray officials = objMain.getJSONArray("officials");
            ArrayList<Officials> lstObjOfficials = new ArrayList<>();
            for(int k=0;k<officials.length();k++){
                JSONObject objOfficials = (JSONObject) officials.get(k);
                String objOfficials_name = objOfficials.has("name")?objOfficials.getString("name"):"";
                String address_city = "";
                String address_state = "";
                String address_zip = "";
                String address_line = "";
                if(objOfficials.has("address")) {
                    JSONArray objOfficials_addresses = objOfficials.getJSONArray("address");
                    if (objOfficials_addresses.length() > 0) {
                        JSONObject jObjOfficials_address = (JSONObject) objOfficials_addresses.get(0);
                        String address_line1 = jObjOfficials_address.has("line1") ? jObjOfficials_address.getString("line1") : "";
                        String address_line2 = jObjOfficials_address.has("line2") ? jObjOfficials_address.getString("line2") : "";
                        String address_line3 = jObjOfficials_address.has("line3") ? jObjOfficials_address.getString("line3") : "";
                        address_city = jObjOfficials_address.has("city") ? jObjOfficials_address.getString("city") : "";
                        address_state = jObjOfficials_address.has("state") ? jObjOfficials_address.getString("state") : "";
                        address_zip = jObjOfficials_address.has("zip") ? jObjOfficials_address.getString("zip") : "";
                        address_line = address_line1 + " " + address_line2 + " " + address_line3;
                    }
                }
                //Fetching Party details
                String objOfficials_party=objOfficials.has("party")?objOfficials.getString("party"):"";
                //Fetching Phone details
                JSONArray objOfficials_phones=objOfficials.has("phones")?objOfficials.getJSONArray("phones"):null;
                String objOfficials_phone="";
                if(objOfficials_phones!=null){
                    objOfficials_phone = objOfficials_phones.get(0).toString();
                }
                //Fetching required URLs
                JSONArray objOfficials_urls=objOfficials.has("urls")?objOfficials.getJSONArray("urls"):null;
                String objOfficials_url="";
                if(objOfficials_urls!=null){
                    objOfficials_url = objOfficials_urls.get(0).toString();
                }
                //fetching email
                JSONArray objOfficials_emails=objOfficials.has("emails")?objOfficials.getJSONArray("emails"):null;
                String jObjOfficials_email="";
                if(objOfficials_emails!=null){
                    jObjOfficials_email = objOfficials_emails.get(0).toString();
                }
                //fetching photo url and storing it in picasso
                String objOfficials_photoUrl = objOfficials.has("photoUrl")?objOfficials.getString("photoUrl"):"";
                //fetching channels
                JSONArray objOfficials_channels=objOfficials.has("channels")?objOfficials.getJSONArray("channels"):null;
                ArrayList<Channels> lstObjChannels=null;
                if(objOfficials_channels!=null)
                {
                    lstObjChannels = new ArrayList<>();
                    for(int l=0;l<objOfficials_channels.length();l++)
                    {
                        JSONObject jObjOfficials_channel =(JSONObject) objOfficials_channels.get(l);
                        String jObjOfficials_channels_type = jObjOfficials_channel.has("type")?jObjOfficials_channel.getString("type"):"";
                        String jObjOfficials_channels_id = jObjOfficials_channel.has("id")?jObjOfficials_channel.getString("id"):"";
                        lstObjChannels.add(new Channels(jObjOfficials_channels_type, jObjOfficials_channels_id));
                    }
                }
                lstObjOfficials.add(new Officials(objOfficials_name,address_line,address_city,address_state,address_zip,
                        objOfficials_party,objOfficials_phone,objOfficials_url,jObjOfficials_email,objOfficials_photoUrl,lstObjChannels));

            }
            return new CivicInformation(normalizedInput_line1,normalizedInput_city,normalizedInput_state,normalizedInput_zip,lstObjOffices,lstObjOfficials);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "error: ", e);
        }
        return null;
    }
}
