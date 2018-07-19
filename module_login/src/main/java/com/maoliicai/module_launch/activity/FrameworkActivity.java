package com.maoliicai.module_launch.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.maoliicai.common.base.BaseFragment;
import com.maoliicai.common.base.RouterPath;
import com.maoliicai.common.mvp_senior.annotations.CreatePresenterAnnotation;
import com.maoliicai.module_launch.R;
import com.maoliicai.module_launch.R2;
import com.maoliicai.module_launch.base.AdapterStatusActivity;
import com.maoliicai.module_launch.contract.IFrameworkActivityContract;
import com.maoliicai.module_launch.presenter.FrameworkActivityPresenter;

import java.lang.reflect.Field;

import butterknife.BindView;

/**
 * yangyoupeng  on 2018/4/3.
 */

@SuppressLint("Registered")
@CreatePresenterAnnotation(FrameworkActivityPresenter.class)
@Route(path = RouterPath.FRAMWORK_ACTIVITY)
public class FrameworkActivity extends AdapterStatusActivity<IFrameworkActivityContract.View, FrameworkActivityPresenter>
        implements BottomNavigationView.OnNavigationItemSelectedListener, IFrameworkActivityContract.View {

    @BindView(R2.id.fragment_content)
    FrameLayout mFragmentContent;
    @BindView(R2.id.navigation)
    BottomNavigationView mNavigation;
    /**
     * fragment数组
     */
    private Fragment[] mFragments;

    /**
     * 当前的fragment
     */
    private Fragment mCurrentFragment;
    private static final String TAG = "FrameworkActivity";
    private BaseFragment mProductFragment;
    private BaseFragment mHomeFragment;
    private BaseFragment mFoundFragment;
    private BaseFragment mMyAssetsFragment;
    private long mExitTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    public int setLayoutId() {
        return R.layout.activity_framework;
    }

    @Override
    protected void initData() {
        getMvpPresenter().httpTest();//网络框架的使用
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //通过反射禁止Navigation动画
        disableShiftMode(mNavigation);
        mNavigation.setOnNavigationItemSelectedListener(this);
        getFragments(); //获取fragment
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.navigation_home) {
            switchFragment(0);
        } else if (i == R.id.navigation_product) {
            switchFragment(1);
        } else if (i == R.id.navigation_find) {
            switchFragment(2);
        } else if (i == R.id.navigation_my) {
            switchFragment(3);
        }
        return true;
    }

    /**
     * 切换fragment
     */
    private void switchFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = mFragments[position];

        if (!fragment.isAdded()) {
            transaction.hide(mCurrentFragment)
                    //设置tag,当activity意外挂了，会出现fragment重叠问题
                    .add(R.id.fragment_content, fragment, fragment.getClass().getName())
                    .show(fragment)
                    .commit();
            Log.e(TAG, "被添加: " + fragment.getClass().hashCode());
        } else {
            //当前fragment不相等再显示
            if (mCurrentFragment != fragment) {
                transaction.hide(mCurrentFragment).show(fragment).commit();
                Log.e(TAG, "被隐藏: " + fragment);
            }
        }
        mCurrentFragment = fragment;

    }


    /**
     * 通过路由路径获取Fragment
     */
    private void getFragments() {
        mHomeFragment = (BaseFragment) ARouter.getInstance().build(RouterPath.HOME_FRAGMENT).navigation();
        mProductFragment = (BaseFragment) ARouter.getInstance().build(RouterPath.PRODUCT_FRAGMENT).navigation();
        mFoundFragment = (BaseFragment) ARouter.getInstance().build(RouterPath.FOUND_FRAGMENT).navigation();
        mMyAssetsFragment = (BaseFragment) ARouter.getInstance().build(RouterPath.MY_ASSETS_FRAGMENT).navigation();

        mFragments = new Fragment[]{mHomeFragment, mProductFragment, mFoundFragment, mMyAssetsFragment};
        Fragment fragment = mFragments[0];
        //第一次进来的时候，不会加载fragment

        addFragment(fragment, R.id.fragment_content);
        mCurrentFragment = mHomeFragment;
    }


    /**
     * 禁止系统BottomNavigationView 默认的动画效果
     * 返回值：ClipboardManager
     *
     * @param view
     */
    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String home = intent.getStringExtra("home");//这是从投资成功页面跳转到首页
        if (!TextUtils.isEmpty(home)) {
            mNavigation.setSelectedItemId(R.id.navigation_home);
        }

        String homeInvestment = intent.getStringExtra("homeInvestment");//这个是首页投资
        if (!TextUtils.isEmpty(homeInvestment)) {
            mNavigation.setSelectedItemId(R.id.navigation_product);
        }

        String find = intent.getStringExtra("foufindnd");//去发现页面
        if (!TextUtils.isEmpty(find)) {
            mNavigation.setSelectedItemId(R.id.navigation_find);
        }

        String myAssets = intent.getStringExtra("myAssets");//去我的资产页面
        if (!TextUtils.isEmpty(myAssets)) {
            mNavigation.setSelectedItemId(R.id.navigation_my);
        }

    }

    @Override
    public FrameworkActivity getThis() {
        return this;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 绑定物理返回键
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            // 如果两次按键时间间隔大于2000毫秒，则不退出
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                // 记录exitTime
                mExitTime = System.currentTimeMillis();
            } else {
                // 否则退出程序
                finish();
//                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
