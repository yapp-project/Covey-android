package org.yapp.covey.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.yapp.covey.R;
import org.yapp.covey.activity.CustomCalendarActivity;
import org.yapp.covey.adapter.AdapterCustomSpinner;
import org.yapp.covey.databinding.LayoutDialogFilterBinding;
import org.yapp.covey.helper.DatePickerHelper;
import org.yapp.covey.model.ItemDataModel;
import org.yapp.covey.util.Singleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class FilterDialogFragment extends BottomSheetDialogFragment {
    private LayoutDialogFilterBinding binding;
    private DatePickerHelper datePickerHelper;
    AdapterCustomSpinner sCityAdapter, sGuAdapter;

    private final static int REQUEST_CODE = 202;
    private final static int SPINNER_TYPE_CITY = 1;
    private final static int SPINNER_TYPE_GU = 2;

    private String startDate, endDate, address1, address2;
    private String category = "기타";
    private int payment = 0;

    private ArrayList<String> cityList= new ArrayList<>();
    ArrayList<String> guList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_dialog_filter,container,false);
        binding.tvCancel.setOnClickListener(view -> dismiss());

        ArrayList<String> weekList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.week)));

        datePickerHelper = new DatePickerHelper(getContext(), weekList);
        binding.tvStartDate.setOnClickListener(view -> dateSelect());
        binding.tvEndDate.setOnClickListener(view -> dateSelect());

        guList.add("구");
        setSpinner(binding.spinnerGu, guList, SPINNER_TYPE_GU);

        cityList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.city)));
        cityList.add("시");
        setSpinner(binding.spinnerCity, cityList, SPINNER_TYPE_CITY);

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

    private void setSpinner(Spinner spinner, ArrayList<String> spinnerData, int type){
        if (type == SPINNER_TYPE_CITY){
            sCityAdapter = new AdapterCustomSpinner(getContext(),spinnerData);
            spinner.setAdapter(sCityAdapter);
        }
        if (type == SPINNER_TYPE_GU){
            sGuAdapter = new AdapterCustomSpinner(getContext(), spinnerData);
            spinner.setAdapter(sGuAdapter);
        }
        spinner.setSelection(spinnerData.size()-1);
    }

    private void getInputData(){
        new Thread(() -> {
            payment = Integer.parseInt(binding.etPayment.getText().toString());
            startDate = binding.tvStartDate.getText().toString();
            endDate = binding.tvEndDate.getText().toString();
            binding.radioGroupCategory.setOnCheckedChangeListener((radioGroup, id) -> {

            });
        }).start();
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
