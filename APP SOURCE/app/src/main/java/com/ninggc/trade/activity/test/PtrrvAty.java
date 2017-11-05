package com.ninggc.trade.activity.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.lhh.ptrrv.library.footer.loadmore.BaseLoadMoreView;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.base.BaseActivity;
import com.ninggc.trade.adapter.CommodityRecyclerViewAdapter;

/**
 * Created by Ning on 11/1/2017 0001.
 */

public class PtrrvAty extends BaseActivity {
    class DemoLoadMoreView extends BaseLoadMoreView {
        public DemoLoadMoreView(Context context, RecyclerView recyclerView) {
            super(context, recyclerView);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_ptrrv);
        PullToRefreshRecyclerView mPtrrv = (PullToRefreshRecyclerView) this.findViewById(R.id.ptrrv);

        DemoLoadMoreView loadMoreView = new DemoLoadMoreView(this, mPtrrv.getRecyclerView());
        loadMoreView.setLoadmoreString("load more");
        loadMoreView.setLoadMorePadding(100);

        mPtrrv.setLoadMoreFooter(loadMoreView);

//remove header
        mPtrrv.removeHeader();

// set true to open swipe(pull to refresh, default is true)
        mPtrrv.setSwipeEnable(true);

// set the layoutManager which to use
        mPtrrv.setLayoutManager(new LinearLayoutManager(this));

// set PagingableListener
        mPtrrv.setPagingableListener(new PullToRefreshRecyclerView.PagingableListener() {
            @Override
            public void onLoadMoreItems() {
                //do loadmore here
                Toast.makeText(PtrrvAty.this, "onLoadMoreItems", Toast.LENGTH_SHORT).show();
            }
        });

// set OnRefreshListener
        mPtrrv.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // do refresh here
                Toast.makeText(PtrrvAty.this, "onRefresh", Toast.LENGTH_SHORT).show();
            }
        });

// add item divider to recyclerView
        mPtrrv.getRecyclerView().addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

// add headerView
        mPtrrv.addHeaderView(View.inflate(this, R.layout.item_commidity, null));

//set EmptyVIEW
        mPtrrv.setEmptyView(View.inflate(this,R.layout.item_commidity, null));

// set loadmore String
        mPtrrv.setLoadmoreString("loading");

// set loadmore enable, onFinishLoading(can load more? , select before item)
        mPtrrv.onFinishLoading(true, false);

        mPtrrv.setAdapter(new CommodityRecyclerViewAdapter(this));
    }
}
