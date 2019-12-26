package org.yapp.covey.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.yapp.covey.R;
import org.yapp.covey.adapter.AdapterLocationList;
import org.yapp.covey.adapter.AdapterLocationMoreList;
import org.yapp.covey.adapter.AdapterMoneyList;
import org.yapp.covey.databinding.ActivityMoreDetailBinding;
import org.yapp.covey.etc.ItemDecorationGrid;
import org.yapp.covey.etc.ItemPostVO;
import org.yapp.covey.util.Singleton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoreDetailActivity extends AppCompatActivity {
    ActivityMoreDetailBinding binding;
    AdapterLocationMoreList mLocationAdapter = new AdapterLocationMoreList();
    AdapterMoneyList mMoneyAdapter = new AdapterMoneyList();
    private static String TAG = "MORE POST ACTIVITY";
    String categoryTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_more_detail);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_more_detail);
        binding.setMoreLocation(this);

        final Intent intentDetail = new Intent(this, DetailActivity.class);

        categoryTitle = getIntent().getStringExtra("category");
        binding.tvTitle.setText(categoryTitle);

        getPostData(getIntent().getIntExtra("categoryNum",0));
        mLocationAdapter.setOnItemClickListener(new AdapterLocationList.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                intentDetail.putExtra("postId",mLocationAdapter.mDataList.get(position).getId());
                startActivity(intentDetail);
            }
        });

        mMoneyAdapter.setOnItemClickListener(new AdapterMoneyList.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                intentDetail.putExtra("postId",mLocationAdapter.mDataList.get(position).getId());
                startActivity(intentDetail);
            }
        });
    }
    public void getPostData(int category){
        int LOCATION = 1;
        int PAY = 2;
        if (category== LOCATION) {
            Singleton.retrofit.addressList(1).enqueue(new Callback<ArrayList<ItemPostVO>>() {
                @Override
                public void onResponse(Call<ArrayList<ItemPostVO>> call, Response<ArrayList<ItemPostVO>> response) {
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            ArrayList<ItemPostVO> result = response.body();
                            mLocationAdapter.mDataList.addAll(result);
                            mLocationAdapter.notifyDataSetChanged();
                            Log.w(TAG, String.valueOf(response.body()) + response.body().size());
                        } else
                            Log.w(TAG, String.valueOf(response.code()));
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<ItemPostVO>> call, Throwable t) {
                    Log.w(TAG, call.toString());
                }
            });

            binding.recyclerMorePost.setLayoutManager(new GridLayoutManager(this,2));
            binding.recyclerMorePost.addItemDecoration(new ItemDecorationGrid(this,8f,8f));
            binding.recyclerMorePost.setAdapter(mLocationAdapter);
        }
        else if (category == PAY){
            Singleton.retrofit.payList(1).enqueue(new Callback<ArrayList<ItemPostVO>>() {
                @Override
                public void onResponse(Call<ArrayList<ItemPostVO>> call, Response<ArrayList<ItemPostVO>> response) {
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            ArrayList<ItemPostVO> result = response.body();
                            mMoneyAdapter.mDataList.addAll(result);
                            mMoneyAdapter.notifyDataSetChanged();
                            Log.w(TAG, String.valueOf(response.body()) + response.body().size());
                        } else
                            Log.w(TAG, String.valueOf(response.code()));
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<ItemPostVO>> call, Throwable t) {
                    Log.w(TAG, "OnFailure Pay");
                }
            });

            binding.recyclerMorePost.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            binding.recyclerMorePost.setAdapter(mMoneyAdapter);
        }
    }
}
