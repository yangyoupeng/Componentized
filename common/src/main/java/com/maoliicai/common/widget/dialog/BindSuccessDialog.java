package com.maoliicai.common.widget.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maoliicai.common.R;
import com.maoliicai.common.widget.RoundRectImageView;

/**
 * @author mumuji
 * @version 1.0.0
 * @date 2018/5/12
 * @description
 */

public class BindSuccessDialog extends Dialog{
    private static final String TAG = "BindSuccessDialog";
    private RoundRectImageView mImageView;
    private TextView mTvToTicket, mTvToInvest;
    private Context context;
    private String mImgUrl;
    private Callback mCallback;
    private String mLeft, mRight;

    public BindSuccessDialog(Context context, String imgUrl, String left, String right) {

        super(context, R.style.Dialog);
        this.context = context;
        this.mImgUrl = imgUrl;
        this.mLeft = left;
        this.mRight = right;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bind_success);
        initView();
        initListen();

    }

    @SuppressLint("WrongViewCast")
    private void initView() {
        mImageView = findViewById(R.id.imageView);
        mTvToTicket = findViewById(R.id.tv_to_ticket);
        mTvToInvest = findViewById(R.id.tv_to_invest);
        if(!TextUtils.isEmpty(mLeft)){
            mTvToTicket.setText(mLeft);
        }
        if(!TextUtils.isEmpty(mRight)){
            mTvToInvest.setText(mRight);
        }
        Log.d(TAG, "initView: "+mImgUrl);
        Glide.with(context).load(mImgUrl).error(R.mipmap.icon_bind_success_default).into(mImageView);
    }

    private void initListen() {
        mTvToTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                mCallback.toTicket();
            }
        });
        mTvToInvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                mCallback.toInvest();
            }
        });
    }

    public BindSuccessDialog setCallback(Callback callback) {
        mCallback = callback;
        return this;
    }

    public interface Callback {
        void toTicket();

        void toInvest();
    }
}
