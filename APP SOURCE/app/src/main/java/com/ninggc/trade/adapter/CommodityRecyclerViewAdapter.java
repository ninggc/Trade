package com.ninggc.trade.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ninggc.trade.DAO.Commodity;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.c_d_activity.DetailCommodityActivity;
import com.ninggc.trade.fragment.onMoveAndSwipedListener;

import java.util.ArrayList;
import java.util.List;

import static com.ninggc.trade.factory.constants.Constant.DEBUG;

/**
 * Created by Ning on 8/16/2017 0016.
 */

public class CommodityRecyclerViewAdapter extends RecyclerView.Adapter<CommodityRecyclerViewAdapter.RecyclerViewHolder> implements onMoveAndSwipedListener {
    private Context context;
    private List<Commodity> list;

    public CommodityRecyclerViewAdapter(Context context, List<Commodity> commodities) {
        this.context = context;
        list = commodities == null ? list = new ArrayList<>() : commodities;
    }

    public CommodityRecyclerViewAdapter(Context context) {
        this(context, new ArrayList<Commodity>());
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commidity, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.position = position;
        final Commodity commodity = list.get(position);
        holder.tv_note.setText(commodity.getNote().length() > 20 ?
                commodity.getNote().substring(0, 60) + "..." : commodity.getNote());
        holder.tv_price.setText("￥" + commodity.getPrice().toString());
        Log.e("ADAPTER", "onBindViewHolder: " + commodity.getNote().length());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailCommodityActivity.class);
                intent.putExtra("commodity", commodity);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (DEBUG) {
            Log.e(getClass().getSimpleName(), "onItemMove: ");
        }
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        if (DEBUG) {
            Log.e(getClass().getSimpleName(), "onItemDismiss: ");
        }
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView tv_note;
        private TextView tv_price;
        private int position;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            tv_note = (TextView) itemView.findViewById(R.id.card_main_1_tv_intro);
            tv_price = (TextView) itemView.findViewById(R.id.card_main_1_tv_price);
        }
    }

    public void addItem(Commodity c) {
        for (int i = 0; i < list.size(); i++) {
            //id 相同则比较
            if (c.getId() == list.get(i).getId()) {
                //不一样就替换
                if (!c.equals(list.get(i))) {
                    list.set(i, c);
                    notifyItemChanged(i);
                    return;
                } else {
                    return;
                }
            }
        }

        this.list.add(c);
        notifyItemChanged(list.size());
        Log.e("ADAPTER", "addItem: " + new Gson().toJson(this.list));
    }

    public void addItem(List<Commodity> list) {
        for (Commodity c : list) {
            addItem(c);
        }
        Log.e("ADAPTER", "addAllItem: " + new Gson().toJson(this.list));
    }

    public void changeList(List<Commodity> list) {
        this.list = list;
        notifyDataSetChanged();
        Log.e("ADAPTER", "changeList: " + new Gson().toJson(this.list));
    }
}
