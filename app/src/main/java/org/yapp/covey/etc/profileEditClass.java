package org.yapp.covey.etc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class profileEditClass {
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
}