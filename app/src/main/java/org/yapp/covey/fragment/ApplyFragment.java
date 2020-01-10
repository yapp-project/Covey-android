package org.yapp.covey.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class ApplyFragment extends Fragment {
    String TAG = "APPLY";

    private RecyclerView recyclerViewApply;
    AdapterApplyRecruit adapterApply = new AdapterApplyRecruit();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_apply, container, false);

        TextView titleText = rootView.findViewById(R.id.tv_title);
        titleText.setText("지원 내역");

        if (adapterApply.mDataList!=null) adapterApply.mDataList.clear();

        recyclerViewApply = rootView.findViewById(R.id.recycler_apply);
        recyclerViewApply.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        Singleton.retrofit.applyList().enqueue(new Callback<ArrayList<ItemDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemDataModel>> call, Response<ArrayList<ItemDataModel>> response) {
                if (response.isSuccessful()){
                    if (response.code() == 200){
                        ArrayList<ItemDataModel> resultData = response.body();
                        adapterApply.mDataList.addAll(resultData);
                        adapterApply.notifyDataSetChanged();

                        Log.w(TAG,"apply list success");
                    }
                    else{
                        Log.w(TAG, String.valueOf(response.code()));
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemDataModel>> call, Throwable t) {
                Log.w(TAG,"onFailure" + t);
            }
        });
        recyclerViewApply.setAdapter(adapterApply);
        return rootView;
    }
}
