package org.yapp.covey.etc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class userResponseClass {
    @SerializedName("user")
    @Expose
    private userClass user;

    @SerializedName("careerList")
    @Expose
    private careerClass careerList[];

    public userClass getUser() {
        return user;
    }

    public void setUser(userClass user) {
        this.user = user;
    }

    public careerClass[] getCareerList() {
        return careerList;
    }

    public void setCareerList(careerClass[] careerList) {
        this.careerList = careerList;
    }
}