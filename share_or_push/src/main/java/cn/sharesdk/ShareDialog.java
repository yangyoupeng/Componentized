package cn.sharesdk;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maoliicai.share_or_push.R;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * @author mumuji
 * @version 1.0.0
 * @date 2018/5/22
 * @description
 */

public class ShareDialog extends Dialog {

    private Context mContext;
    private ImageView mIvShareQq, mIvShareQzone, mIvShareWechat, mIvShareWechatMoments;
    private TextView mTvDelete;
    private String mTitle, mTitleUrl, mDescription, mImgUrl, mSite;
    private Bitmap mImgData;
    private OnCountShareWxListener mListener;
    private boolean mIsInviteShare;//是否是邀请好友的分享

    public ShareDialog(Context context, String title, String titleUrl, String description, Bitmap imgData, String site) {
        this(context, title, titleUrl, description, imgData, site, null);
        this.mIsInviteShare = false;
    }

    public ShareDialog(Context context, String title, String titleUrl, String description, Bitmap imgData, String site, OnCountShareWxListener listener) {
        super(context, R.style.ShareDialog);
        this.mContext = context;
        this.mTitle = title;
        this.mTitleUrl = titleUrl;
        this.mDescription = description;
        this.mImgData = imgData;
        this.mSite = site;
        this.mListener = listener;
        this.mIsInviteShare = true;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.popview_share, null, false);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        addContentView(view, layoutParams);

        Window window = this.getWindow();
        WindowManager m = window.getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = window.getAttributes();
        p.width = d.getWidth();
        p.gravity = Gravity.BOTTOM;
        window.setAttributes(p);
        initView();
    }

    private void initView() {
        mIvShareQq = findViewById(R.id.iv_share_qq);
        mIvShareQzone = findViewById(R.id.iv_share_qzone);
        mIvShareWechat = findViewById(R.id.iv_share_wechat);
        mIvShareWechatMoments = findViewById(R.id.iv_share_wechat_moments);
        mTvDelete = findViewById(R.id.tv_delete);

        //分享到QQ好友
        mIvShareQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();

                Platform.ShareParams qq = new Platform.ShareParams();
                qq.setTitle(mTitle);
                qq.setTitleUrl(mTitleUrl);
                qq.setText(mDescription);
                qq.setImageData(mImgData);
//                qq.setImageUrl(mImgUrl);//http://wap.ibrjf.com/favicon.png
                qq.setSite(mSite);
                qq.setSiteUrl(mTitleUrl);

                share(QQ.NAME, qq);
            }
        });
        //分享到QQ空间
        mIvShareQzone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Platform.ShareParams qZone = new Platform.ShareParams();
                qZone.setTitle(mTitle);
                qZone.setTitleUrl(mTitleUrl);
                qZone.setText(mDescription);
                qZone.setImageData(mImgData);
                qZone.setSite(mSite);// site是分享此内容的网站名称，仅在QQ空间使用,一般为app_name
                qZone.setSiteUrl(mTitleUrl);

                share(QZone.NAME, qZone);

                dismiss();
            }
        });
        //分享到微信好友
        mIvShareWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Wechat.ShareParams swx = new Wechat.ShareParams();
                swx.setShareType(Platform.SHARE_WEBPAGE);
                swx.setTitle(mTitle);
                swx.setText(mDescription);
                swx.setImageData(mImgData);
                swx.setUrl(mTitleUrl);

                share(Wechat.NAME, swx);

                dismiss();
            }
        });
        //分享到微信朋友圈
        mIvShareWechatMoments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Platform.ShareParams pyq = new Platform.ShareParams();
                pyq.setShareType(Platform.SHARE_WEBPAGE);
                pyq.setTitle(mTitle);
                pyq.setText(mDescription);
                pyq.setImageData(mImgData);
                pyq.setUrl(mTitleUrl);
                share(WechatMoments.NAME, pyq);

                dismiss();
            }
        });
        mTvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    /**
     * 分享回调方法
     * @param name
     * @param sp
     */
    private void share(String name, Platform.ShareParams sp) {
        Platform pl = ShareSDK.getPlatform(name);
        // 设置分享事件回调
        pl.setPlatformActionListener(new PlatformActionListener() {
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                //失败的回调，arg:平台对象，arg1:表示当前的动作，arg2:异常信息
                Toast.makeText(mContext, "分享失败", Toast.LENGTH_SHORT).show();
            }

            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                //分享成功的回调
                if(mIsInviteShare){//邀请好友的分享 不提示
                    if(mListener != null){
                        mListener.getShareWx();
                    }
                } else {
                    Toast.makeText(mContext, "分享成功", Toast.LENGTH_SHORT).show();
                }
            }

            public void onCancel(Platform arg0, int arg1) {
                //取消分享的回调
                Toast.makeText(mContext, "分享取消", Toast.LENGTH_SHORT).show();
            }
        });
        // 执行图文分享
        pl.share(sp);
    }

    public interface OnCountShareWxListener{
        void getShareWx();
    }

}
