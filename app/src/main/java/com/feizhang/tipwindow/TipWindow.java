package com.feizhang.tipwindow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class TipWindow extends PopupWindow{
    private View mContentView;
    private TextView mTipText;

    @SuppressLint("InflateParams")
    public TipWindow(Context context){
        super(context);

        mContentView = LayoutInflater.from(context).inflate(R.layout.tip_window, null);
        mTipText = mContentView.findViewById(R.id.tipText);
        mTipText.setBackgroundDrawable(buildBackground(context));

        setFocusable(false);
        setTouchable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable());
        setContentView(mContentView);
    }

    public void show(final View anchorView, final String tip){
        mTipText.setText(tip);
        invalidate();

        getContentView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                autoAdjustArrowPos(anchorView);
                mContentView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        showAsDropDown(anchorView);
    }

    private void invalidate(){
        mContentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        setHeight(mContentView.getMeasuredHeight());
        setWidth(mContentView.getMeasuredWidth());
    }

    private void autoAdjustArrowPos(View anchorView) {
        View upArrow = mContentView.findViewById(R.id.upArrow);
        View downArrow = mContentView.findViewById(R.id.downArrow);

        // 获取contentView的坐标
        int[] position = new int[2];
        mContentView.getLocationOnScreen(position);

        // 弹框左边坐标
        int popLeftPosition = position[0];

        // 获取anchorView的坐标
        anchorView.getLocationOnScreen(position);

        // anchorView左边坐标
        int anchorLeftPosition = position[0];

        int arrowLeftMargin = anchorLeftPosition - popLeftPosition + anchorView.getWidth() / 2 - upArrow.getWidth() / 2;
        upArrow.setVisibility(isAboveAnchor() ? View.INVISIBLE : View.VISIBLE);
        downArrow.setVisibility(isAboveAnchor() ? View.VISIBLE : View.INVISIBLE);

        LinearLayout.LayoutParams upArrowParams = (LinearLayout.LayoutParams) upArrow.getLayoutParams();
        upArrowParams.leftMargin = arrowLeftMargin;
        LinearLayout.LayoutParams downArrowParams = (LinearLayout.LayoutParams) downArrow.getLayoutParams();
        downArrowParams.leftMargin = arrowLeftMargin;
        upArrow.setLayoutParams(upArrowParams);
        downArrow.setLayoutParams(downArrowParams);
    }

    private Drawable buildBackground(Context context){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(ContextCompat.getColor(context, android.R.color.holo_orange_light));
        drawable.setCornerRadius(10);
        return drawable;
    }

}
