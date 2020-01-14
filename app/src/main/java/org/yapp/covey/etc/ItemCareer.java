package org.yapp.covey.etc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemCareer {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("userId")
    @Expose
    private String userId;
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
    private Boolean periodUnit;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Boolean getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(Boolean periodUnit) {
        this.periodUnit = periodUnit;
    }
}
