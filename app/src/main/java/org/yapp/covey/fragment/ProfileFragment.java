package org.yapp.covey.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.yapp.covey.R;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private static final String TAG = "Profile";

    public ProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        return rootView;
    }
}
