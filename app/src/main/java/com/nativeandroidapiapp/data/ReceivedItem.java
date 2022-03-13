package com.nativeandroidapiapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReceivedItem {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("short_description")
    @Expose
    private String short_description;
    @SerializedName("minimum_system_requirements")
    @Expose
    private SystemRequirements systemRequirements;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public SystemRequirements getSystemRequirements() {
        return systemRequirements;
    }

    public void setSystemRequirements(SystemRequirements systemRequirements) {
        this.systemRequirements = systemRequirements;
    }

    @Override
    public String toString() {
        return "ReceivedItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", short_description='" + short_description + '\'' +
                ", systemRequirements=" + systemRequirements.toString() +
                '}';
    }
}
