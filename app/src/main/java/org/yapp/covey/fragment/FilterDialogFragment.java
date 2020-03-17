package org.yapp.covey.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.yapp.covey.R;
import org.yapp.covey.activity.CustomCalendarActivity;
import org.yapp.covey.databinding.LayoutDialogFilterBinding;
import org.yapp.covey.helper.DatePickerHelper;
import org.yapp.covey.model.ItemDataModel;
import org.yapp.covey.util.Singleton;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class FilterDialogFragment extends BottomSheetDialogFragment {
    private LayoutDialogFilterBinding binding;
    private DatePickerHelper datePickerHelper;

    private final static int REQUEST_CODE = 202;

    private String startDate, endDate, address1, address2;
    private String category = "기타";
    private int payment = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_dialog_filter,container,false);
        binding.tvCancel.setOnClickListener(view -> dismiss());

        ArrayList<String> weekList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.week)));

        datePickerHelper = new DatePickerHelper(getContext(), weekList);
        binding.tvStartDate.setOnClickListener(view -> dateSelect());
        binding.tvEndDate.setOnClickListener(view -> dateSelect());
        return binding.getRoot();
    }

    private void dateSelect() {
        startActivityForResult(datePickerHelper.getCalendarIntent(), REQUEST_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((resultCode == RESULT_OK)&&(requestCode == REQUEST_CODE && data != null)) {
            startDate = data.getStringExtra(CustomCalendarActivity.RESULT_SELECT_START_DATE);
            endDate = data.getStringExtra(CustomCalendarActivity.RESULT_SELECT_END_DATE);
            binding.tvStartDate.setText(startDate);
            binding.tvEndDate.setText(endDate);
        }
    }

    private void getInputData(){
        payment = Integer.parseInt(binding.etPayment.getText().toString());
        binding.radioGroupCategory.setOnCheckedChangeListener((radioGroup, id) -> {

        });
    }

    public void sendData(){
        Singleton.retrofit.filterList(1,payment, category, address1, address2, startDate, endDate)
                .enqueue(new Callback<ArrayList<ItemDataModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ItemDataModel>> call, Response<ArrayList<ItemDataModel>> response) {

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ItemDataModel>> call, Throwable t) {

                    }
                });
    }
}
