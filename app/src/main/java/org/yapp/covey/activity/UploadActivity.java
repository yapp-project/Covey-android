package org.yapp.covey.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yongbeom.aircalendar.core.AirCalendarIntent;

import org.yapp.covey.R;
import org.yapp.covey.adapter.AdapterCustomSpinner;
import org.yapp.covey.adapter.AdapterUploadImageList;
import org.yapp.covey.databinding.ActivityUploadBinding;
import org.yapp.covey.etc.CustomAppBar;
import org.yapp.covey.helper.PermissionHelper;
import org.yapp.covey.helper.TextWatchHelper;
import org.yapp.covey.util.Singleton;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import gun0912.tedimagepicker.builder.TedImagePicker;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadActivity extends AppCompatActivity{
    ActivityUploadBinding binding;
    AdapterCustomSpinner mAdapterSpinner, mHourAdapterSpinner;
    AdapterUploadImageList mAdapterImageList = new AdapterUploadImageList();
    PermissionHelper permissionHelper = new PermissionHelper(this);

//    TextWatchHelper textWatchHelper = new TextWatchHelper();

    private String startDate;
    private String endDate;
    private String selectDate;
    private String selectAddress;
    private String title;
    private String description = "";
    private String category;
    private String startTime;
    private String startHour;
    private String startMin;
    private String endHour;
    private String endMin;
    private String endTime;

    private ArrayList<RequestBody> requestBodies = new ArrayList<>();

    private final static String TAG = "UPLOAD_SERVER_ERROR";
    private int payment;

    private static int REQUEST_CODE = 202;
    private static int REQUEST_CODE_ADDRESS = 204;
    private boolean isTextExisted = false;

    ArrayList<String> hourArray = new ArrayList<>();
    ArrayList<String> minArray = new ArrayList<>();
    ArrayList<String> categoryArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_upload);

        setHourArray(hourArray);
        setMinArray(minArray);

        categoryArray.addAll(Arrays.asList(getResources().getStringArray(R.array.category)));
        categoryArray.add("카테고리");

        setSpinnerSet();
        setCustomAppBar();
        getPermission();

        binding.recyclerUploadImage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        binding.tvSelectAddress.setOnClickListener(view -> {
            Intent intentAddress = new Intent(getApplicationContext(), SearchAddressActivity.class);
            startActivityForResult(intentAddress, REQUEST_CODE_ADDRESS);
        });
        binding.btnUpload.setOnClickListener(view -> sendUploadData());
    }

    private void setCustomAppBar(){
        CustomAppBar customAppBar = new CustomAppBar(this, getSupportActionBar());
        customAppBar.setCustomAppBar("대타 등록하기");
        customAppBar.setBackClickListener(v -> finish());
    }

    private void getPermission(){
        permissionHelper.getPermission(100);
    }

    private void setSpinnerSet(){
        // set spinner adapter, data
        setSpinner(binding.spinnerCategory, categoryArray);
        setSpinnerHour(binding.spinnerStartTimeHour, hourArray);
        setSpinner(binding.spinnerStartTimeMin, minArray);
        setSpinner(binding.spinnerEndTimeHour, hourArray);
        setSpinner(binding.spinnerEndTimeMin, minArray);

        // set spinner item selected Listener
        setSpinnerSelectedListener(binding.spinnerCategory, 1);
        setSpinnerSelectedListener(binding.spinnerStartTimeHour, 2);
        setSpinnerSelectedListener(binding.spinnerStartTimeMin,3);
        setSpinnerSelectedListener(binding.spinnerEndTimeHour, 4);
        setSpinnerSelectedListener(binding.spinnerEndTimeMin,5);
    }

    private void setSpinner(Spinner spinner, ArrayList<String> spinnerData){
        mAdapterSpinner = new AdapterCustomSpinner(this,spinnerData);
        spinner.setAdapter(mAdapterSpinner);
        spinner.setSelection(spinnerData.size()-1);
    }

    private void setSpinnerHour(Spinner spinnerHour, ArrayList<String> spinnerData){
        mHourAdapterSpinner = new AdapterCustomSpinner(this, spinnerData);
        spinnerHour.setAdapter(mHourAdapterSpinner);
        spinnerHour.setSelection(spinnerData.size()-1);
    }

    private void setHourArray(List<String> hourArray){
        for (int i = 0 ; i<= 24; i++){
            if (i<10){
                hourArray.add("0" + i);
            }else{
                hourArray.add(String.valueOf(i));
            }
        }
        hourArray.add("시");
    }

    private void setMinArray(List<String> minArray){
        minArray.add("00");
        for (int i = 1 ; i<= 5 ; i++){
            minArray.add(String.valueOf(i*10));
        }
        minArray.add("분");
    }

    public void setSpinnerSelectedListener(Spinner selectedSpinner, int type){
        if (type == 2 || type == 4){
            selectedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    switch (type){
                        case 2: startHour = mHourAdapterSpinner.getItem(position); break;
                        case 4: endHour = mHourAdapterSpinner.getItem(position); break;
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }else if(type == 3 || type == 5){
            selectedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    switch (type){
                        case 3: startMin = minArray.get(position);
                        case 5: endMin = minArray.get(position);
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
        else{
            selectedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    category = categoryArray.get(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    public void showDatePicker(){
        AirCalendarIntent intent = new AirCalendarIntent(this);
        intent.isBooking(false);
        intent.isSelect(false);
        intent.isMonthLabels(false);
        intent.setSelectButtonText("선택");
        intent.setResetBtnText("리셋");
        intent.setWeekStart(Calendar.MONDAY);
        intent.setWeekDaysLanguage(AirCalendarIntent.Language.EN);

        ArrayList<String> weekDay = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.week)));
        intent.setCustomWeekDays(weekDay);

        startActivityForResult(intent, REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if(requestCode == REQUEST_CODE && data != null){
                startDate = data.getStringExtra(CustomCalendarActivity.RESULT_SELECT_START_DATE);
                endDate = data.getStringExtra(CustomCalendarActivity.RESULT_SELECT_END_DATE);
                binding.tvPostDateStart.setText(startDate);
                binding.tvPostDateEnd.setText(endDate);
            }
            else if(requestCode == REQUEST_CODE_ADDRESS){
                assert data != null;
                selectAddress = data.getStringExtra("select Data");
                binding.tvSelectAddress.setText(selectAddress);
            }
        }
    }

    public void dateSelect(View view) {
        showDatePicker();
    }

    public void addImage(View view) {
        TedImagePicker.with(this)
                .cameraTileBackground(R.color.salmon)
                .buttonBackground(R.color.tomato)
                .mediaType(gun0912.tedimagepicker.builder.type.MediaType.IMAGE)
                .max(3-mAdapterImageList.getItemCount(), "최대 3장까지 선택 가능합니다")
                .startMultiImage(list -> {
                    mAdapterImageList.addUriList((ArrayList<Uri>) list);
                });
        binding.recyclerUploadImage.setAdapter(mAdapterImageList);
    }

    public void sendUploadData(){
        getUploadData();
        setRequestBody();

        ArrayList<Uri> uriList = mAdapterImageList.getUriList();
        ArrayList<MultipartBody.Part> multipartData = new ArrayList<>();
        for (int i = 0 ; i < uriList.size() ; i++){
            File file = new File(uriList.get(i).getPath());
            RequestBody fileReqBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("img"+(i+1), file.getName(), fileReqBody);
            multipartData.add(part);
        }

        if (multipartData.size()<3){
            Toast.makeText(this,"사진이 없어요",Toast.LENGTH_SHORT).show();
        }else {
            Singleton.retrofit.upload(requestBodies.get(0), requestBodies.get(1), requestBodies.get(2), requestBodies.get(3)
                    , true
                    , requestBodies.get(4)
                    , requestBodies.get(5), requestBodies.get(6), requestBodies.get(7)
                    , payment, requestBodies.get(8), requestBodies.get(9)
                    , multipartData.get(0), multipartData.get(1), multipartData.get(2))
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                switch (response.code()) {
                                    case 201: {
                                        Toast.makeText(getApplicationContext(), "대타 등록 성공", Toast.LENGTH_SHORT).show();
                                        finish();
                                        break;
                                    }
                                    case 404: {
                                        Log.d(TAG, "NotFound");
                                        break;
                                    }
                                    case 409: {
                                        Toast.makeText(getApplicationContext(), "게시글 제목을 바꿔주세요", Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                }
                            }
                            Log.w("TAG", String.valueOf(response.code()));
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.d("Upload Error", "onFailure" + t);
                        }
                    });
        }
    }

    private void getUploadData(){
        title = binding.editTitle.getText().toString();
        description = binding.editDescription.getText().toString();
        payment = Integer.parseInt(binding.editPayment.getText().toString());

        startTime = startHour +":"+startMin;
        endTime = endHour +":"+endMin;

        Log.w("TAG",startTime + endTime);
    }

    private void setRequestBody(){
        String[] addressString = selectAddress.split(" ");
        String addressDetail = "";
        for (int i = 2 ; i< addressString.length ; i++){
            addressDetail = addressDetail + addressString[i];
        }

        // type convert requestBody
        requestBodies.add(changeRequestBody(title));
        requestBodies.add(changeRequestBody(startDate));
        requestBodies.add(changeRequestBody(endDate));
        requestBodies.add(changeRequestBody(endDate));
        requestBodies.add(changeRequestBody(startTime + "~" + endTime));
        requestBodies.add(changeRequestBody(addressString[0]));
        requestBodies.add(changeRequestBody(addressString[1]));
        requestBodies.add(changeRequestBody(addressDetail));
        requestBodies.add(changeRequestBody(description));
        if (category.length() == 0){
            requestBodies.add(changeRequestBody("기타"));
        }else   requestBodies.add(changeRequestBody(category));
    }
    private RequestBody changeRequestBody(String text){
        return RequestBody.create(MediaType.parse("text/plain"),text);
    }

//    private void setTextWatchHelper(EditText editText){
//        editText.addTextChangedListener(textWatchHelper);
//    }
}
