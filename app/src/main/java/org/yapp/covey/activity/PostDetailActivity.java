package org.yapp.covey.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import org.yapp.covey.R;
import org.yapp.covey.databinding.LayoutPostDetailBinding;
import org.yapp.covey.etc.ItemPostVO;

public class PostDetailActivity extends AppCompatActivity {

    LayoutPostDetailBinding binding;
    private ItemPostVO itemPostData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.layout_post_detail);

        binding = DataBindingUtil.setContentView(this, R.layout.layout_post_detail);
        binding.setPostDetail(itemPostData);

    }
}
