package org.yapp.covey.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import org.yapp.covey.R;
import org.yapp.covey.adapter.AdapterApplyImage;
import org.yapp.covey.databinding.LayoutPostDetailBinding;
import org.yapp.covey.etc.CustomAppBar;
import org.yapp.covey.helper.ItemDecorationLinear;
import org.yapp.covey.model.ItemDataModel;
import org.yapp.covey.util.Singleton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    int activityCategory;
    String postId;
    LayoutPostDetailBinding binding;
    AdapterApplyImage adapterApplyImage = new AdapterApplyImage();
    ArrayList<String> imageUriList = new ArrayList<>();
    private ItemDataModel itemPostData;
    private static String TAG = "POST DETAIL ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.layout_post_detail);

        postId = getIntent().getStringExtra("postId");
        setButtonText(activityCategory);
        setCustomAppBar();
        getPostData(postId);

        binding.recyclerImage.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        binding.recyclerImage.addItemDecoration(new ItemDecorationLinear(this,8f,3));

        binding.btnApply.setOnClickListener(view -> clickApply(postId));
    }
    public void setButtonText(int activityCategory){
        switch (activityCategory){
            case 1:{
                binding.btnApply.setText("지원 취소");
                break;
            }
            case 2:{
                binding.btnApply.setText("지원 하기");
                break;
            }
            case 3:{
                binding.btnApply.setText("모집마감");
                break;
            }
        }
    }

    private void getPostData(final String postId){
        Singleton.retrofit.postDetail(postId).enqueue(new Callback<ItemDataModel>() {
            @Override
            public void onResponse(Call<ItemDataModel> call, Response<ItemDataModel> response) {
                if (response.code()==200){
                    itemPostData = response.body();
                    Log.w(TAG, postId);

                    imageUriList.add(itemPostData.getImg1());
                    imageUriList.add(itemPostData.getImg2());
                    imageUriList.add(itemPostData.getImg3());
                    setImageRecycler(imageUriList);
                    setPostData(itemPostData);
            }
                else if (response.code() ==404){
                    Log.d(TAG, "code 404");
                    Toast.makeText(getApplicationContext(),"해당 게시글을 찾을 수 없습니다",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ItemDataModel> call, Throwable t) {
                Log.d(TAG, "onFailure");
                Toast.makeText(getApplicationContext(),"서버 연결을 확인해주세요",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setPostData(ItemDataModel itemPostData){
        String location = itemPostData.getAddress1()+" "+itemPostData.getAddress2()+" "+itemPostData.getAddress3();
        binding.tvLocation.setText("위치\t\t"+location);
        binding.tvPay.setText("시급\t\t"+itemPostData.getPay()+"원");
        binding.tvStartToEndDate.setText("일시\t\t"+itemPostData.getStartDate().substring(0,10)+" ~ "+itemPostData.getEndDate().substring(0,10));
        binding.tvTime.setText("시간\t\t"+itemPostData.getWorkingTime());
        binding.tvDiscription.setText(itemPostData.getDescription());
        binding.tvTitle.setText(itemPostData.getTitle());
    }

    private void setImageRecycler(ArrayList<String> imageList){
        adapterApplyImage.imageUriList = imageList;
        binding.recyclerImage.setAdapter(adapterApplyImage);
        adapterApplyImage.notifyDataSetChanged();
    }

    private void setCustomAppBar(){
        CustomAppBar customAppBar = new CustomAppBar(this, getSupportActionBar());
        customAppBar.setCustomAppBar("상세 정보");
        customAppBar.setBackClickListener(v -> finish());
    }
    @SuppressLint("ResourceAsColor")
    public void clickApply(String postId){
        switch (activityCategory){
//            case 1:{
//                // apply code server err
//                break;
//            }
//            case 2:{
//                // cancel code server err
//                break;
//            }
//            case 3:{
//                binding.btnApply.setText("모집마감");
//                break;
//            }
        }
      binding.btnApply.setVisibility(View.INVISIBLE);
      binding.btnApplied.setVisibility(View.VISIBLE);
    }
}