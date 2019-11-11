package org.yapp.covey.etc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class userClass {
    @SerializedName("id")
    @Expose
    private int id;

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


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getSnsId() { return snsId; }

    public void setSnsId(String snsId) { this.snsId = snsId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public boolean isGender() { return gender; }

    public void setGender(boolean gender) { this.gender = gender; }

    public String getAge() { return age; }

    public void setAge(String age) { this.age = age; }

    public String getAddress1() { return address1; }

    public void setAddress1(String address1) { this.address1 = address1; }

    public String getAddress2() { return address2; }

    public void setAddress2(String address2) { this.address2 = address2; }

    public String getIntro() { return intro; }

    public void setIntro(String intro) { this.intro = intro; }

    public String getPhoneNum() { return phoneNum; }

    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }

    public String getImg() { return img; }

    public void setImg(String img) { this.img = img; }
}