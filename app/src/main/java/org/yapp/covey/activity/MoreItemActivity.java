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
import org.yapp.covey.databinding.ActivityMoreItemBinding;
import org.yapp.covey.etc.ItemDecorationGrid;
import org.yapp.covey.model.ItemDataModel;
import org.yapp.covey.util.Singleton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoreItemActivity extends AppCompatActivity {
    ActivityMoreItemBinding binding;
    AdapterLocationList mLocationAdapter = new AdapterLocationList();
    AdapterMoneyList mMoneyAdapter = new AdapterMoneyList();
    private static String TAG = "MORE ITEM ACTIVITY";
    String categoryTitle;
    int categoryNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_more_item);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_more_item);
        binding.setMoreLocation(this);

        final Intent intentDetail = new Intent(this, DetailActivity.class);

        categoryTitle = getIntent().getStringExtra("category");
        binding.tvTitle.setText(categoryTitle);

        categoryNum = getIntent().getIntExtra("categoryNum",0);
        Log.w("categoryNum", String.valueOf(categoryNum));
        getDetailData(categoryNum);
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
                intentDetail.putExtra("postId",mMoneyAdapter.mDataList.get(position).getId());
                startActivity(intentDetail);
            }
        });
    }
    public void getDetailData(int category){
        int LOCATION = 1;
        int PAY = 2;
        if (category == LOCATION) {
            Singleton.retrofit.addressList(1).enqueue(new Callback<ArrayList<ItemDataModel>>() {
                @Override
                public void onResponse(Call<ArrayList<ItemDataModel>> call, Response<ArrayList<ItemDataModel>> response) {
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            ArrayList<ItemDataModel> result = response.body();
                            mLocationAdapter.mDataList.addAll(result);
                            mLocationAdapter.notifyDataSetChanged();
                            Log.w(TAG, String.valueOf(response.body()) + response.body().size());
                        } else
                            Log.w(TAG, String.valueOf(response.code()));
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<ItemDataModel>> call, Throwable t) {
                    Log.w(TAG, call.toString());
                }
            });

            binding.recyclerMoreItem.setLayoutManager(new GridLayoutManager(this,2));
            binding.recyclerMoreItem.addItemDecoration(new ItemDecorationGrid(this,8f,8f));
            binding.recyclerMoreItem.setAdapter(mLocationAdapter);
        }
        else if (category == PAY){
            Singleton.retrofit.payList(1).enqueue(new Callback<ArrayList<ItemDataModel>>() {
                @Override
                public void onResponse(Call<ArrayList<ItemDataModel>> call, Response<ArrayList<ItemDataModel>> response) {
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            ArrayList<ItemDataModel> result = response.body();
                            mMoneyAdapter.mDataList.addAll(result);
                            mMoneyAdapter.notifyDataSetChanged();
                            Log.w(TAG, String.valueOf(response.body()) + response.body().size());
                        } else
                            Log.w(TAG, String.valueOf(response.code()));
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<ItemDataModel>> call, Throwable t) {
                    Log.w(TAG, "OnFailure Pay");
                }
            });

            binding.recyclerMoreItem.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            binding.recyclerMoreItem.setAdapter(mMoneyAdapter);
        }
    }
}
