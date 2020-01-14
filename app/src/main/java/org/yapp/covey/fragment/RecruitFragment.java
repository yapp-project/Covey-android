package org.yapp.covey.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.yapp.covey.R;
import org.yapp.covey.adapter.AdapterApplyRecruit;
import org.yapp.covey.model.ItemDataModel;
import org.yapp.covey.util.Singleton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecruitFragment extends Fragment {
    private String TAG = "RECRUIT";

    private RecyclerView recyclerViewRecruit;
    private AdapterApplyRecruit adapterRecruit = new AdapterApplyRecruit();
    private ConstraintLayout constraintNothing;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recruit, container, false);

        constraintNothing = rootView.findViewById(R.id.constraint_no_recruit);
        TextView titleText = rootView.findViewById(R.id.tv_title);
        titleText.setText("모집 내역");

        recyclerViewRecruit = rootView.findViewById(R.id.recycler_recruit);
        recyclerViewRecruit.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerViewRecruit.setAdapter(adapterRecruit);

        Singleton.retrofit.registerList().enqueue(new Callback<ArrayList<ItemDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemDataModel>> call, Response<ArrayList<ItemDataModel>> response) {
                if (response.isSuccessful()){
                    if (response.code() == 200){
                        adapterRecruit.mDataList.clear();
                        ArrayList<ItemDataModel> resultData = response.body();

                        if (resultData.size() == 0 ){
                            setViewVisible(constraintNothing, recyclerViewRecruit,true);
                        }
                        else{
                            adapterRecruit.mDataList.addAll(resultData);
                            adapterRecruit.notifyDataSetChanged();
                            setViewVisible(constraintNothing, recyclerViewRecruit,false);
                        }
                        Log.w(TAG,"apply list success");
                    }
                    else{
                        Log.w(TAG, String.valueOf(response.code()));
                        Toast.makeText(getContext(),"서버 연결을 확인해주세요",Toast.LENGTH_SHORT).show();
                        setViewVisible(constraintNothing, recyclerViewRecruit,true);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemDataModel>> call, Throwable t) {
                Log.w(TAG,"onFailure" + t);
            }
        });
        return rootView;
    }
    private void setViewVisible(ConstraintLayout constraintNothing, RecyclerView recyclerView, Boolean visible){
        if (visible){
            constraintNothing.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }else{
            constraintNothing.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
