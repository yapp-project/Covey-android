package org.yapp.covey.etc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import org.yapp.covey.R;

public class CustomAppBar{
    private Context context;
    private ActionBar actionBar;
    private ImageButton btnAdd;

    private backClickListener backClickListener = null;
    private addClickListener addClickListener = null;

    public interface backClickListener{
        void onBackClick(View v);
    }

    public interface addClickListener{
        void onAddClick(View v);
    }

    public void setBackClickListener(backClickListener backClickListener) {
        this.backClickListener = backClickListener;
    }

    public void setAddClickListener(addClickListener addClickListener){
        this.addClickListener = addClickListener;
    }

    public void setAddVisible(){
        btnAdd.setVisibility(View.VISIBLE);
    }

    public CustomAppBar(Context context, ActionBar actionBar){
        this.context = context;
        this.actionBar = actionBar;
    }

    public void setCustomAppBar(String title){

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        View mCustomView = LayoutInflater.from(context).inflate(R.layout.layout_custom_app_bar,null);
        TextView tvTitle= mCustomView.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        ImageButton btnBack = mCustomView.findViewById(R.id.iv_back);
        btnAdd = mCustomView.findViewById(R.id.iv_add);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (backClickListener != null){
                      backClickListener.onBackClick(view);
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addClickListener != null){
                    addClickListener.onAddClick(view);
                }
            }
        });

        actionBar.setCustomView(mCustomView);
        actionBar.setElevation(0);

        Toolbar parent = (Toolbar) mCustomView.getParent();
        parent.setContentInsetsAbsolute(0,0);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setCustomView(mCustomView,params);
    }

}
