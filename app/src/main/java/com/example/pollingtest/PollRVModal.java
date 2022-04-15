package com.example.pollingtest;

import android.os.Parcel;
import android.os.Parcelable;

public class PollRVModal implements Parcelable {
    // creating variables for our different fields.
    private String pollName;
    private String pollDescription;
    private String pollImg;
    private String pollId;
    private String option1;
    private String option2;
    private String option3;
    private Integer votes1;
    private Integer votes2;
    private Integer votes3;

    // creating an empty constructor which is required to use firebase.
    public PollRVModal() {

    }

    // creating getter and setter methods.


    protected PollRVModal(Parcel in) {
        pollName = in.readString();
        pollDescription = in.readString();
        pollImg = in.readString();
        pollId = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        votes1 = 0;
        votes2 = 0;
        votes3 = 0;
    }

    public static final Creator<PollRVModal> CREATOR = new Creator<PollRVModal>() {
        @Override
        public PollRVModal createFromParcel(Parcel in) {
            return new PollRVModal(in);
        }

        @Override
        public PollRVModal[] newArray(int size) {
            return new PollRVModal[size];
        }
    };

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getPollName() {
        return pollName;
    }

    public void setPollName(String pollName) {
        this.pollName = pollName;
    }

    public String getPollDescription() {
        return pollDescription;
    }

    public void setPollDescription(String pollDescription) {
        this.pollDescription = pollDescription;
    }

    public Integer getVotes1() {
        return votes1;
    }

    public void setVotes1(Integer votes1) {
        this.votes1 = votes1;
    }

    public Integer getVotes2() {
        return votes2;
    }

    public void setVotes2(Integer votes2) {
        this.votes2 = votes2;
    }

    public Integer getVotes3() {
        return votes3;
    }

    public void setVotes3(Integer votes3) {
        this.votes3 = votes3;
    }

    public String getPollImg() {
        return pollImg;
    }

    public void setPollImg(String pollImg) {
        this.pollImg = pollImg;
    }

    public String getPollId() {
        return pollId;
    }

    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    public PollRVModal(String pollId, String pollName, String pollDescription, String pollImg, String option1, String option2, String option3, Integer votes1, Integer votes2, Integer votes3) {
        this.pollName = pollName;
        this.pollId = pollId;
        this.pollDescription = pollDescription;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.pollImg = pollImg;
        this.votes1 = votes1;
        this.votes2 = votes2;
        this.votes3 = votes3;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(pollName);
        parcel.writeString(pollDescription);
        parcel.writeString(pollImg);
        parcel.writeString(pollId);
    }
}
