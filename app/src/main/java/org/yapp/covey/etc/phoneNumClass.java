package org.yapp.covey.etc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class phoneNumClass {
    @SerializedName("phoneNum")
    @Expose
    private String phoneNum;

    @SerializedName("verifyNumFromClient")
    @Expose
    private String verifyNumFromClient;

    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }

    public String getPhoneNum() { return phoneNum; }

    public void setVerifyNumFromClient(String verifyNumFromClient) { this.verifyNumFromClient = verifyNumFromClient; }

    public String getVerifyNumFromClient() { return verifyNumFromClient; }
}
