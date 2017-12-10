/*
 * Copyright © Yan Zhenjie. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ninggc.trade.address;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ninggc.trade.R;
import java.util.List;

/**
 * <p>地址列表的适配器。</p>
 * Created by YanZhenjie on 2017/6/1.
 */
public class CampusListAdapter extends RecyclerView.Adapter<CampusListAdapter.CampusViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<City> mCityList;
    private OnCompatItemClickListener mItemClickListener;

    public CampusListAdapter(LayoutInflater layoutInflater, OnCompatItemClickListener itemClickListener) {
        this.mLayoutInflater = layoutInflater;
        this.mItemClickListener = itemClickListener;
    }

    public void notifyDataSetChanged(List<City> mCityList) {
        this.mCityList = mCityList;
        super.notifyDataSetChanged();
    }

    @Override
    public CampusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CampusViewHolder viewHolder = new CampusViewHolder(mLayoutInflater.inflate(R.layout.item_address_select, parent, false));
        viewHolder.mItemClickListener = mItemClickListener;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CampusViewHolder holder, int position) {
        holder.setData(mCityList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCityList == null ? 0 : mCityList.size();
    }

    static class CampusViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mTvCampus;
        RadioButton mRadioButton;

        OnCompatItemClickListener mItemClickListener;

        CampusViewHolder(View itemView) {
            super(itemView);
            mTvCampus = (TextView) itemView.findViewById(R.id.tv_area_name);
            mRadioButton = (RadioButton) itemView.findViewById(R.id.radio_btn);

            itemView.setOnClickListener(this);
        }

        public void setData(City mData) {
            mTvCampus.setText(mData.getName());
            mRadioButton.setChecked(mData.isSelect());
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null)
                mItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

}
