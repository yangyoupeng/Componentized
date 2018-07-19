package com.maoliicai.common.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.maoliicai.common.R;
import com.maoliicai.common.widget.dialog.gridPasswordView.GridPasswordView;


/**
 * Created by lauyk on 2016/8/12.
 */
public class PayDialog extends Dialog {

    private Context context;

    private ImageView mIvDismiss;
    private TextView mTvForgetPwd;
    private GridPasswordView pwdView;

    public PasswordChangedListener listener;

    public PayDialog(Context context) {
        super(context, R.style.PayDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pay);

        initView();
    }

    public void setPasswordChangedListener(PasswordChangedListener listener) {
        this.listener = listener;
    }

    private void initView() {
        mIvDismiss = (ImageView) findViewById(R.id.iv_dismiss);
//        mTvForgetPwd = (TextView) findViewById(R.id.tv_forget_pwd);
        pwdView = (GridPasswordView) findViewById(R.id.pwd);

        //dialog弹出时弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        mIvDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        pwdView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onChanged(String psw) {
                listener.onChanged(psw);
            }

            @Override
            public void onMaxLength(String psw) {
                listener.onMaxLength(psw);
            }
        });

//        mTvForgetPwd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // context.startActivity(new Intent(context, InitialPayActivity.class));
//            }
//        });

        Window dialogWindow = getWindow();
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        dialogWindow.setAttributes(lp);
        dialogWindow.setGravity(Gravity.BOTTOM);

    }

    public PayDialog clear() {

        if (pwdView != null)
            pwdView.clearPassword();
        return this;
    }

    public interface PasswordChangedListener {
        void onChanged(String pwd);

        void onMaxLength(String pwd);

    }


}


