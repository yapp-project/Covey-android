package org.yapp.covey.etc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class careerClass {
    @SerializedName("_id")
    @Expose
    private String _id;

    @SerializedName("userid")
    @Expose
    private String userid;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("job")
    @Expose
    private String job;

    @SerializedName("periodNum")
    @Expose
    private String periodNum;

    @SerializedName("periodUnit")
    @Expose
    private String periodUnit;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String isUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPeriodNum() {
        return periodNum;
    }

    public void setPeriodNum(String periodNum) {
        this.periodNum = periodNum;
    }

    public String getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(String periodUnit) {
        this.periodUnit = periodUnit;
    }
}