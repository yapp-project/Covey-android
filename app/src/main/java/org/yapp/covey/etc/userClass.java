package org.yapp.covey.etc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class userClass {
    @SerializedName("_id")
    @Expose
    private String _id;

    @SerializedName("snsId")
    @Expose
    private String snsId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("gender")
    @Expose
    private boolean gender;

    @SerializedName("age")
    @Expose
    private String age;

    @SerializedName("address1")
    @Expose
    private String address1;

    @SerializedName("address2")
    @Expose
    private String address2;

    @SerializedName("intro")
    @Expose
    private String intro;

    @SerializedName("phoneNum")
    @Expose
    private String phoneNum;

    @SerializedName("img")
    @Expose
    private String img;
}