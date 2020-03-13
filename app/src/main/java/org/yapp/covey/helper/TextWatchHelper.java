package org.yapp.covey.helper;

import android.text.Editable;
import android.text.TextWatcher;

public class TextWatchHelper implements TextWatcher{
    private Boolean isExisted = false;

    public Boolean getExisted() {
        return isExisted;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        int length = editable.toString().length();
        isExisted = length > 0;
    }
}
