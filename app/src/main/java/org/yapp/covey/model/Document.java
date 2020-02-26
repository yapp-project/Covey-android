package org.yapp.covey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Document{
    @SerializedName("place_name")
    @Expose
    private String placeName;

    @SerializedName("distance")
    @Expose
    private String distance;

    @SerializedName("place_url")
    @Expose
    private String placeUrl;

    @SerializedName("category_name")
    @Expose
    private String categoryName;

    @SerializedName("address_name")
    @Expose
    private String addressName;

    @SerializedName("road_address_name")
    @Expose
    private String roadAddressName;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("category_group_code")
    @Expose
    private String categoryGroupCode;

    @SerializedName("category_group_name")
    @Expose
    private String categoryGroupName;

    @SerializedName("x")
    @Expose
    private String x;

    @SerializedName("y")
    @Expose
    private String y;
}
