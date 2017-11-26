package com.ninggc.trade.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.ninggc.trade.DAO.User;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.account.AccountUtil;
import com.ninggc.trade.activity.account.LoginActivity;
import com.ninggc.trade.activity.account.PleaseLoginFragment;
import com.ninggc.trade.activity.account.UserinfoActivity;
import com.ninggc.trade.activity.c_d_activity.ReleaseCommodityActivity;
import com.ninggc.trade.activity.c_d_activity.ReleaseDelegationActivity;
import com.ninggc.trade.activity.ease.ChatActivity;
import com.ninggc.trade.activity.ease.ConversationListFragment;
import com.ninggc.trade.activity.test.TestListFragment;
import com.ninggc.trade.adapter.MyFragmentPagerAdapter;
import com.ninggc.trade.address.AddressCheckActivity;
import com.ninggc.trade.address.City;
import com.ninggc.trade.factory.constants.Constant;
import com.ninggc.trade.factory.constants.IRequestCode;
import com.ninggc.trade.fragment.IndexFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.ArrayList;

import static com.ninggc.trade.factory.constants.Constant.CANCEL;
import static com.ninggc.trade.factory.constants.Constant.DEBUG;
import static com.ninggc.trade.factory.constants.Constant.SUCCESS;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    final String TAG = getClass().getName();

//    ViewPager main_view_pager;
    BottomBar bottomBar;
//    TabLayout main_tabLayout;
    RelativeLayout relativeLayout;
    FloatingActionButton fab;

    ImageView nav_header_iv_login;
    TextView nav_header_tv_login;
    TextView nav_header_tv_tip;

    MyFragmentPagerAdapter myFragmentPagerAdapter;
    Tencent mTencent;
    IUiListener QQUIListener;

    Gson gson = new Gson();
    AccountSPUtil accountSPUtil;

    private int mCurrentViewPagerPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        initBaiduMap();
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

    @Override
    protected void onResume() {
        super.onResume();
        if (AccountUtil.isLogin()) {
            AccountUtil.getCurrentUser().getName();
            nav_header_tv_login.setText(AccountUtil.getCurrentUser().getName());
            nav_header_tv_tip.setVisibility(View.VISIBLE);
        } else {
            nav_header_tv_login.setText(getResources().getString(R.string.header_main_login_tip));
            nav_header_tv_tip.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出EMC
        EMClient.getInstance().logout(true);
    }

    private void initBaiduMap() {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
//        SDKInitializer.initialize(getApplicationContext());
    }

    void initView() {
        relativeLayout =  (RelativeLayout) findViewById(R.id.coordinatorlayout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        final LinearLayout nav_header = (LinearLayout) headerView.findViewById(R.id.nav_header);
        nav_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv_login = (TextView) nav_header.findViewById(R.id.main_tv_login);
                String text = tv_login.getText().toString();
                if (!AccountUtil.isLogin()) {
                    startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), IRequestCode.LOGIN);
                } else {
//                    Log.e(TAG, "onClick: " + text);
                    startActivity(new Intent(MainActivity.this, UserinfoActivity.class));
                }
            }
        });

        nav_header_iv_login = (ImageView) nav_header.findViewById(R.id.main_iv_login);
        nav_header_tv_login = (TextView) nav_header.findViewById(R.id.main_tv_login);
        nav_header_tv_tip = (TextView) nav_header.findViewById(R.id.nav_header_tv_tip);

    }

    void initData() {
        accountSPUtil = new AccountSPUtil();
        User user = accountSPUtil.getUserFromLocal();
        String cookie = accountSPUtil.getCookieFromLocal();
        if (user != null && cookie != null) {
            AccountUtil.login(user, cookie);
            // FIXME: 11/12/2017 0012 TEST账号拟登陆
            EMClient.getInstance().login("test", "test", new EMCallBack() {
                @Override
                public void onSuccess() {
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // FIXME: 11/12/2017 0012 未经测试
                            Toast.makeText(MainActivity.this, "EMC登陆成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onError(int i, String s) {

                }

                @Override
                public void onProgress(int i, String s) {

                }
            });
        }
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
//        main_tabLayout = (TabLayout) findViewById(R.id.main_tab_layout);
//        main_view_pager = (ViewPager) findViewById(R.id.main_view_pager);
//        ArrayList<String> titles = new ArrayList<>();
//        titles.add(getString(R.string.main_tab_title_1));
//        titles.add(getString(R.string.main_tab_title_2));
//        titles.add(getString(R.string.main_tab_title_3));
//        List<Fragment> fragments = new ArrayList<>();
//        fragments.add(new IndexFragment());
////        fragments.add(new CommodityFragment());
//        ConversationListFragment conversationListFragment = new ConversationListFragment();
//        conversationListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {
//            @Override
//            public void onListItemClicked(EMConversation conversation) {
//                startActivity(new Intent(MainActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation.conversationId()));
//            }
//        });
//        fragments.add(conversationListFragment);
//        fragments.add(new TestFragment());
//        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles);
//        main_view_pager.setAdapter(myFragmentPagerAdapter);
//        main_tabLayout.setupWithViewPager(main_view_pager);
//        main_tabLayout.setTabsFromPagerAdapter(myFragmentPagerAdapter);
        //set fab init status
        fab.hide();
//        main_view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                mCurrentViewPagerPosition = position;
//                if (DEBUG) {
//                    Log.e(TAG, "onPageSelected: " + position);
//                }
//                if (position == 2) {
//                    fab.show();
//                } else {
//                    fab.hide();
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

        final IndexFragment indexFragment = new IndexFragment();
        final TestListFragment testListFragment = new TestListFragment();
        final ConversationListFragment conversationListFragment = new ConversationListFragment();
        conversationListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                startActivity(new Intent(MainActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation.conversationId()));
            }
        });

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {

            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_home) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.contentContainer, indexFragment);
                    fragmentTransaction.commit();
                } else if (tabId == R.id.tab_contact) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    if (!AccountUtil.isLogin()) {
                        fragmentTransaction.replace(R.id.contentContainer, new PleaseLoginFragment());
                    } else {
                        fragmentTransaction.replace(R.id.contentContainer, testListFragment);
                    }
                    fragmentTransaction.commit();
                } else if (tabId == R.id.tab_chat) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    if (!AccountUtil.isLogin()) {
                        fragmentTransaction.replace(R.id.contentContainer, new PleaseLoginFragment());
                    } else {
                        fragmentTransaction.replace(R.id.contentContainer, conversationListFragment);
                    }
                    fragmentTransaction.commit();
                } else if (tabId == R.id.tab_shop) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.contentContainer, conversationListFragment);
                    fragmentTransaction.commit();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:break;
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
//            Snackbar.make(relativeLayout, "Message show", Snackbar.LENGTH_SHORT).setAction("ok", new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            }).show();
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
        }
        else if (id == R.id.nav_release_commodity) {
            startActivity(new Intent(MainActivity.this, ReleaseCommodityActivity.class));
        } else if (id == R.id.nav_share) {
            onClickShare();
//        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_setting) {
            // FIXME: 11/6/2017 0006 SETTING
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void onClickShare() {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "Trade");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "校内交易平台，快来瞧瞧吧");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://ninggc.cn/trade");
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,
                "https://github.com/ninggaocong/Trade/blob/master/DOC/store.png");
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
        Log.e(TAG, "onActivityResult: ");
        if (null != mTencent) {
            mTencent.onActivityResult(requestCode, resultCode, data);
        }
