package com.ninggc.trade.activity.c_d_activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.ninggc.trade.DAO.Delegation;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.base.BaseActivity;
import com.ninggc.trade.adapter.DelegationRecycleViewAdapter;
import com.ninggc.trade.factory.Server;
import com.ninggc.trade.factory.constants.Constant;
import com.ninggc.trade.factory.nohttp.CallServer;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ning on 7/31/2017 0031.
 */

public class DelegationList extends BaseActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    DelegationRecycleViewAdapter adapter;

    public final String TAG = getClass().getSimpleName();
    ArrayList<Delegation> delegations = new ArrayList<>();
    Gson gson = new Gson();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.list_delegation);
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initList();
    }

    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.list_2_recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        initData();
    }

    @Override
    protected void initData() {
        ArrayList<Delegation> list = new ArrayList<>();
        adapter = new DelegationRecycleViewAdapter(DelegationList.this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(DelegationList.this));

        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initList();
            }
        });
    }


    void initList() {
        Request<String> request = NoHttp.createStringRequest(Server.url + "delegation/select.php", RequestMethod.POST);
        CallServer.getInstance().add(-1, request, new OnResponseListener() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response response) {
                String result = (String) response.get();
                List<Delegation> list = parseJsonToList(result);
                adapter.addItem(list);
            }

            @Override
            public void onFailed(int what, Response response) {
                Toast.makeText(DelegationList.this, getString(R.string.NoHttp_status_failed), Toast.LENGTH_SHORT).show();
                Toast.makeText(DelegationList.this, getString(R.string.NoHttp_status_success), Toast.LENGTH_SHORT).show();
                if (Constant.DEBUG) {
                    Log.e(TAG, "onSucceed: " + response.get());
                }
            }

            @Override
            public void onFinish(int what) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    List<Delegation> parseJsonToList(String json) {
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        String list = object.get("data").getAsString();
        Log.e(TAG, "parseJsonToList: " + list);
        return gson.fromJson(list, new TypeToken<List<Delegation>>(){}.getType());
    }
}
