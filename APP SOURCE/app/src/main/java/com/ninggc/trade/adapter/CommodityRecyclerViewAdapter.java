package com.ninggc.trade.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ninggc.trade.DAO.Commodity;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.c_d_activity.DetailCommodityActivity;
import com.ninggc.trade.fragment.onMoveAndSwipedListener;

import java.util.ArrayList;
import java.util.List;

import static com.ninggc.trade.factory.Constant.DEBUG;

/**
 * Created by Ning on 8/16/2017 0016.
 */

public class CommodityRecyclerViewAdapter extends RecyclerView.Adapter<CommodityRecyclerViewAdapter.RecyclerViewHolder> implements onMoveAndSwipedListener {
    private Context context;
    private List<Commodity> list;

    public CommodityRecyclerViewAdapter(Context context, List<Commodity> commodities) {
        this.context = context;
        this.list = commodities;
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
        holder.tv_note.setText(commodity.getNote());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, ShareViewActivity.class);
//                intent.putExtra("color", color);
//                context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation
//                        ((Activity) context, holder.rela_round, "shareView").toBundle());

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
        private int position;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            tv_note = (TextView) itemView.findViewById(R.id.card_main_1_tv_intro);
        }
    }

    public void addItem(Commodity c) {
        int position = -1;
        for (int i = 0; i < list.size(); i++) {
            if (c.getId() == list.get(i).getId()) {
                position = i;
                break;
            }
        }
        //如果已经存在，就不添加
        //if is already exist, not add
        if (position == -1) {
            this.list.add(c);
            notifyItemInserted(this.list.size());
        } else {
             this.list.set(position, c);
            notifyItemChanged(position);
            //如果存在,就替换
        }
    }

    public void addItem(List<Commodity> list) {
        for (Commodity c : list) {
            addItem(c);
        }
    }
}
