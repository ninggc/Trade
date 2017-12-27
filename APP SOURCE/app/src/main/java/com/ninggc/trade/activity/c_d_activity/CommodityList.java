package com.ninggc.trade.activity.c_d_activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.ninggc.trade.DAO.Commodity;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.base.BaseActivity;
import com.ninggc.trade.adapter.CommodityRecyclerViewAdapter;
import com.ninggc.trade.util.http.HttpResponseListener;
import com.ninggc.trade.util.http.Server;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

/**
 * Created by Ning on 7/31/2017 0031.
 * 根据Intent传递的kind，从服务器动态获取信息列表
 */

public class CommodityList extends BaseActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    CommodityRecyclerViewAdapter adapter;
    LinearLayout linearLayout_selector;
    AppCompatSpinner spinner;
    AppCompatSpinner spinner_1;

    public String TAG = getClass().getSimpleName();
//    ArrayList<Commodity> commodities = new ArrayList<>();
    Gson gson = new Gson();

    int kind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.list_commodity);
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        layout_toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.list_1_recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        linearLayout_selector = (LinearLayout) findViewById(R.id.linear_selector);
        spinner = (AppCompatSpinner) findViewById(R.id.spinner);
        //Spinner初始化时会自动调用一次OnItemSelectedListener事件
        //spinner.setSelection(0, true) 可设置初始化时不调用
        spinner.setSelection(0, true);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String[] arr = getResources().getStringArray(R.array.sort_first);
//                Toast.makeText(CommodityList.this, arr[position], Toast.LENGTH_SHORT).show();
                //刷新列表
//                initList();
                generateSpinner(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void generateSpinner(int position) {
        if (spinner_1 == null) {
            //实例化，添加，设为不可见
            spinner_1 = new AppCompatSpinner(CommodityList.this);
            spinner_1.setSelection(0, false);
            linearLayout_selector.addView(spinner_1);
            spinner_1.setVisibility(View.INVISIBLE);
        }
        ArrayAdapter<String> adapter = null;
        //动态添加的Spinner的数据集合
        String[] arr = null;
        switch (position) {
            case 0:
                arr = getResources().getStringArray(R.array.sort_clothes);
                break;
            case 1:
                arr = getResources().getStringArray(R.array.sort_digital);
                break;
            case 2:
                arr = getResources().getStringArray(R.array.sort_book);
                break;
        }
        adapter = new ArrayAdapter<>(CommodityList.this, android.R.layout.simple_spinner_dropdown_item, arr);
        spinner_1.setAdapter(adapter);
        final String[] finalArr = arr;
        spinner_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CommodityList.this, finalArr[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_1.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        if (getIntent() != null) {
            this.kind = getIntent().getIntExtra("kind", 0);

            if (kind == 0) {
                kind = 12;
            }
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

    //刷新列表
    @Override
    protected void initList() {
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }

        Server.showCommodityListWithSort(kind, new HttpResponseListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                super.onSucceed(what, response);

                String s = response.get();
                Log.e(TAG_NOHTTP + TAG_INFO, "onSucceed: " + s);
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                super.onFailed(what, response);
                Log.e("NOHTTP", "onFailed: " + "服务器异常");
            }

            @Override
            public void onFinish(int what) {
                super.onFinish(what);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
//        Server.showCommodityListWithSort(kind, new HttpResponseListener<String>() {
//            @Override
//            public void onSucceed(int what, Response<String> response) {
//                super.onSucceed(what, response);
//                String result = (String) response.get();
//                Log.e("NOHTTP-INFO", "onSucceed-result: " + result);
//                // FIXME: 12/3/2017 0003 结果处理
////                List<Commodity> list = parseJsonToList(result);
////                Log.e(TAG, "onSucceed-list.size: " + list.size());
////                adapter.addItem(list);
//            }
//
//            @Override
//            public void onFailed(int what, Response<String> response) {
//                super.onFailed(what, response);
//                Toast.makeText(CommodityList.this, getString(R.string.NoHttp_status_failed), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFinish(int what) {
//                super.onFinish(what);
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });
    }

    List<Commodity> parseJsonToList(String json) {
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        String list = object.get("data").getAsString();
        Log.e(TAG, "parseJsonToList: " + list);
        return gson.fromJson(list, new TypeToken<List<Commodity>>(){}.getType());
    }
}
