package org.yapp.covey.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.yapp.covey.R;
import org.yapp.covey.adapter.AdapterCategoryList;
import org.yapp.covey.etc.CategoryData;
import org.yapp.covey.etc.ItemDecorationCategory;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeFragment extends Fragment {
    ImageButton buttonAlert, buttonFilter;
    TextView tvCome, tvTitle;
    private RecyclerView listViewCategory;
    private AdapterCategoryList adapterCategory;
    private ArrayList<String> mArrayData = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        setInitView(rootView);
        setTitle("커비에서 빠르고 편리한\n" + "알바 대타를 경험해보세요!");

        mArrayData.addAll(Arrays.asList(CategoryData.sData));

        adapterCategory = new AdapterCategoryList(getContext(),mArrayData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()
                ,LinearLayoutManager.HORIZONTAL,false);
        listViewCategory.setLayoutManager(linearLayoutManager);
        listViewCategory.addItemDecoration(new ItemDecorationCategory(getContext(),8f));
        listViewCategory.setAdapter(adapterCategory);

        return rootView;
    }
    private void setInitView(View view){
        buttonAlert = view.findViewById(R.id.btn_alert);
        buttonFilter = view.findViewById(R.id.btn_filter);
        tvCome = view.findViewById(R.id.btn_come);
        tvTitle = view.findViewById(R.id.tv_title);

        listViewCategory = view.findViewById(R.id.recycler_category);
    }

    private void setTitle(String title){
        SpannableStringBuilder builder = new SpannableStringBuilder(title);
        builder.setSpan(new StyleSpan(Typeface.BOLD),0,2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new StyleSpan(Typeface.BOLD),5,18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTitle.append(builder);
    }
}
