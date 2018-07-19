package com.maoliicai.module_launch.base;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

import com.maoliicai.common.base.BaseMvpActivity;
import com.maoliicai.common.base.BasePresenter;
import com.maoliicai.common.base.IBaseView;
import com.maoliicai.common.utils.DownLoadUtil;
import com.maoliicai.common.utils.StatusBarUtils;
import com.maoliicai.common.utils.ToastUtils;
import com.maoliicai.common.utils.Utils;
import com.maoliicai.module_launch.R;

import java.io.File;

/**
 * yangyoupeng  on 2018/5/3.
 * <p>
 * 当前activity  创建  主要是用来适配FrameworkActivity当中的HomeFragment沉趁式透明状态栏
 */

public abstract class AdapterStatusActivity<V extends IBaseView, P extends BasePresenter<V>> extends BaseMvpActivity<V, P>  {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置沉浸式，透明状态栏,针对一个activity对多个fragment
        StatusBarUtils.setBarColorFragment(this, Color.TRANSPARENT);
    }

    /**
     * 添加fragment
     */
    protected void addFragment(Fragment fragment, @IdRes int frameId) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager()
                .beginTransaction()
                .add(frameId, fragment, fragment.getClass().getSimpleName())
                .show(fragment)
                .commit();
    }

    /**
     * 替换fragment
     */
    protected void replaceFragment(Fragment fragment, @IdRes int frameId) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(frameId, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    /**
     * 显示fragment
     */
    protected void showFragment(Fragment fragment) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager()
                .beginTransaction()
                .show(fragment)
                .commitAllowingStateLoss();
    }

    /**
     * 隐藏fragment
     */
    protected void hideFragment(Fragment fragment) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager()
                .beginTransaction()
                .hide(fragment)
                .commitAllowingStateLoss();
    }

    /**
     * 移出fragment
     */
    protected void removeFragment(Fragment fragment) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager()
                .beginTransaction()
                .remove(fragment)
                .commitAllowingStateLoss();
    }

    /**
     * 弹出栈顶部的Fragment
     */
    protected void popFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    /**
     * 下载文件
     *
     * @param url
     */
//    protected ProgressListener mProgressListener;
    private NotificationManager mNotificationManager;
    private Notification mNotification;
    private String mApkFile;
    public void downLoadFile(String url, final String fileName, ProgressListener progressListener) {
        DownLoadUtil.downloadFile(this, url, new DownLoadUtil.DownLoadListener() {
            @Override
            public void download(long fileSize, long downLoadFileSize) {
                showNotification(fileSize, downLoadFileSize, fileName);
                if (null != progressListener) {
                    progressListener.toUpdateProgress(fileSize, downLoadFileSize);
                }
            }

            @Override
            public void downloadComplete(long fileSize, long downLoadFileSize, String apkFile) {
                if (null != mNotificationManager) {
                    mNotificationManager.cancel(NOTIFICATION_ID);
                }
                mApkFile = apkFile;
                Intent intent = getFileIntent(new File(apkFile));
                startActivity(intent);
            }

            @Override
            public void downloadFaile(boolean isConnect) {
                progressListener.toReDownLoad();
                if (!isConnect) {
                    ToastUtils.showLongToast("请检查网络后，重新下载");
                }
                if (null != mNotificationManager) {
                    mNotificationManager.cancel(NOTIFICATION_ID);
                }
            }
        });
    }

    private static final int NOTIFICATION_ID = 9999;
    private boolean mIsStopDownLoad = false;
    private RemoteViews rv;
    private void showNotification(long fileSize, long downLoadFileSize, String fileName) {
        if (mIsStopDownLoad) {
            if (null != mNotificationManager) {
                mNotificationManager.cancel(NOTIFICATION_ID);
            }
        } else {
            mNotificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
            mNotification = new Notification();
            mNotification.icon = R.mipmap.ic_logo_application;
//                    mNotification.largeIcon=BitmapFactory.decodeResource(getResources(), R.drawable.logo);
            mNotification.tickerText = "开始下载";

            rv = new RemoteViews(getPackageName(), R.layout.notification_download_update);
            rv.setImageViewResource(R.id.iv_logo, R.mipmap.ic_logo_application);
            if (!TextUtils.isEmpty(fileName)) {
                rv.setTextViewText(R.id.txt_notice, "正在下载" + fileName + "，已完成" + (downLoadFileSize * 100 / fileSize) + "%");
            } else {
                rv.setTextViewText(R.id.txt_notice, "正在下载文件，已完成" + (downLoadFileSize * 100 / fileSize) + "%");
            }
            int progress = (int) (downLoadFileSize * 100 / fileSize);
            rv.setProgressBar(R.id.progBar, 100, progress, false);
            mNotification.contentView = rv;

            Intent i = new Intent();
            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, 0);
            mNotification.contentIntent = pendingIntent;
            mNotificationManager.notify(NOTIFICATION_ID, mNotification);
        }
    }

    public interface ProgressListener {
        void toUpdateProgress(long fileSize, long downLoadFileSize);

        void toReDownLoad();
    }

    private Intent getFileIntent(File file) {
        Uri uri = Uri.fromFile(file);
        String type = getMIMEType(file);
        Log.i("tag", "type=" + type);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, type);
        return intent;
    }
    private String getMIMEType(File f) {
        String type = "";
        String fName = f.getName();
      /* 取得扩展名 */
        String end = fName.substring(fName.lastIndexOf(".") + 1, fName.length()).toLowerCase();

      /* 依扩展名的类型决定MimeType */
        if (end.equals("pdf")) {
            type = "application/pdf";//
        } else if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") ||
                end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            type = "audio/*";
        } else if (end.equals("3gp") || end.equals("mp4")) {
            type = "video/*";
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") ||
                end.equals("jpeg") || end.equals("bmp")) {
            type = "image/*";
        } else if (end.equals("apk")) {
        /* android.permission.INSTALL_PACKAGES */
            type = "application/vnd.android.package-archive";
        } else if (end.equals("pptx") || end.equals("ppt")) {
            type = "application/vnd.ms-powerpoint";
        } else if (end.equals("docx") || end.equals("doc")) {
            type = "application/vnd.ms-word";
        } else if (end.equals("xlsx") || end.equals("xls")) {
            type = "application/vnd.ms-excel";
        } else {
//        /*如果无法直接打开，就跳出软件列表给用户选择 */
            type = "*/*";
        }
        return type;
    }

    public void open() {
        Intent intent = getFileIntent(new File(mApkFile));
        startActivity(intent);
    }
    public void stop() {
        DownLoadUtil.isStop(true);
        mIsStopDownLoad = true;
    }

    public void start() {
        DownLoadUtil.isStop(false);
        mIsStopDownLoad = false;
    }
}
