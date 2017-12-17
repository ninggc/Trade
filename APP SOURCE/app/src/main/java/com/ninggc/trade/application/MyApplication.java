package com.ninggc.trade.application;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.hyphenate.chat.EMChatManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.mob.MobApplication;
import com.ninggc.trade.R;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.zxy.tiny.Tiny;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Ning on 7/29/2017 0029.
 */

public class MyApplication extends MobApplication {
//    public static RequestQueue queue = null;

    Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        initNoHttp();
        initIM();
        initTiny();
        initBaiduSDK();
    }

    private void initBaiduSDK() {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
    }

    private void initNoHttp() {
        NoHttp.initialize(this, new NoHttp.Config()
                .setNetworkExecutor(new OkHttpNetworkExecutor()));
        Logger.setTag("NoHttpUser");

        Logger.setDebug(true);
        Logger.setTag("NOHTTP");
    }

    void initIM() {
        context = this;
        int pid = Process.myPid();
        String processAppName = getAppName(pid);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回

        if (processAppName == null ||!processAppName.equalsIgnoreCase(context.getPackageName())) {
            Log.e("MyApplication", "enter the service process!");

            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }


        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true);
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true);
        //取消自动登录
        options.setAutoLogin(false);
        //初始化
        EMClient.getInstance().init(context, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);

//        EMChatManager.getTestInstance().getChatOptions().setUseRoster(true)
        EaseUI.getInstance().init(context, null);
    }

    void initTiny() {
//        Tiny.getTestInstance().init(this);
    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }
}