//        if (data == null) {
//            return;
//        }
        switch (requestCode) {
            case IRequestCode.LOGIN:
                if (resultCode == SUCCESS) {
                    Toast.makeText(this, getResources().getString(R.string.main_login_success), Toast.LENGTH_SHORT).show();
//                    Snackbar.make(relativeLayout, getResources().getString(R.string.main_login_success), Snackbar.LENGTH_SHORT).show();
                } else if (resultCode == CANCEL) {
                    Toast.makeText(this, getResources().getString(R.string.main_login_canceled), Toast.LENGTH_SHORT).show();
//                    Snackbar.make(relativeLayout, getResources().getString(R.string.main_login_canceled), Snackbar.LENGTH_SHORT).show();
                }
            default:
                break;
        }
        
        //修改首页显示的用户名
        if (AccountUtil.isLogin()) {
            Log.e(TAG, "onActivityResult: " + "登陆成功");
            nav_header_tv_login.setText(AccountUtil.getCurrentUser().getName());
            nav_header_tv_tip.setVisibility(View.INVISIBLE);
            accountSPUtil.saveUserToLocal();
        } else {
            nav_header_tv_login.setText(getResources().getString(R.string.header_main_login_tip));
            nav_header_tv_tip.setVisibility(View.INVISIBLE);
            Log.e(TAG, "onActivityResult: " + "未登录");
            accountSPUtil.logoutFromLocal();
            String currentUser = EMClient.getInstance().getCurrentUser();
            if (currentUser != null) {
                nav_header_tv_login.setText("EMC已登录 : " + currentUser);
            } else {
//                Log.e(TAG, "onActivityResult: ", );
                nav_header_tv_login.setText("未登录");
            }
        }
    }

    /**
     * 去选择地址。
     */
    public void selectAddress() {
        Intent intent = new Intent(this, AddressCheckActivity.class);
        startActivityForResult(intent, 666);
    }

    /**
     * 解析地址。
     */
    public void parseAddress(Intent intent) {
        ArrayList<City> cityList = AddressCheckActivity.parse(intent);

        String tvAddress = "", lastId = "";
        if (cityList != null) {
            for (int i = 0; i < cityList.size(); i++) {
                City city = cityList.get(i);
                lastId = city.getId();
                tvAddress += city.getName();
            }
        }
//        mTextView.setText(tvAddress + "\n提交到服务器的id是：" + lastId);
    }

    class AccountSPUtil {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;

        public AccountSPUtil() {
            sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        }

        void saveUserToLocal() {
            editor = sharedPreferences.edit();
            editor.putString("user", gson.toJson(AccountUtil.getCurrentUser()));
            editor.putString("cookie", AccountUtil.getCookie());
            editor.apply();
        }

        void logoutFromLocal() {
            editor = sharedPreferences.edit();
            editor.remove("user");
            editor.remove("cookie");
            editor.apply();
        }

        User getUserFromLocal() {
            String s = sharedPreferences.getString("user", "null");
            return gson.fromJson(s, User.class);
        }

        String getCookieFromLocal() {
            return sharedPreferences.getString("cookie", null);
        }
    }
}
