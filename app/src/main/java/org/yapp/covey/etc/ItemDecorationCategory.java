package org.yapp.covey.etc;

import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemDecorationCategory extends RecyclerView.ItemDecoration{
    private int MARGIN_WIDTH;

    public ItemDecorationCategory(Context context, Float width) {
        MARGIN_WIDTH = dpToPx(context, width);
    }

    @Override
    public void getItemOffsets
            (@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);

        if(position == 0) {
            outRect.left = 0;
            outRect.right = MARGIN_WIDTH;
        } else if (position == 7){
            outRect.left = MARGIN_WIDTH;
            outRect.right = 0;

        }
        outRect.right = MARGIN_WIDTH;
        outRect.left = MARGIN_WIDTH;
    }

    // dp -> pixel 단위로 변경
    private int dpToPx(Context context, double dp) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) dp, context.getResources().getDisplayMetrics());
    }
}
