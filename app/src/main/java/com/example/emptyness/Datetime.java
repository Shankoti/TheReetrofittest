
package com.example.emptyness;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datetime {

    @SerializedName("times")
    @Expose
    private Times times;
    @SerializedName("date")
    @Expose
    private Date date;

    public Times getTimes() {
        return times;
    }

    public void setTimes(Times times) {
        this.times = times;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
