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
import org.yapp.covey.adapter.AdapterMoneyList;
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
    private AdapterLocationList adapterLocationPost = new AdapterLocationList();
    private AdapterMoneyList adapterMoneyList = new AdapterMoneyList();
    private ArrayList<String> mArrayData = new ArrayList<>();

    private static final String TAG = "HOME";
    private static final int DATA_PAY = 1;
    private static final int DATA_LOCATION = 2;
    private static final int DATA_CATEGORY = 3;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mArrayData.addAll(Arrays.asList(CategoryData.sData));

        setInitView(rootView);

//        getPostData();
//        getMoneyData();
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

            }
        });

        Singleton.retrofit.payList(1).enqueue(new Callback<ArrayList<ItemPostVO>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemPostVO>> call, Response<ArrayList<ItemPostVO>> response) {
                if (response.isSuccessful()){
                    if (response.code()==200) {
                        ArrayList<ItemPostVO> result = response.body();
                        assert result != null;
                        adapterMoneyList.mDataList.addAll(result);
                        adapterMoneyList.notifyDataSetChanged();

                        Log.d(TAG, String.valueOf(response.code()));
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
//
//    private void setRecyclerView(){
////        switch (kind){
////            case DATA_LOCATION:{
////
////            }
////            case DATA_PAY:{
////
////            }
////            case DATA_CATEGORY:{
//
////            }
////        }
//    }

//    private void getPostData(){
//        Singleton.retrofit.addressList(1).enqueue(new Callback<ArrayList<ItemPostVO>>() {
//            @Override
//            public void onResponse(Call<ArrayList<ItemPostVO>> call, Response<ArrayList<ItemPostVO>> response) {
//                if (response.isSuccessful()){
//                    if (response.code()==200) {
//                        adapterLocationPost = new AdapterLocationList(response.body());
//                        Log.w(TAG, String.valueOf(response.body()));
//                    }
//                    else
//                        Log.w(TAG, String.valueOf(response.code()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<ItemPostVO>> call, Throwable t) {
//
//            }
//        });
//    }

//    private void getMoneyData(){
//        Singleton.retrofit.payList(1).enqueue(new Callback<ArrayList<ItemPostVO>>() {
//            @Override
//            public void onResponse(Call<ArrayList<ItemPostVO>> call, Response<ArrayList<ItemPostVO>> response) {
//                if (response.isSuccessful()){
//                    if (response.code()==200) {
//                        ArrayList<ItemPostVO> result = response.body();
//                        adapterMoneyList = new AdapterMoneyList(result);
//                        Log.d(TAG, String.valueOf(response.code()));
//                    }
//                    else
//                        Log.d(TAG, String.valueOf(response.code()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<ItemPostVO>> call, Throwable t) {
//                Log.w(TAG,"OnFailure PayList");
//            }
//        });
//    }
}
