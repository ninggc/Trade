package com.ninggc.trade.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ninggc.trade.DAO.Delegation;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.c_d_activity.DetailDelegationActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ning on 8/16/2017 0016.
 */

public class DelegationRecycleViewAdapter extends RecyclerView.Adapter<DelegationRecycleViewAdapter.RecyclerViewHoler>  {
    Context context;
    List<Delegation> list;

    public DelegationRecycleViewAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public DelegationRecycleViewAdapter(Context context, List<Delegation> list) {
        this.context = context;
        this.list = (list == null ? new ArrayList<>() : list);
    }

    @Override
    public RecyclerViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_delegate, parent, false);
        return new RecyclerViewHoler(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHoler holder, int position) {
        holder.position = position;
        final Delegation delegation = list.get(position);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailDelegationActivity.class);
                intent.putExtra("delegation", delegation);
                context.startActivity(intent);
            }
        });
        holder.tv_note.setText(delegation.getDescription().length() > 20 ?
                delegation.getDescription().substring(0, 60) + "..." : delegation.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RecyclerViewHoler extends RecyclerView.ViewHolder {
        private int position;
        private View view;
        private TextView tv_note;

        public RecyclerViewHoler(View itemView) {
            super(itemView);
            view = itemView;
            tv_note = (TextView) view.findViewById(R.id.tv_note);
        }
    }


    public void addItem(Delegation d) {
        for (int i = 0; i < list.size(); i++) {
            //id 相同则比较
            if (d.getId() == list.get(i).getId()) {
                //不一样就替换
                if (!d.equals(list.get(i))) {
                    list.set(i, d);
                    notifyItemChanged(i);
                    return;
                } else {
                    return;
                }
            }
        }

        this.list.add(d);
        notifyItemChanged(list.size());
    }

    public void addItem(List<Delegation> list) {
        for (Delegation d : list) {
            addItem(d);
        }
    }
}
