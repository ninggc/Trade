package com.ninggc.trade.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

        String note = commodity.getNote();
        if (note != null) {
            holder.tv_note.setText(note.length() > 20 ?
                    note.substring(0, 60) + "..." : note);
        }

        holder.tv_price.setText("￥" + commodity.getPrice().toString());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailCommodityActivity.class);
                intent.putExtra("commodity", commodity);
                context.startActivity(intent);
            }
        });

        List<String> images = list.get(position).getImages();
        // FIXME: 12/17/2017 0017 IMAGE size
        for (int i = 0; i < (images == null ? 10 : images.size()); i++) {
            ImageView imageView = new ImageView(context);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageView.setImageDrawable(context.getDrawable(R.drawable.iphone));
            }
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            WindowManager windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
            int width = windowManager.getDefaultDisplay().getWidth();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) ((width / 3.0) - 8),(int) ((width / 3.0) - 8));
            params.setMargins(8, 0, 0, 0);
            imageView.setLayoutParams(params);
            holder.layout_image.addView(imageView);
        }
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
        private LinearLayout layout_image;
        private int position;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            tv_note = (TextView) itemView.findViewById(R.id.item_commodity_tv_intro);
            tv_price = (TextView) itemView.findViewById(R.id.item_commodity_tv_price);
            layout_image = (LinearLayout) itemView.findViewById(R.id.layout_image);
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
