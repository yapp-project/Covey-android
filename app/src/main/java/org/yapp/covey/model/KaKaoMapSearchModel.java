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
    private ArrayList<Document> documents;
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

