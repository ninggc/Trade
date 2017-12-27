package com.ninggc.trade.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ning on 12/27/2017 0027.
 */

public class CommentRecyclerViewAdapter extends RecyclerView.Adapter<CommentRecyclerViewAdapter.RecyclerViewHolder> {

    private Context context;
    private List<String> list;

    public CommentRecyclerViewAdapter(Context context, List<String> comments) {
        this.context = context;
        list = comments == null ? new ArrayList<>() : comments;
    }

    public CommentRecyclerViewAdapter(Context context) {
        this(context, new ArrayList<>());
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public RecyclerViewHolder(View itemView) {
            super(itemView);
        }
    }
}
