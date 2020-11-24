package org.yapp.covey.activity;

import android.annotation.SuppressLint;
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
import org.yapp.covey.adapter.AdapterMoneyList;
import org.yapp.covey.databinding.ActivityMoreItemBinding;
import org.yapp.covey.helper.ItemDecorationGrid;
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
    String category, title;
    int categoryNum;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_more_item);
        binding.setMoreLocation(this);

        final Intent intentDetail = new Intent(this, DetailActivity.class);

        category = getIntent().getStringExtra("category");
        title = getIntent().getStringExtra("title");

        categoryNum = getIntent().getIntExtra("categoryNum",0);
        Log.w("categoryNum", String.valueOf(categoryNum));

        if (categoryNum == 3){
            binding.tvTitle.setText(category + " 대타");
        }else{
            binding.tvTitle.setText(title);
        }

        getDetailData(categoryNum);
        mLocationAdapter.setOnItemClickListener((v, position) -> {
            intentDetail.putExtra("postId",mLocationAdapter.mDataList.get(position).getId());
            startActivity(intentDetail);
        });

        mMoneyAdapter.setOnItemClickListener((v, position) -> {
            intentDetail.putExtra("postId",mMoneyAdapter.mDataList.get(position).getId());
            startActivity(intentDetail);
        });
    }
    public void getDetailData(int categoryNum){

        switch (categoryNum){
            case 1:{
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

                break;
            }
            case 2:{
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

                break;
            }

            case 3:{
                Singleton.retrofit.categoryList(1,category).enqueue(new Callback<ArrayList<ItemDataModel>>() {
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
                        Log.w(TAG, "OnFailure category loading");
                    }
                });
                binding.recyclerMoreItem.setLayoutManager(new GridLayoutManager(this,2));
                binding.recyclerMoreItem.addItemDecoration(new ItemDecorationGrid(this,8f,8f));
                binding.recyclerMoreItem.setAdapter(mLocationAdapter);

                break;
            }
        }
    }
}
