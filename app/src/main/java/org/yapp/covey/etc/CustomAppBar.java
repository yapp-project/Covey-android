package org.yapp.covey.etc;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import org.yapp.covey.R;

public class CustomAppBar{
    private Activity activity;
    private ActionBar actionBar;

    public CustomAppBar(Activity activity, ActionBar actionBar){
        this.activity = activity;
        this.actionBar = actionBar;
    }

    public void setCustomAppBar(String title){

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        View mCustomView = LayoutInflater.from(activity).inflate(R.layout.layout_title_bar,null);
        TextView tvTitle= mCustomView.findViewById(R.id.tv_title);
        tvTitle.setText(title);

        actionBar.setCustomView(mCustomView);
        actionBar.setElevation(0);

        Toolbar parent = (Toolbar) mCustomView.getParent();
        parent.setContentInsetsAbsolute(0,0);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setCustomView(mCustomView,params);
    }
}
