package com.ninggc.trade.activity.c_d_activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.ninggc.trade.DAO.Commodity;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.BaseActivity;
import com.ninggc.trade.adapter.CommodityRecyclerViewAdapter;
import com.ninggc.trade.factory.Constant;
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

public class CommodityList extends BaseActivity {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    CommodityRecyclerViewAdapter adapter;

    public String TAG = getClass().getSimpleName();
    ArrayList<Commodity> commodities = new ArrayList<>();
    Gson gson = new Gson();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_commodity);
        initView();
        initData();
        initList();
    }

    void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.fragment_main_1_recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
    }

    void initData() {

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
        Request<String> request = NoHttp.createStringRequest(Constant.url + "delegation/select.php", RequestMethod.POST);
        CallServer.getInstance().add(-1, request, new OnResponseListener() {
            @Override
            public void onStart(int what) {
                Log.e(TAG, "onStart: ");
            }

            @Override
            public void onSucceed(int what, Response response) {
                String result = (String) response.get();
                Log.e(TAG, "onSucceed: " + result);
                List<Commodity> list = parseJsonToList(result);
                Log.e(TAG, "onSucceed: " + list.size());
                adapter.addItem(list);
            }

            @Override
            public void onFailed(int what, Response response) {
                Toast.makeText(CommodityList.this, getString(R.string.NoHttp_status_failed), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish(int what) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    List<Commodity> parseJsonToList(String json) {
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        String list = object.get("data").getAsString();
        Log.e(TAG, "parseJsonToList: " + list);
        return gson.fromJson(list, new TypeToken<List<Commodity>>(){}.getType());
    }
}
