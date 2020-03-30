package com.example.filmapp.Domain;

import java.io.Serializable;

public class Film implements Serializable {
    private String mTitle;
    private String mDiscription;
    private String mReleaseDate;
    private String mPicture;

    public Film(String mTitle, String mDiscription, String mReleaseDate, String mPicture) {
        this.mTitle = mTitle;
        this.mDiscription = mDiscription;
        this.mReleaseDate = mReleaseDate;
        this.mPicture = mPicture;
    }

    public String getmTitle() {
        return mTitle;
    }


    public String getmDiscription() {
        return mDiscription;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public String getmPicture() {
        return mPicture;
    }

    @Override
    public String toString() {
        return "Film{" +
                "mTitle='" + mTitle + '\'' +
                ", mDiscription='" + mDiscription + '\'' +
                ", mReleaseDate='" + mReleaseDate + '\'' +
                '}';
    }
}
