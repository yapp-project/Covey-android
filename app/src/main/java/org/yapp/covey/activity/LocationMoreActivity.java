package org.yapp.covey.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import org.yapp.covey.R;
import org.yapp.covey.adapter.AdapterLocationList;
import org.yapp.covey.databinding.ActivityLocationMoreBinding;
import org.yapp.covey.etc.ItemPostVO;
import org.yapp.covey.util.Singleton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationMoreActivity extends AppCompatActivity {
    ActivityLocationMoreBinding binding;
    AdapterLocationList mAdapter;
    private static String TAG = "LOCATION MORE ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_location_more);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_location_more);
        binding.setActivity(this);

        binding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getPostData();
        binding.recyclerMoneyMore.setLayoutManager(new GridLayoutManager(this,2));
        binding.recyclerMoneyMore.setAdapter(mAdapter);
    }
    public void getPostData(){
        Singleton.retrofit.addressList(1).enqueue(new Callback<ArrayList<ItemPostVO>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemPostVO>> call, Response<ArrayList<ItemPostVO>> response) {
                if (response.isSuccessful()){
                    if (response.code()==200) {
                        ArrayList<ItemPostVO> result = response.body();
                        mAdapter.mDataList.addAll(result);
                        mAdapter.notifyDataSetChanged();
                        Log.w(TAG, String.valueOf(response.body())+response.body().size());
                    }
                    else
                        Log.w(TAG, String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemPostVO>> call, Throwable t) {
                Log.w(TAG,"OnFailure LocationList");
            }
        });
    }
}
