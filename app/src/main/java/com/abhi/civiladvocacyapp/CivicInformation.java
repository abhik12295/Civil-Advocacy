package com.abhi.civiladvocacyapp;

import java.io.Serializable;
import java.util.ArrayList;

public class CivicInformation implements Serializable {
    private final String normalizedInput_line1;
    private final String normalizedInput_city;
    private final String normalizedInput_state;
    private final String normalizedInput_zip;
    private final ArrayList<Offices> offices;
    private final ArrayList<Officials> officials;

    public CivicInformation(String normalizedInput_line1, String normalizedInput_city, String normalizedInput_state, String normalizedInput_zip, ArrayList<Offices> offices, ArrayList<Officials> officials) {
        this.normalizedInput_line1 = normalizedInput_line1;
        this.normalizedInput_city = normalizedInput_city;
        this.normalizedInput_state = normalizedInput_state;
        this.normalizedInput_zip = normalizedInput_zip;
        this.offices = offices;
        this.officials = officials;

    }
    public String getNormalizedInput_line1() {
        return normalizedInput_line1;
    }

    public String getNormalizedInput_city() {
        return normalizedInput_city;
    }

    public String getNormalizedInput_state() {
        return normalizedInput_state;
    }

    public String getNormalizedInput_zip() {
        return normalizedInput_zip;
    }

    public ArrayList<Offices> getOffices() {
        return offices;
    }

    public ArrayList<Officials> getOfficials() {
        return officials;
    }
}
class Offices implements Serializable{
    private final String officesName;
    private final ArrayList<Integer> offices_officialIndices;

    Offices(String offices_name, ArrayList<Integer> offices_officialIndices) {
        this.officesName = offices_name;
        this.offices_officialIndices = offices_officialIndices;
    }

    public String getOffices_name() {
        return officesName;
    }

    public ArrayList<Integer> getOffices_officialIndices() {
        return offices_officialIndices;
    }
}

class Officials implements Serializable {
    private final String officialsName;
    private final String officialsAddress;
    private final String officialsCity;
    private final String officialsState;
    private final String officialsUrl;
    private final String officialsEmail;
    private final String officialsZip;
    private final String officialsParty;
    private final String officialsPhone;
    private final String officialsPhotoUrl;
    private final ArrayList<Channels> officialsChannels;

    Officials(String officialsName, String officialsAddress, String officialsCity, String officialsState, String officialsZip, String officialsParty, String officialsPhone, String officialsUrl, String officialsEmail, String officialsPhotoUrl, ArrayList<Channels> officialsChannels) {
        this.officialsName = officialsName;
        this.officialsAddress = officialsAddress;
        this.officialsParty = officialsParty;
        this.officialsPhone = officialsPhone;
        this.officialsUrl = officialsUrl;
        this.officialsEmail = officialsEmail;
        this.officialsCity = officialsCity;
        this.officialsState = officialsState;
        this.officialsZip = officialsZip;
        this.officialsPhotoUrl = officialsPhotoUrl;
        this.officialsChannels = officialsChannels;
    }

    public String getOfficialsName() {
        return officialsName;
    }

    public String getOfficialsParty() {
        return officialsParty;
    }

    public String getOfficialsPhone() {
        return officialsPhone;
    }

    public String getOfficialsUrl() {
        return officialsUrl;
    }

    public String getOfficialsAddress() {
        return officialsAddress;
    }

    public String getOfficialsCity() {
        return officialsCity;
    }

    public String getOfficialsState() {
        return officialsState;
    }

    public String getOfficialsZip() {
        return officialsZip;
    }

    public String getOfficialsEmail() {
        return officialsEmail;
    }

    public String getOfficialsPhotoUrl() {
        return officialsPhotoUrl;
    }

    public ArrayList<Channels> getOfficialsChannels() {
        return officialsChannels;
    }
}

class Channels implements Serializable{
    private final String channels_type;
    private final String channels_id;

    Channels(String channels_type, String channels_id) {
        this.channels_type = channels_type;
        this.channels_id = channels_id;
    }

    public String getChannels_type() {
        return channels_type;
    }

    public String getChannels_id() {
        return channels_id;
    }
}