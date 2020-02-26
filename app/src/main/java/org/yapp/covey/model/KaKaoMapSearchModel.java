package org.yapp.covey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class KaKaoMapSearchModel {
    @SerializedName("meta")
    @Expose
    private meta metaData;

    @SerializedName("documents")
    @Expose
    private ArrayList<document> documents;
}
@Data
class meta{
    @SerializedName("total_count")
    @Expose
    private Integer totalCount;

    @SerializedName("pageable_count")
    @Expose
    private Integer pageableCount;

    @SerializedName("is_end")
    @Expose
    private Boolean isEnd;

    @SerializedName("same_name")
    @Expose
    private same sameName;
}

@Data
class same{
    @SerializedName("region")
    @Expose
    private List<String> region;

    @SerializedName("keyword")
    @Expose
    private String keyword;

    @SerializedName("selected_region")
    @Expose
    private String selectedRegion;
}

@Data
class document{
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
