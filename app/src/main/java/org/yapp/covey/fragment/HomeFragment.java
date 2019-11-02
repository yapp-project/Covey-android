package org.yapp.covey.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.yapp.covey.R;
import org.yapp.covey.adapter.AdapterCategoryList;
import org.yapp.covey.adapter.AdapterLocationList;
import org.yapp.covey.etc.CategoryData;
import org.yapp.covey.etc.ItemDecorationCategory;
import org.yapp.covey.etc.ItemPostVO;
import org.yapp.covey.util.Singleton;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    ImageButton buttonAlert, buttonFilter;
    TextView tvCome, tvTitle;
    private RecyclerView listViewCategory, recyclerViewLocation, recyclerViewPay;
    private AdapterCategoryList adapterCategory;
    private AdapterLocationList adapterLocationPost;
    private ArrayList<String> mArrayData = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;

    private static final String TAG = "HOME";
    private static final int DATA_PAY = 1;
    private static final int DATA_LOCATION = 2;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mArrayData.addAll(Arrays.asList(CategoryData.sData));

        linearLayoutManager = new LinearLayoutManager(getContext()
                ,LinearLayoutManager.HORIZONTAL,false);

        setInitView(rootView);
        setTitle("커비에서 빠르고 편리한\n" + "알바 대타를 경험해보세요!");
        setRecyclerView();

        getPostData();
        recyclerViewLocation.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerViewLocation.setAdapter(adapterLocationPost);

        return rootView;
    }
    private void setInitView(View view){
        buttonAlert = view.findViewById(R.id.btn_alert);
        buttonFilter = view.findViewById(R.id.btn_filter);
        tvCome = view.findViewById(R.id.btn_come);
        tvTitle = view.findViewById(R.id.tv_title);

        listViewCategory = view.findViewById(R.id.recycler_category);
        recyclerViewLocation = view.findViewById(R.id.recycler_distance);
        recyclerViewPay = view.findViewById(R.id.recycler_money);
    }

    private void setTitle(String title){
        SpannableStringBuilder builder = new SpannableStringBuilder(title);
        builder.setSpan(new StyleSpan(Typeface.BOLD),0,2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new StyleSpan(Typeface.BOLD),5,18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTitle.append(builder);
    }

    private void setRecyclerView(){
        adapterCategory = new AdapterCategoryList(getContext(),mArrayData);
        listViewCategory.setLayoutManager(linearLayoutManager);
        listViewCategory.addItemDecoration(new ItemDecorationCategory(getContext(),8f));
        listViewCategory.setAdapter(adapterCategory);
    }

    public void getPostData(){
        Singleton.retrofit.addressList(1).enqueue(new Callback<ArrayList<ItemPostVO>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemPostVO>> call, Response<ArrayList<ItemPostVO>> response) {
                if (response.isSuccessful()){
                    if (response.code()==200) {
                        adapterLocationPost = new AdapterLocationList(response.body());
                        Log.d(TAG, String.valueOf(response.body().get(0)));
                    }
                    else
                        Log.d(TAG, String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemPostVO>> call, Throwable t) {

            }
        });
    }
}
