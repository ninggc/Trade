package com.ninggc.trade.activity.test;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.ninggc.trade.R;
import com.yanzhenjie.loading.LoadingView;
import com.yanzhenjie.loading.dialog.LoadingDialog;

import java.util.ArrayList;

/**
 * Created by Ning on 11/16/2017 0016.
 */

public class TestListActivity extends ListActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        Adapter adapter = new Adapter();
        listView.setAdapter(adapter);
        final Context context = this;
        adapter.addItem("测试Loading", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingView loadingView = new LoadingView(context);

                final Dialog mWaitDialog = new LoadingDialog(context);
                mWaitDialog.setTitle("Please wait...");
//                mWaitDialog.setCircleColors(...);
                mWaitDialog.show();
                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mWaitDialog.dismiss();
            }
        });
    }

    class Adapter implements ListAdapter {
        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(int position) {
            return false;
        }

        class Entity {
            protected String text;
            protected View.OnClickListener listener;
        }
        ArrayList<Entity> list = new ArrayList<>();

        public Adapter() {
            Entity e = new Entity();
            e.text = "text";
            list.add(e);
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public boolean hasStableIds() {
            return false;
        }

        public void addItem(String text, View.OnClickListener onClickListener) {
            Entity e = new Entity();
            e.text = text;
            e.listener = onClickListener;
            list.add(e);
            synchronized (Thread.currentThread()) {
                try {
                    notify();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView != null) {
                view = convertView;
            } else {
                view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.test_111, null);
            }
            Entity entity = (Entity) getItem(position);
            view.findViewById(R.id.button1).setOnClickListener(entity.listener);
            ((Button) view.findViewById(R.id.button1)).setText(entity.text);
            return view;
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return list.size();
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

    }
}
