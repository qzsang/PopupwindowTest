package com.qzsang.popupwindowtest;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class MainActivity extends Activity {
    LinearLayout ll_btns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_btns = (LinearLayout) findViewById(R.id.ll_btns);
        for (int i = 0;i < ll_btns.getChildCount(); i++) {
            ll_btns.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupWindow(view);
                }
            });
        }
    }


    private PopupWindow popupWindow = null;
    private PopupWindow getPopupWindow () {
        if (popupWindow == null) {
            // 一个自定义的布局，作为显示的内容
            View contentView = LayoutInflater.from(this).inflate(
                    R.layout.view_pp_content, null);
            contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            // 设置按钮的点击事件
//        Button button = (Button) contentView.findViewById(R.id.btn_click);
//        button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "button is pressed",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

            popupWindow = new PopupWindow(contentView,
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

//        popupWindow.setTouchable(true);
//
//        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                Log.i("mengdd", "onTouch : ");
//
//                return false;
//                // 这里如果返回true的话，touch事件将被拦截
//                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
//            }
//        });

            // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
            // 我觉得这里是API的一个bug
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
        }

        return popupWindow;
    }

    private void showPopupWindow(View view) {
        PopupWindow popupWindow = getPopupWindow();
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int offsetLeft = (popupWindow.getContentView().getMeasuredWidth() - view.getWidth()) / 2;
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,location[0] - offsetLeft, location[1] - popupWindow.getContentView().getMeasuredHeight());

    }

}
