package org.yapp.covey.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.yapp.covey.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecruitFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recruit, container, false);

        TextView titleText = rootView.findViewById(R.id.tv_title);
        titleText.setText("모집 내역");

        // Inflate the layout for this fragment
        return rootView;
    }

}
