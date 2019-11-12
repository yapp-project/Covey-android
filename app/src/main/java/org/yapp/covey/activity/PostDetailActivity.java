package org.yapp.covey.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import org.yapp.covey.R;
import org.yapp.covey.databinding.LayoutPostDetailBinding;
import org.yapp.covey.etc.ItemPostVO;
import org.yapp.covey.util.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDetailActivity extends AppCompatActivity {

    int postId;
    LayoutPostDetailBinding binding;
    private ItemPostVO itemPostData;
    private static String TAG = "POST DETAIL ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.layout_post_detail);
        binding = DataBindingUtil.setContentView(this, R.layout.layout_post_detail);
        binding.setPostDetail(itemPostData);

        postId = getIntent().getIntExtra("postId",0);
        Log.d(TAG, String.valueOf(postId));


    }

    private void getPostData(){
        Singleton.retrofit.postDetail(postId).enqueue(new Callback<ItemPostVO>() {
            @Override
            public void onResponse(Call<ItemPostVO> call, Response<ItemPostVO> response) {

            }

            @Override
            public void onFailure(Call<ItemPostVO> call, Throwable t) {

            }
        });
    }
}
