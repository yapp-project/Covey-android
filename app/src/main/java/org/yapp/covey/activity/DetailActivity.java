package org.yapp.covey.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import org.yapp.covey.R;
import org.yapp.covey.databinding.LayoutPostDetailBinding;
import org.yapp.covey.etc.CustomAppBar;
import org.yapp.covey.model.ItemDataModel;
import org.yapp.covey.util.Singleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    int activityCategory;
    String postId;
    LayoutPostDetailBinding binding;
    private ItemDataModel itemPostData;
    private static String TAG = "POST DETAIL ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.layout_post_detail);

        activityCategory = getIntent().getIntExtra("detailCategory",0);
        postId = getIntent().getStringExtra("postId");
        Log.d(TAG, postId);
        setButtonText(activityCategory);
        setCustomAppBar();
        getPostData(postId);

        binding.btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickApply(postId);
            }
        });
    }
    public void setButtonText(int activityCategory){
        switch (activityCategory){
            case 1:{
                binding.btnApply.setText("지원하기");
                break;
            }
            case 2:{
                binding.btnApply.setText("지원 취소");
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
                    Log.w(TAG, itemPostData.getTitle());
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
        binding.tvContent.setText(itemPostData.getDescription());
        binding.tvTitle.setText(itemPostData.getTitle());
    }
    private void setCustomAppBar(){
        CustomAppBar customAppBar = new CustomAppBar(this, getSupportActionBar());
        customAppBar.setCustomAppBar("상세 정보");
        customAppBar.setBackClickListener(new CustomAppBar.backClickListener() {
            @Override
            public void onBackClick(View v) {
                finish();
            }
        });
    }
    @SuppressLint("ResourceAsColor")
    public void clickApply(String postId){
//        Singleton.retrofit.apply(postId).enqueue(new Callback<JSONArray>() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onResponse(Call<JSONArray> call, Response<JSONArray> response) {
//                if (response.isSuccessful()){
//                    if (response.code()==201){
//                        Log.d("성공","apply posting");
//                        binding.btnApply.setBackgroundColor(R.color.white_four);
//                        binding.btnApply.setTextColor(R.color.warm_grey);
//                    }
//                }
//                else{
//                    Toast.makeText(getApplicationContext(), "서버연결을 확인해주세요",Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<JSONArray> call, Throwable t) {
//
//            }
//        });

      binding.btnApply.setVisibility(View.INVISIBLE);
      binding.btnApplied.setVisibility(View.VISIBLE);
    }
}