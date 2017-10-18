package com.alguojian.flexboxlayoutdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import static com.google.android.flexbox.AlignSelf.FLEX_END;
import static com.google.android.flexbox.AlignSelf.FLEX_START;
import static com.google.android.flexbox.FlexDirection.ROW;
import static com.google.android.flexbox.FlexWrap.WRAP_REVERSE;

public class MainActivity extends AppCompatActivity {

    protected FlexboxLayout flexBoxLayout;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
        init();
    }

    private void init() {

        //设置交叉轴起点对齐
        flexBoxLayout.setAlignContent(FLEX_START);

        //控制副轴的对齐方式
        flexBoxLayout.setAlignItems(FLEX_START);

        //设置主轴方向
        flexBoxLayout.setFlexDirection(ROW);

        //设置换行
        flexBoxLayout.setFlexWrap(WRAP_REVERSE);

        //设置元素主轴方向上的对齐方式
        flexBoxLayout.setJustifyContent(FLEX_END);

        //设置水平方向分割线
        flexBoxLayout.setShowDividerHorizontal(DividerItemDecoration.VERTICAL);

        for (int i = 0; i < 15; i++) {
            textView = new TextView(this);
            textView.setText(i + "just贝贝" + (i * i));
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(20, 5, 20, 5);
            textView.setTextColor(Color.BLACK);
            textView.setBackgroundColor(Color.YELLOW);
            flexBoxLayout.addView(textView);
        }
        ViewGroup.LayoutParams params = textView.getLayoutParams();

        if (params instanceof FlexboxLayout.LayoutParams) {
            FlexboxLayout.LayoutParams layoutParams = (FlexboxLayout.LayoutParams) params;
            //子view的属性
            layoutParams.setFlexBasisPercent(0.5f);
            layoutParams.setAlignSelf(FLEX_START);
            layoutParams.setMinHeight(dpToPx(20));
            layoutParams.setMinWidth(dpToPx(40));
        }
    }

    /**
     * px转dp工具类
     * @param pixel
     * @return
     */
    public int pxToDp(int pixel) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return pixel < 0 ? pixel : Math.round(pixel / displayMetrics.density);
    }

    /**
     * dp转px工具类
     * @param dp
     * @return
     */
    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return dp < 0 ? dp : Math.round(dp * displayMetrics.density);
    }

    private void initView() {
        flexBoxLayout = (FlexboxLayout) findViewById(R.id.flexBoxLayout);
    }
}
