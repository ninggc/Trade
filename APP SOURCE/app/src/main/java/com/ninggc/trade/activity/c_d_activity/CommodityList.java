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
import com.ninggc.trade.DAO.Commodity;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.base.BaseActivity;
import com.ninggc.trade.adapter.CommodityRecyclerViewAdapter;
import com.ninggc.trade.factory.constants.Constant;
import com.ninggc.trade.factory.http.HttpGetSomething;
import com.ninggc.trade.factory.http.ResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ning on 7/31/2017 0031.
 */

public class CommodityList extends BaseActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    CommodityRecyclerViewAdapter adapter;

    public String TAG = getClass().getSimpleName();
    ArrayList<Commodity> commodities = new ArrayList<>();
    Gson gson = new Gson();

    String kind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.list_commodity);
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();
        initData();
        initList();

    }

    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.list_1_recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
    }

    @Override
    protected void initData() {

        if (getIntent() != null) {
            this.kind = getIntent().getStringExtra("kind");
        }

        adapter = new CommodityRecyclerViewAdapter(CommodityList.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

//        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
//        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callback);
//        mItemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initList();
            }
        });

    }

    void initList() {
        Map<String, String> map = new HashMap<>();
        map.put("kind", kind);
        ResponseListener<String> responseListener = new ResponseListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                super.onSucceed(what, response);
                String result = (String) response.get();
                Log.e(TAG, "onSucceed: " + result);
                List<Commodity> list = parseJsonToList(result);
                Log.e(TAG, "onSucceed: " + list.size());
                adapter.addItem(list);
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                super.onFailed(what, response);
                Toast.makeText(CommodityList.this, getString(R.string.NoHttp_status_failed), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish(int what) {
                super.onFinish(what);
                swipeRefreshLayout.setRefreshing(false);
            }
        };
        HttpGetSomething.getString(NO_WHAT, Constant.url + "commodity/select.php", responseListener, map);
    }

    List<Commodity> parseJsonToList(String json) {
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        String list = object.get("data").getAsString();
        Log.e(TAG, "parseJsonToList: " + list);
        return gson.fromJson(list, new TypeToken<List<Commodity>>(){}.getType());
    }
}
