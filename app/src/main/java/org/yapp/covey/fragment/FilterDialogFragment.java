package org.yapp.covey.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.yapp.covey.R;
import org.yapp.covey.databinding.LayoutDialogFilterBinding;

public class FilterDialogFragment extends BottomSheetDialogFragment {
    private LayoutDialogFilterBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_dialog_filter,container,false);

        binding.tvCancel.setOnClickListener(view -> dismiss());

        return binding.getRoot();
    }
}
