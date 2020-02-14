package org.yapp.covey.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import org.yapp.covey.R;
import org.yapp.covey.activity.CareerActivity;
import org.yapp.covey.etc.careerClass;
import org.yapp.covey.util.Singleton;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Career_Edit_Fragment extends Fragment implements View.OnClickListener{
    private String careerId;
    private Spinner careerSpinner;
    private RelativeLayout careerAddButton;
    private EditText careerCompany, careerTitle, careerTime;
    private final String TAG = "careerEdit";
    ArrayList<String> spinnerItems = new ArrayList<String>();

    public static Career_Edit_Fragment newInstance() {
        return new Career_Edit_Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_career_add, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ((CareerActivity)getActivity()).setCustomAppBar("경력사항 수정");

        spinnerItems.add("주");
        spinnerItems.add("개월");
        spinnerItems.add("년");
        careerSpinner = view.findViewById(R.id.career_spinner);
        careerAddButton = view.findViewById((R.id.career_edit_button));
        careerCompany = view.findViewById((R.id.career_edit_company));
        careerTitle = view.findViewById((R.id.career_edit_title));
        careerTime = view.findViewById((R.id.career_edit_time));

        careerAddButton.setBackground(getResources().getDrawable(R.drawable.red_square));
        careerAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                careerClass editedCareer = new careerClass();
                editedCareer.setJob(careerTitle.getText().toString());
                editedCareer.setName(careerCompany.getText().toString());
                editedCareer.setPeriodNum(careerTime.getText().toString());
                editedCareer.setPeriodUnit(careerSpinner.getSelectedItem().toString());
                editCareer(careerId, editedCareer);
            }
        });

        ArrayAdapter<String> spinnerAdapter= new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, spinnerItems);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        careerSpinner.setAdapter(spinnerAdapter);

        careerCompany.addTextChangedListener(careerTextWatcher());
        careerTitle.addTextChangedListener(careerTextWatcher());
        careerTime.addTextChangedListener(careerTextWatcher());

        careerId = getArguments().getString("careerId");

        getCareerDetail(careerId);

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

    private TextWatcher careerTextWatcher(){
        return new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(careerCompany.getText().length() >= 1 && careerTitle.getText().length() >= 1 && careerTime.getText().length() >= 1) {
                    careerAddButton.setBackground(getResources().getDrawable(R.drawable.red_square));
                    careerAddButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            careerClass editedCareer = new careerClass();
                            editedCareer.setJob(careerTitle.getText().toString());
                            editedCareer.setName(careerCompany.getText().toString());
                            editedCareer.setPeriodNum(careerTime.getText().toString());
                            editedCareer.setPeriodUnit(careerSpinner.getSelectedItem().toString());
                            editCareer(careerId, editedCareer);
                        }
                    });
                }
                else {
                    careerAddButton.setBackground(getResources().getDrawable(R.drawable.gray_square));
                    careerAddButton.setOnClickListener(null);
                }
            }
            @Override
            public void afterTextChanged(Editable arg0) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        };
    }
    private void getCareerDetail(String id){
        Singleton.retrofit.getCareerDetail(id).enqueue(new Callback<careerClass>() {
            @Override
            public void onResponse(Call<careerClass> call, Response<careerClass> response) {
                if (response.isSuccessful()){
                    if (response.code()==200) {
                        careerTitle.setText(response.body().getJob());
                        careerCompany.setText(response.body().getName());
                        careerTime.setText(response.body().getPeriodNum());
                        switch(response.body().getPeriodUnit()) {
                            case "주":
                                careerSpinner.setSelection(0);
                                break;
                            case "개월":
                                careerSpinner.setSelection(1);
                                break;
                            case "년":
                                careerSpinner.setSelection(2);
                        }
                    }
                    else
                        Log.w(TAG, String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<careerClass> call, Throwable t) {
                Log.w(TAG,"OnFailure");
            }
        });
    }

    private void editCareer(String id, careerClass body){
        Singleton.retrofit.editCareer(id, body).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    if (response.code()==201) {
                        Fragment next = Career_Main_Fragment.newInstance();
                        ((CareerActivity)getActivity()).replaceFragment(next);
                    }
                    else
                        Log.w(TAG, String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.w(TAG,"OnFailure");
            }
        });
    }
}
