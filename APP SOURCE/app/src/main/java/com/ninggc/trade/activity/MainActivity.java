package com.ninggc.trade.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.google.gson.Gson;
import com.ninggc.trade.DAO.User;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.c_d_activity.ReleaseCommodityActivity;
import com.ninggc.trade.activity.c_d_activity.ReleaseDelegationActivity;
import com.ninggc.trade.adapter.MyFragmentPagerAdapter;
import com.ninggc.trade.factory.Constant;
import com.ninggc.trade.fragment.CommodityFragment;
import com.ninggc.trade.fragment.DelegationFragment;
import com.ninggc.trade.fragment.IndexFragment;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.ArrayList;
import java.util.List;

import static com.ninggc.trade.factory.Constant.DEBUG;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, IIntentCode {
    final String TAG = getClass().getName();

    ViewPager main_view_pager;
    TabLayout main_tabLayout;
    CoordinatorLayout coordinatorLayout;
    FloatingActionButton fab;

    ImageView main_iv_login;
    TextView main_tv_login;

    MyFragmentPagerAdapter myFragmentPagerAdapter;
    Tencent mTencent;
    IUiListener QQUIListener;

    Gson gson = new Gson();
    /**
     * 登陆成功后的用户
     */
    User user;

    private int mCurrentViewPagerPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBaiduMap();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, mCurrentViewPagerPosition + "", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Log.e(TAG, "onClick: " + mCurrentViewPagerPosition);

                if (mCurrentViewPagerPosition == 1) {
                    startActivity(new Intent(MainActivity.this, ReleaseCommodityActivity.class));
                } else if (mCurrentViewPagerPosition == 2) {
                    startActivity(new Intent(MainActivity.this, ReleaseDelegationActivity.class));
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initView();
        initData();
        initViewPager();
    }

    private void initBaiduMap() {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
    }

    void initView() {
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        LinearLayout nav_header = (LinearLayout) headerView.findViewById(R.id.nav_header);
        nav_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), LOGIN);
            }
        });

        main_iv_login = (ImageView) nav_header.findViewById(R.id.main_iv_login);
        main_tv_login = (TextView) nav_header.findViewById(R.id.main_tv_login);
    }

    void initData() {
        mTencent = Tencent.createInstance(Constant.QQ_APP_ID, this);
        QQUIListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {

            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        };
    }

    void initViewPager() {
        main_tabLayout = (TabLayout) findViewById(R.id.main_tab_layout);
        main_view_pager = (ViewPager) findViewById(R.id.main_view_pager);
        ArrayList<String> titles = new ArrayList<>();
        titles.add(getString(R.string.main_tab_title_1));
        titles.add(getString(R.string.main_tab_title_2));
        titles.add(getString(R.string.main_tab_title_3));
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new IndexFragment());
        fragments.add(new CommodityFragment());
        fragments.add(new DelegationFragment());
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles);
        main_view_pager.setAdapter(myFragmentPagerAdapter);
        main_tabLayout.setupWithViewPager(main_view_pager);
        main_tabLayout.setTabsFromPagerAdapter(myFragmentPagerAdapter);
        //set fab init status
        fab.hide();
        main_view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentViewPagerPosition = position;
                if (DEBUG) {
                    Log.e(TAG, "onPageSelected: " + position);
                }
                if (position == 1 || position == 2) {
                    fab.show();
                } else {
                    fab.hide();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.btn_login:
//                startActivity(new Intent(MainActivity.class, LoginActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (DEBUG) {
            Log.e(TAG, "onCreateOptionsMenu: ");
        }
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (DEBUG) {
            Log.e(TAG, "onOptionsItemSelected: ");
        }
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
//            startActivity(new Intent(MainActivity.this, UserinfoActivity.class));
            startActivity(new Intent(MainActivity.this, ScrollingActivity.class));
//        } else if (id == R.id.nav_gallery) {
//            Snackbar.make(coordinatorLayout, "Message show", Snackbar.LENGTH_SHORT).setAction("ok", new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            }).show();
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
        } else if (id == R.id.nav_share) {
            onClickShare();
//        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_setting) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void onClickShare() {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "邀请您使用Trade");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "校内交易平台");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://baidu.com");
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,
                "http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
//        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "测试应用trade");
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE);
        mTencent.shareToQQ(MainActivity.this, params, QQUIListener);
    }

//    public void share(View view)
//    {
//        Bundle bundle = new Bundle();
////这条分享消息被好友点击后的跳转URL。
//        bundle.putString(Constants.PARAM_TARGET_URL, "http://connect.qq.com/");
////分享的标题。注：PARAM_TITLE、PARAM_IMAGE_URL、PARAM_	SUMMARY不能全为空，最少必须有一个是有值的。
//        bundle.putString(Constants.PARAM_TITLE, "我在测试");
////分享的图片URL
//        bundle.putString(Constants.PARAM_IMAGE_URL,
//                "http://img3.cache.netease.com/photo/0005/2013-03-07/8PBKS8G400BV0005.jpg");
////分享的消息摘要，最长50个字
//        bundle.putString(Constants.PARAM_SUMMARY, "测试");
////手Q客户端顶部，替换“返回”按钮文字，如果为空，用返回代替
//        bundle.putString(Constants.PARAM_APPNAME, "??我在测试");
////标识该消息的来源应用，值为应用名称+AppId。
//        bundle.putString(Constants.PARAM_APP_SOURCE, "星期几" + AppId);
//
//        mTencent.shareToQQ(this, bundle , QQUIListener);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (null != mTencent) {
            mTencent.onActivityResult(requestCode, resultCode, data);
        }
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case LOGIN:
                String result = data.getStringExtra("user");
                if (DEBUG) {
                    Log.e(TAG, "onActivityResult: " + result);
                }
                if (result == null || "".equals(result)) {
                    Toast.makeText(this, getResources().getString(R.string.main_login_canceled), Toast.LENGTH_SHORT).show();
                    break;
                }
                user = gson.fromJson(result, User.class);
                if (user == null) {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.main_login_failed), Toast.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(coordinatorLayout, getResources().getString(R.string.main_login_success) + result, Snackbar.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
        if (user != null) {
            main_tv_login.setText(user.getName());
        }
    }
}
