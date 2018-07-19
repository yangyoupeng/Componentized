package com.maoliicai.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maoliicai.common.R;
import com.maoliicai.common.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author mumuji
 * @version 1.0.0
 * @date 2018/4/12
 * @description
 */

public class EditTextLayout extends LinearLayout {

    @BindView(R2.id.image)
    ImageView mImage;
    @BindView(R2.id.et_content)
    EditText mEtContent;
    @BindView(R2.id.iv_clean)
    ImageView mIvClean;
    @BindView(R2.id.iv_pwd_show)
    ImageView mIvPwdShow;
    @BindView(R2.id.view)
    View mView;
    @BindView(R2.id.tv_code)
    TextView mTvCode;

    private final static int TYPE_COMMON = 0;//普通类型，什么都不限制
    private final static int TYPE_PHONE = 1;//手机号
    private final static int TYPE_PWD = 2;//密码
    private final static int TYPE_CODE = 3;//验证码
    private final static int TYPE_PAY_PWD = 4;//交易密码


    private OnContentChangedListener mOnContentChangedListener;
    private OnClickGetCodeListener mClickGetCodeListener;
    private Context mContext;
    private boolean mIsShowPwd, mIsPwdFocus;
    private int mImageId;
    private int mEditType;//输入类型
    private String mHintContent;
    private boolean mHideLine;

    public EditTextLayout(Context context) {
        this(context, null);
    }

    public EditTextLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditTextLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditTextLayout);
        mEditType = typedArray.getInteger(R.styleable.EditTextLayout_editType, TYPE_COMMON);
        mImageId = typedArray.getResourceId(R.styleable.EditTextLayout_imageContent, 0);
        mHintContent = typedArray.getString(R.styleable.EditTextLayout_hintContent);
        mHideLine = typedArray.getBoolean(R.styleable.EditTextLayout_hideBottomLine,false);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.my_edit_text, this, true);
        ButterKnife.bind(this, view);
        mImage.setImageResource(mImageId);
        mEtContent.setHint(mHintContent);
        if(mHideLine){
            mView.setVisibility(View.GONE);
        } else {
            mView.setVisibility(View.VISIBLE);
        }
        switch (mEditType) {
            case TYPE_COMMON://普通内容
                mIvPwdShow.setVisibility(View.GONE);
                mTvCode.setVisibility(View.GONE);

                break;
            case TYPE_PHONE://手机号
                mIvPwdShow.setVisibility(View.GONE);
                mTvCode.setVisibility(View.GONE);
                mEtContent.setInputType(InputType.TYPE_CLASS_NUMBER);
                mEtContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
                break;
            case TYPE_PWD://密码
                mIvPwdShow.setVisibility(View.VISIBLE);
                mTvCode.setVisibility(View.GONE);

                mIsShowPwd = false;
                mEtContent.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                mEtContent.setKeyListener(DigitsKeyListener.getInstance(mContext.getString(R.string.number_letter)));
                mEtContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
                mEtContent.setTransformationMethod(PasswordTransformationMethod.getInstance());
                mIvPwdShow.setImageResource(R.mipmap.icon_pwd_hide);
                break;
            case TYPE_CODE://验证码
                mIvPwdShow.setVisibility(View.GONE);
                mTvCode.setVisibility(View.VISIBLE);

                mEtContent.setInputType(InputType.TYPE_CLASS_NUMBER);
                mEtContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
                break;
            case TYPE_PAY_PWD:
                mIvPwdShow.setVisibility(View.VISIBLE);
                mTvCode.setVisibility(View.GONE);

                mIsShowPwd = false;
                mEtContent.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                mEtContent.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
                mEtContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
                mEtContent.setTransformationMethod(PasswordTransformationMethod.getInstance());
                mIvPwdShow.setImageResource(R.mipmap.icon_pwd_hide);
                break;
        }
        mEtContent.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mIsPwdFocus = hasFocus;
                if (hasFocus) {//聚焦
                    if (!TextUtils.isEmpty(mEtContent.getText())) {
                        mIvClean.setVisibility(View.VISIBLE);
                    } else {
                        mIvClean.setVisibility(View.GONE);
                    }
                    mView.setBackgroundResource(R.color.color_red_fb5a5c);
                } else {//失焦
                    mView.setBackgroundResource(R.color.color_e4);
                    mIvClean.setVisibility(View.GONE);
                }
            }
        });
        mEtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (null != mOnContentChangedListener) {
                    mOnContentChangedListener.getChangedContent(mEtContent.getText().toString().trim());
                }
                if (!TextUtils.isEmpty(mEtContent.getText().toString().trim()) && mIsPwdFocus) {
                    mIvClean.setVisibility(View.VISIBLE);
                } else {
                    mIvClean.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick({R2.id.iv_clean, R2.id.iv_pwd_show, R2.id.tv_code})
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.iv_clean) {
            mEtContent.setText("");

        } else if (i == R.id.iv_pwd_show) {
            if (mIsShowPwd) {//原来是显示密码，点击后改为隐藏密码
                mEtContent.setTransformationMethod(PasswordTransformationMethod.getInstance());
                mIvPwdShow.setImageResource(R.mipmap.icon_pwd_hide);
            } else {//原来是隐藏密码，点击后改为显示密码
                mEtContent.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                mIvPwdShow.setImageResource(R.mipmap.icon_pwd_show);
            }
            mEtContent.setSelection(mEtContent.getText().toString().trim().length());
            mIsShowPwd = !mIsShowPwd;

        } else if (i == R.id.tv_code) {
            if (null != mClickGetCodeListener) {
                mClickGetCodeListener.getCode();
            }

        }
    }

    public void setContent(String content) {
        mEtContent.setText(content);
    }

    public String getContent() {
        return mEtContent.getText().toString().trim();
    }

    public void setCode(String s) {
        mTvCode.setText(s);
    }

    public void setCodeClickable(boolean canClick) {
        mTvCode.setClickable(canClick);
        if (canClick) {//可点击时红色
            mTvCode.setTextColor(mContext.getResources().getColor(R.color.color_red_fb5a5c));
        } else {//不可点击时置灰
            mTvCode.setTextColor(mContext.getResources().getColor(R.color.color_c0));
        }
    }

    public interface OnContentChangedListener {
        void getChangedContent(String content);
    }

    public void setOnContentChangedListener(OnContentChangedListener listener) {
        this.mOnContentChangedListener = listener;
    }

    public interface OnClickGetCodeListener {
        void getCode();
    }

    public void setOnClickGetCodeListener(OnClickGetCodeListener listener) {
        this.mClickGetCodeListener = listener;
    }
}
