package com.alguojian.flexboxlayoutdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;

import static com.google.android.flexbox.AlignSelf.FLEX_START;
import static com.google.android.flexbox.FlexDirection.ROW;
import static com.google.android.flexbox.FlexWrap.WRAP;

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
        flexBoxLayout.setFlexWrap(WRAP);

        //设置元素主轴方向上的对齐方式
        flexBoxLayout.setJustifyContent(FLEX_START);

        //设置水平方向分割线
        flexBoxLayout.setShowDividerHorizontal(DividerItemDecoration.VERTICAL);

        for (int i = 0; i < 15; i++) {

            flexBoxLayout.addView(getItemView("just贝贝h喀"));
        }

    }

    private TextView getItemView(final String string) {

        textView = new TextView(this);
        textView.setText(string);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(13, 5, 13, 5);
        textView.setTextColor(Color.BLUE);
        textView.setBackgroundColor(Color.YELLOW);
        //设置点击事件
        textView.setClickable(true);

        //设置外间距
        FlexboxLayout.LayoutParams params1 = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.setMargins(dpToPx(14),dpToPx(6),dpToPx(6),dpToPx(14));
        textView.setLayoutParams(params1);

        //添加点击事件
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();

            }
        });

        return textView;

    }

    /**
     * px转dp工具类
     *
     * @param pixel
     * @return
     */
    public int pxToDp(int pixel) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return pixel < 0 ? pixel : Math.round(pixel / displayMetrics.density);
    }

    /**
     * dp转px工具类
     *
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
