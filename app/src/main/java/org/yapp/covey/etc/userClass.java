package org.yapp.covey.etc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class userClass {
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
}