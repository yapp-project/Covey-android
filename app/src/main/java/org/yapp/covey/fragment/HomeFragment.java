package org.yapp.covey.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.yapp.covey.R;
import org.yapp.covey.activity.LocationMoreActivity;
import org.yapp.covey.activity.MainActivity;
import org.yapp.covey.adapter.AdapterCategoryList;
import org.yapp.covey.adapter.AdapterLocationList;
import org.yapp.covey.adapter.AdapterMoneyList;
import org.yapp.covey.etc.CategoryData;
import org.yapp.covey.etc.ItemDecorationCategory;
import org.yapp.covey.etc.ItemPostVO;
import org.yapp.covey.util.Singleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener{
    ImageButton buttonAlert, buttonFilter;
    TextView tvCome, tvTitle, tvMoreLocationPost, tvMoreMoneyPost;
    private RecyclerView listViewCategory, recyclerViewLocation, recyclerViewPay;
    private AdapterCategoryList adapterCategory;
    private AdapterLocationList adapterLocationPost = new AdapterLocationList();
    private AdapterMoneyList adapterMoneyList = new AdapterMoneyList();
    private ArrayList<String> mArrayData = new ArrayList<>();

    private static final String TAG = "HOME";

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mArrayData.addAll(Arrays.asList(CategoryData.sData));

        setInitView(rootView);

        getPostData();
        getMoneyData();

        recyclerViewLocation.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerViewLocation.addItemDecoration(new ItemDecorationCategory(getContext(),rootView.getWidth()*0.044f));
        recyclerViewLocation.setAdapter(adapterLocationPost);

        recyclerViewPay.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerViewPay.setAdapter(adapterMoneyList);

        setTitle("커비에서 빠르고 편리한\n" + "알바 대타를 경험해보세요!");
        adapterCategory = new AdapterCategoryList(getContext(),mArrayData);
        listViewCategory.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        listViewCategory.addItemDecoration(new ItemDecorationCategory(getContext(),4f));
        listViewCategory.setAdapter(adapterCategory);

        return rootView;
    }
    private void setInitView(View view){
        buttonAlert = view.findViewById(R.id.btn_alert);
        buttonFilter = view.findViewById(R.id.btn_filter);
        buttonFilter.setOnClickListener(this);
        tvCome = view.findViewById(R.id.btn_come);
        tvTitle = view.findViewById(R.id.tv_title);

        tvMoreLocationPost = view.findViewById(R.id.tv_location_more);
        tvMoreMoneyPost = view.findViewById(R.id.tv_money_more);

        tvMoreLocationPost.setOnClickListener(this);
        tvMoreMoneyPost.setOnClickListener(this);

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

    private void getPostData(){
        Singleton.retrofit.addressList(1).enqueue(new Callback<ArrayList<ItemPostVO>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemPostVO>> call, Response<ArrayList<ItemPostVO>> response) {
                if (response.isSuccessful()){
                    if (response.code()==200) {
                        ArrayList<ItemPostVO> result = response.body();
                        adapterLocationPost.mDataList.addAll(result);
                        adapterLocationPost.notifyDataSetChanged();
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

    private void getMoneyData(){
         Singleton.retrofit.payList(1).enqueue(new Callback<ArrayList<ItemPostVO>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemPostVO>> call, Response<ArrayList<ItemPostVO>> response) {
                if (response.isSuccessful()){
                    if (response.code()==200) {
                        ArrayList<ItemPostVO> result = response.body();
                        assert result != null;
                        adapterMoneyList.mDataList.addAll(result);
                        adapterMoneyList.notifyDataSetChanged();

                        Log.w(TAG, String.valueOf(result));
                    }
                    else
                        Log.d(TAG, String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemPostVO>> call, Throwable t) {
                Log.w(TAG,"OnFailure PayList");
            }
        });
    }

//    public void showBottomSheetFragment(View view) {
//        FilterDialogFragment filterDialogFragment = new FilterDialogFragment();
//
//        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
//
//        int width = displayMetrics.widthPixels;
//        int height = displayMetrics.heightPixels;
//
//        BottomSheetBehavior mBehavior = BottomSheetBehavior.from(view);
//        mBehavior.setPeekHeight((int) (height*0.73));
//        filterDialogFragment.show(getFragmentManager(),"filter");
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_location_more:{
                Intent intentLocationMore = new Intent(getContext(), LocationMoreActivity.class);
                startActivity(intentLocationMore);
                break;
            }
            case R.id.tv_money_more:{

            }
            case R.id.btn_filter:{
//                showBottomSheetFragment(view);
                FilterDialogFragment filterDialogFragment = new FilterDialogFragment();
                filterDialogFragment.show(getFragmentManager(),"filter");

            }
        }
    }
}
