package com.example.filmapp.Domain;

public class Film {
    private String mTitle;
    private String mGenre;
    private String mDiscription;
    private String mReleaseDate;
    private String mPicture;
    private int mRuntime;
    private int mReviewScore;

    public Film(String mTitle, String mGenre, String mDiscription, String mReleaseDate, int mRuntime, int mReviewScore, String mPicture) {
        this.mTitle = mTitle;
        this.mGenre = mGenre;
        this.mDiscription = mDiscription;
        this.mReleaseDate = mReleaseDate;
        this.mRuntime = mRuntime;
        this.mReviewScore = mReviewScore;
        this.mPicture = mPicture;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmGenre() {
        return mGenre;
    }

    public String getmDiscription() {
        return mDiscription;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public int getmRuntime() {
        return mRuntime;
    }

    public int getmReviewScore() {
        return mReviewScore;
    }

    public String getmPicture() {
        return mPicture;
    }

    @Override
    public String toString() {
        return "Film{" +
                "mTitle='" + mTitle + '\'' +
                ", mGenre='" + mGenre + '\'' +
                ", mDiscription='" + mDiscription + '\'' +
                ", mReleaseDate='" + mReleaseDate + '\'' +
                ", mRuntime=" + mRuntime +
                ", mReviewScore=" + mReviewScore +
                '}';
    }
}
