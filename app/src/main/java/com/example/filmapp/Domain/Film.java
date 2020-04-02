package com.example.filmapp.Domain;

import java.io.Serializable;

public class Film implements Serializable {
    private String mTitle;
    private String mDiscription;
    private String mReleaseDate;
    private String mPicture;
    private String mRating;
    private int mId;
    private int mVoteCount;
    private double mVoteAverage;

    public Film(String mTitle, String mDiscription, String mReleaseDate, String mPicture, String mRating, int mId, int mVoteCount, double mVoteAverage) {
        this.mTitle = mTitle;
        this.mDiscription = mDiscription;
        this.mReleaseDate = mReleaseDate;
        this.mPicture = mPicture;
        this.mRating = mRating;
        this.mId = mId;
        this.mVoteCount = mVoteCount;
        this.mVoteAverage = mVoteAverage;
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

    public String getmRating() {
        return mRating;
    }

    public int getmId() {
        return mId;
    }

    public int getmVoteCount() {
        return mVoteCount;
    }

    public double getmVoteAverage() {
        return mVoteAverage;
    }

    public void setMVoteAverage(double newVoteAverage){
        this.mVoteAverage = newVoteAverage;
    }

    public void setmVoteCount(){
        this.mVoteCount ++;
    }


    @Override
    public String toString() {
        return "Film{" +
                "mTitle='" + mTitle + '\'' +
                ", mDiscription='" + mDiscription + '\'' +
                ", mReleaseDate='" + mReleaseDate + '\'' +
                ", mPicture='" + mPicture + '\'' +
                ", mRating='" + mRating + '\'' +
                ", mId=" + mId +
                ", mVoteCount=" + mVoteCount +
                ", mVoteAverage=" + mVoteAverage +
                '}';
    }
}
