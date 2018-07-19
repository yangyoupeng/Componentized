package com.maoliicai.common.utils;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.maoliicai.common.R;

/**
 * yangyoupeng  on 2018/5/16.
 */


public class DialogUtils {


    public static AlertDialog showDiaLog(Activity activity, Object obj, String[] str, View.OnClickListener closeListener, View.OnClickListener confirmListenter) {
        AlertDialog alertDialog = new AlertDialog.Builder(activity, R.style.dialog).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setWindowAnimations(R.style.dialog_style);
        window.setContentView(R.layout.dialog_default);

        TextView tv = window.findViewById(R.id.tv_content);
        tv.setVisibility(View.VISIBLE);
        tv.setText(obj.toString());

        TextView tvClose = window.findViewById(R.id.tv_close);
        tvClose.setText(str[0]);
        tvClose.setOnClickListener(closeListener);

        TextView tvConfirm = window.findViewById(R.id.tv_confirm);
        tvConfirm.setText(str[1]);
        tvConfirm.setOnClickListener(confirmListenter);

        alertDialog.setCanceledOnTouchOutside(false);//设置点击Dialog外部任意区域关闭Dialog

        return alertDialog;
    }


}
