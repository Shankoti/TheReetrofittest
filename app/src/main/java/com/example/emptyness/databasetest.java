package com.example.emptyness;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DATASET")
public class databasetest {
   @PrimaryKey
   @NonNull
   private String date;
   private String Fajr;
   private  String Dhuhr;
   private String Asr;
   private String Maghrib;
   private String Isha;

    public databasetest() {
    }

    @Override
    public String toString() {
        return "databasetest{" +
                "date='" + date + '\'' +
                ", Fajr='" + Fajr + '\'' +
                ", Dhuhr='" + Dhuhr + '\'' +
                ", Asr='" + Asr + '\'' +
                ", Maghrib='" + Maghrib + '\'' +
                ", Isha='" + Isha + '\'' +
                '}';
    }

    public databasetest(@NonNull String date, String fajr, String dhuhr, String asr, String maghrib, String isha) {
        this.date = date;
        Fajr = fajr;
        Dhuhr = dhuhr;
        Asr = asr;
        Maghrib = maghrib;
        Isha = isha;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFajr() {
        return Fajr;
    }

    public void setFajr(String fajr) {
        Fajr = fajr;
    }

    public String getDhuhr() {
        return Dhuhr;
    }

    public void setDhuhr(String dhuhr) {
        Dhuhr = dhuhr;
    }

    public String getAsr() {
        return Asr;
    }

    public void setAsr(String asr) {
        Asr = asr;
    }

    public String getMaghrib() {
        return Maghrib;
    }

    public void setMaghrib(String maghrib) {
        Maghrib = maghrib;
    }

    public String getIsha() {
        return Isha;
    }

    public void setIsha(String isha) {
        Isha = isha;
    }
}
