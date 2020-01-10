package org.yapp.covey.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.yapp.covey.R;
import org.yapp.covey.adapter.AdapterApplyRecruit;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecruitFragment extends Fragment {
    String TAG = "RECRUIT";

    private RecyclerView recyclerViewRecruit;
    AdapterApplyRecruit adapterRecruit = new AdapterApplyRecruit();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recruit, container, false);

        TextView titleText = rootView.findViewById(R.id.tv_title);
        titleText.setText("모집 내역");

        recyclerViewRecruit = rootView.findViewById(R.id.recycler_recruit);
        recyclerViewRecruit.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));



        return rootView;
    }

}
