package com.ninggc.trade.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ninggc.trade.DAO.Comment;
import com.ninggc.trade.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ning on 12/27/2017 0027.
 */

public class CommentRecyclerViewAdapter
        extends RecyclerView.Adapter<CommentRecyclerViewAdapter.ViewHolder> {

    private Context context;
    public List<Comment> list;

    public CommentRecyclerViewAdapter(Context context, List<Comment> comments) {
        this.context = context;
        list = comments == null ? new ArrayList<>() : comments;
    }

    public CommentRecyclerViewAdapter(Context context) {
        this(context, new ArrayList<>());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comment comment = list.get(position);
        holder.tv_username.setText(comment.getReleaseUsername());
        holder.tv_time.setText(comment.getTime());

        String image = comment.getImage();
        if (image != null && !"".equals(image)) {
            Glide.with(context).load(image).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    holder.iv_image.setImageBitmap(resource);
                }
            });
        }

        holder.tv_content.setText(comment.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void changeList(List<Comment> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_image;
        TextView tv_username;
        TextView tv_content;
        TextView tv_time;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_image = (ImageView) itemView.findViewById(R.id.item_comment_iv_image);
            tv_username = (TextView) itemView.findViewById(R.id.item_comment_tv_username);
            tv_content = (TextView) itemView.findViewById(R.id.item_comment_tv_content);
            tv_time = (TextView) itemView.findViewById(R.id.item_comment_tv_time);
        }
    }
}
