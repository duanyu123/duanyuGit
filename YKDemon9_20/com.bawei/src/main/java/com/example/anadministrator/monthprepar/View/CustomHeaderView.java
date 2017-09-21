package com.example.anadministrator.monthprepar.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.anadministrator.monthprepar.R;

/**
 * Created by 张祺钒
 * on2017/9/20.
 */

public class CustomHeaderView extends RelativeLayout {

    private int titleBgColor;
    private int titleTextColor;
    private String titleText;
    private TextView tvTitle;

    public CustomHeaderView(Context context) {
        this(context, null);
    }

    public CustomHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypedArray(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.header, this, true);
        tvTitle = findViewById(R.id.tvTitle);
        RelativeLayout relat = findViewById(R.id.relat);
        ImageView imageLeft = findViewById(R.id.imageLeft);
        ImageView imageRight = findViewById(R.id.imageRight);
        relat.setBackgroundColor(titleBgColor);
        tvTitle.setText(titleText);
        tvTitle.setTextColor(titleTextColor);
    }

    /**
     * 获取自定义资源
     */
    private void initTypedArray(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        titleBgColor = typedArray.getColor(R.styleable.TitleBar_titleBgColor, Color.argb(0, 52, 204, 153));
        titleTextColor = typedArray.getColor(R.styleable.TitleBar_titleTextColor, Color.WHITE);
        titleText = typedArray.getString(R.styleable.TitleBar_titleText);
        //回收资源
        typedArray.recycle();
    }

//    public void setTitleText(String titleText) {
//        if(titleText!=null && !titleText.equals(""))
//        this.titleText = titleText;
//    }

}
