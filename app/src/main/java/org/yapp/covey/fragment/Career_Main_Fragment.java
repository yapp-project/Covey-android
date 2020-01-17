package org.yapp.covey.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.yapp.covey.R;
import org.yapp.covey.activity.CareerActivity;
import org.yapp.covey.activity.SettingActivity;
import org.yapp.covey.adapter.AdapterCareerList;
import org.yapp.covey.etc.ItemCareer;
import org.yapp.covey.etc.careerClass;
import org.yapp.covey.util.Singleton;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Career_Main_Fragment extends Fragment implements View.OnClickListener{
    private final String TAG = "Career";
    private RecyclerView careerListView;
    private AdapterCareerList careerAdapter = new AdapterCareerList();
    public static Career_Main_Fragment newInstance() {
        return new Career_Main_Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_career_main, container, false);
        ((CareerActivity)getActivity()).setCustomAppBar("경력사항");

        careerListView = view.findViewById(R.id.recycler_career);
        careerAdapter.mDataList.clear();
        getCareerData();
        careerListView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        careerListView.setAdapter(careerAdapter);

        return view;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            /*case R.id.setting_app_info:{
                ((SettingActivity)getActivity()).replaceFragment(Setting_AppInfo_Fragment.newInstance());
                break;
            }*/
        }
    }

    private void getCareerData(){
        Singleton.retrofit.getCareer().enqueue(new Callback<ArrayList<careerClass>>() {
            @Override
            public void onResponse(Call<ArrayList<careerClass>> call, Response<ArrayList<careerClass>> response) {
                if (response.isSuccessful()){
                    if (response.code()==200) {
                        ArrayList<careerClass> result = response.body();
                        assert result != null;
                        careerAdapter.mDataList.addAll(result);
                        careerAdapter.notifyDataSetChanged();
                        Log.w(TAG, String.valueOf(response.body())+response.body().size());
                    }
                    else
                        Log.w(TAG, String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<careerClass>> call, Throwable t) {
                Log.w(TAG,"OnFailure LocationList");
            }
        });
    }

}
