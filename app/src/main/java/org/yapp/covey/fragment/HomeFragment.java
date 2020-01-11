package org.yapp.covey.fragment;

import android.content.Intent;
import android.graphics.Paint;
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
import org.yapp.covey.activity.AlertActivity;
import org.yapp.covey.activity.DetailActivity;
import org.yapp.covey.activity.MoreItemActivity;
import org.yapp.covey.activity.UploadActivity;
import org.yapp.covey.adapter.AdapterCategoryList;
import org.yapp.covey.adapter.AdapterLocationList;
import org.yapp.covey.adapter.AdapterMoneyList;
import org.yapp.covey.etc.ItemDecorationCategory;
import org.yapp.covey.model.ItemDataModel;
import org.yapp.covey.util.Singleton;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener{
    private ImageButton buttonAlert, buttonFilter;
    private TextView tvUpload, tvTitle, tvMoreLocationPost, tvMoreMoneyPost;
    private RecyclerView listViewCategory, recyclerViewLocation, recyclerViewPay;
    private AdapterCategoryList adapterCategory;
    private AdapterLocationList adapterLocationPost = new AdapterLocationList();
    private AdapterMoneyList adapterMoneyList = new AdapterMoneyList();
    private ArrayList<String> mArrayData = new ArrayList<>();

    private boolean emptyData = true;
    Intent intentItemDetail;

    private static final String TAG = "HOME";

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        if (emptyData){
            mArrayData.addAll(Arrays.asList(getResources().getStringArray(R.array.category)));
            emptyData = false;
        }
        adapterCategory = new AdapterCategoryList(getContext(),mArrayData);

        setInitView(rootView);

        getAddressData();
        getMoneyData();

        intentItemDetail = new Intent(getContext(), DetailActivity.class);

        adapterMoneyList.mDataList.clear();
        adapterLocationPost.mDataList.clear();

        recyclerViewLocation.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerViewLocation.addItemDecoration(new ItemDecorationCategory(getContext(),rootView.getWidth()*0.044f));

        adapterLocationPost.setOnItemClickListener(new AdapterLocationList.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                intentItemDetail.putExtra("postId",adapterLocationPost.mDataList.get(position).getId());
                startActivity(intentItemDetail);
            }
        });

        recyclerViewLocation.setAdapter(adapterLocationPost);
        recyclerViewPay.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        adapterMoneyList.setOnItemClickListener(new AdapterMoneyList.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                intentItemDetail.putExtra("postId",adapterLocationPost.mDataList.get(position).getId());
                startActivity(intentItemDetail);
            }
        });
        recyclerViewPay.setAdapter(adapterMoneyList);

        setTitle("커비에서 빠르고 편리한\n" + "알바 대타를 경험해보세요!");

        listViewCategory.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        listViewCategory.addItemDecoration(new ItemDecorationCategory(getContext(),4f));

        adapterCategory.setOnItemClickListener(new AdapterCategoryList.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intentMoreItem = new Intent(getContext(), MoreItemActivity.class);
                intentMoreItem.putExtra("category",mArrayData.get(position));
                intentMoreItem.putExtra("categoryNum",3);
                startActivity(intentMoreItem);
            }
        });

        listViewCategory.setAdapter(adapterCategory);

        return rootView;
    }
    private void setInitView(View view){
        buttonAlert = view.findViewById(R.id.btn_alert);
        buttonAlert.setOnClickListener(this);

        buttonFilter = view.findViewById(R.id.btn_filter);
        buttonFilter.setOnClickListener(this);
        tvUpload = view.findViewById(R.id.btn_upload);
        tvUpload.setPaintFlags(tvUpload.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvUpload.setOnClickListener(this);

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

    private void getAddressData(){
        Singleton.retrofit.addressList(1).enqueue(new Callback<ArrayList<ItemDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemDataModel>> call, Response<ArrayList<ItemDataModel>> response) {
                if (response.isSuccessful()){
                    int code = response.code();
                    if (code == 200){
                        ArrayList<ItemDataModel> result = response.body();
                        adapterLocationPost.mDataList.addAll(result);
                        adapterLocationPost.notifyDataSetChanged();
                        Log.w(TAG, String.valueOf(response.body())+response.body().size());
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemDataModel>> call, Throwable t) {
                Log.w(TAG, t+"addressList");
            }
        });
    }

    private void getMoneyData(){
         Singleton.retrofit.payList(1).enqueue(new Callback<ArrayList<ItemDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemDataModel>> call, Response<ArrayList<ItemDataModel>> response) {
                if (response.isSuccessful()){
                    if (response.code()==200) {
                        ArrayList<ItemDataModel> result = response.body();
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
            public void onFailure(Call<ArrayList<ItemDataModel>> call, Throwable t) {
                Log.w(TAG,t+"payList");
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
        Intent intentMoreItem = new Intent(getContext(), MoreItemActivity.class);
        switch (view.getId()){
            case R.id.btn_alert:{
                Intent intentAlert = new Intent(getContext(), AlertActivity.class);
                startActivity(intentAlert);
                break;
            }
            case R.id.tv_location_more:{
                intentMoreItem.putExtra("title","우리 동네 대타");
                intentMoreItem.putExtra("categoryNum",1);
                startActivity(intentMoreItem);
                break;
            }
            case R.id.tv_money_more:{
                intentMoreItem.putExtra("title","고수익 알바");
                intentMoreItem.putExtra("categoryNum",2);
                startActivity(intentMoreItem);
                break;
            }
            case R.id.btn_filter:{
//                showBottomSheetFragment(view);
                FilterDialogFragment filterDialogFragment = new FilterDialogFragment();
                filterDialogFragment.show(getFragmentManager(),"filter");
                break;
            }
            case R.id.btn_upload:{
                Intent intentUpload = new Intent(getContext(), UploadActivity.class);
                startActivity(intentUpload);
                break;
            }
        }
    }
}
