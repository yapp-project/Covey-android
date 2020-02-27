package org.yapp.covey.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import org.yapp.covey.R;
import org.yapp.covey.adapter.AdapterKeywordSearchList;
import org.yapp.covey.databinding.ActivitySearchAddressBinding;
import org.yapp.covey.etc.CustomAppBar;
import org.yapp.covey.model.KaKaoMapSearchModel;
import org.yapp.covey.util.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchAddressActivity extends AppCompatActivity {

    AdapterKeywordSearchList mAdaper = new AdapterKeywordSearchList();
    private static ActivitySearchAddressBinding binding;
    private String addressStr;
    private static final String TAG = "SEARCH_ADDRESS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_search_address);

        setCustomAppBar();
        binding.editSearchAddress.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    getAddress();
                    return true;
                }
                return false;
            }
        });
        binding.recyclerAddress.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        getAddress();
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_vertical_1dp));
        binding.recyclerAddress.addItemDecoration(itemDecoration);
        binding.recyclerAddress.setAdapter(mAdaper);
    }

    private void setCustomAppBar(){
        CustomAppBar customAppBar = new CustomAppBar(this, getSupportActionBar());
        customAppBar.setCustomAppBar("주소 검색");
        customAppBar.setBackClickListener(new CustomAppBar.backClickListener() {
            @Override
            public void onBackClick(View v) {
                finish();
            }
        });
    }

    private void getAddress(){
        addressStr = binding.editSearchAddress.getText().toString();
        Singleton.KakaoMaoApi.categoryList(1,addressStr).enqueue(new Callback<KaKaoMapSearchModel>() {
            @Override
            public void onResponse(Call<KaKaoMapSearchModel> call, Response<KaKaoMapSearchModel> response) {
                if (response.code() == 200){
                    if (response.body() != null) {
                        setResultView(true);
                        mAdaper.setmSearchList(response.body());
                    }
                }else if (response.code() == 400)   setResultView(false);
                Log.d(TAG, "response success"+ response.code());
            }

            @Override
            public void onFailure(Call<KaKaoMapSearchModel> call, Throwable t) {
                Log.d(TAG, "OnFailure"+ t.toString());
            }
        });
    }

    private void setResultView(Boolean hasData){
        if (hasData){
            binding.editSearchAddress.setBackground(getResources().getDrawable(R.drawable.rounded_rectangle_outline_red));
            binding.tvSearchTip.setVisibility(View.INVISIBLE);
            binding.tvSearchTips.setVisibility(View.INVISIBLE);
            binding.recyclerAddress.setVisibility(View.VISIBLE);
        }else{
            binding.editSearchAddress.setBackground(getResources().getDrawable(R.drawable.rounded_rectangle_outline_8dp));
            binding.tvSearchTip.setVisibility(View.VISIBLE);
            binding.tvSearchTips.setVisibility(View.VISIBLE);
            binding.recyclerAddress.setVisibility(View.INVISIBLE);
        }
    }

}
