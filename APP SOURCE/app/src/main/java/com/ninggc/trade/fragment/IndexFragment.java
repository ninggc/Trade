package com.ninggc.trade.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninggc.trade.DAO.Commodity;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.c_d_activity.CommodityList;
import com.ninggc.trade.adapter.CommodityRecyclerViewAdapter;
import com.ninggc.trade.factory.constants.Constant;
import com.ninggc.trade.factory.image.GlideImageLoader;
import com.ninggc.trade.factory.constants.IRequestCode;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Ning on 10/18/2017 0018.
 */

@SuppressWarnings("ALL")
public class IndexFragment extends Fragment implements View.OnClickListener {
    final String TAG = getClass().getSimpleName();
//    final String url = "http://123.207.244.139:8080/trade/image/1.jpg";
//    final String url = "http://img4.imgtn.bdimg.com/it/u=2611079001,3896435225&fm=27&gp=0.jpg";

    View view;
    Banner banner;
    RecyclerView recyclerView;
    CommodityRecyclerViewAdapter adapter;
//    EditText et_url;
//    ImageView img;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.index, container, false);
        initView();
        return view;
    }

    private void initView() {
//        et_url = (EditText) view.findViewById(R.i.et_url);
//        img = (ImageView) view.findViewById(R.id.img);
//        view.findViewById(R.id.btn_click).setOnClickListener(this);
//        view.findViewById(R.id.btn_test_banner).setOnClickListener(this);
        banner = (Banner) view.findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());
        String[] images = {Constant.image1, Constant.image2, Constant.image3};
        banner.setImages(Arrays.asList(images));
        banner.start();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        initData();
        view.findViewById(R.id.index_layout_book).setOnClickListener(this);
    }

    private void initData() {
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                // TODO: 11/1/2017 0001
                // TEST: 11/1/2017 0001 TEST something
                Album.album(getContext()) // 图片和视频混选。
                        .multipleChoice() // 多选模式，单选模式为：singleChoice()。
                        .requestCode(IRequestCode.INSIGNIFICANCE) // 请求码，会在listener中返回。
                        .columnCount(3) // 页面列表的列数。
                        .selectCount(5)  // 最多选择几张图片。
                        .camera(true) // 是否在Item中出现相机。
                        .checkedList(null) // 要反选的列表，比如选择一次再次选择，可以把上次选择的传入。
                        .onResult(new Action<ArrayList<AlbumFile>>() {
                            @Override
                            public void onAction(int requestCode, @NonNull ArrayList<AlbumFile> result) {
                                // TODO 接受结果。
                            }
                        })
                        .onCancel(new Action<String>() {
                            @Override
                            public void onAction(int requestCode, @NonNull String result) {
                                // TODO 用户取消了操作。
                            }
                        })
                        .start();
            }
        });
        adapter = new CommodityRecyclerViewAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        initList();
    }

    void initList() {
        Commodity c = new Commodity();
        adapter.addItem(c);
        adapter.addItem(c);
        // TODO: 11/1/2017 0001 列表初始化
        // REFACTOR: 11/1/2017 0001 wait for refactor
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.index_layout_book:
                Intent intent = new Intent(getContext(), CommodityList.class);
                intent.putExtra("kind", Constant.KIND_BOOK);
                startActivity(intent);
                break;
            default:break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }
}
