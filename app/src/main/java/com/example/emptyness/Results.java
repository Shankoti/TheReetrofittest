
package com.example.emptyness;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("datetime")
    @Expose
    private ArrayList<Datetime> datetime = null;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("settings")
    @Expose
    private Settings settings;

    public ArrayList<Datetime> getDatetime() {
        return datetime;
    }

    public void setDatetime(ArrayList<Datetime> datetime) {
        this.datetime = datetime;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

}
