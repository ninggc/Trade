package com.ninggc.trade.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.test.BannerAty;
import com.ninggc.trade.factory.Constant;
import com.ninggc.trade.factory.GlideImageLoader;
import com.ninggc.trade.factory.http.HttpGetSomething;
import com.ninggc.trade.factory.http.ResponseListener;
import com.yanzhenjie.nohttp.rest.Response;
import com.youth.banner.Banner;

import java.util.Arrays;

/**
 * Created by Ning on 10/18/2017 0018.
 */

public class IndexFragment extends Fragment implements View.OnClickListener {
    final String TAG = getClass().getSimpleName();
    final String url = "http://123.207.244.139:8080/trade/image/1.jpg";
//    final String url = "http://img4.imgtn.bdimg.com/it/u=2611079001,3896435225&fm=27&gp=0.jpg";

    View view;
    Banner banner;
    EditText et_url;
    ImageView img;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.index, container, false);
        initView();
        return view;
    }

    private void initView() {
        et_url = (EditText) view.findViewById(R.id.et_url);
        img = (ImageView) view.findViewById(R.id.img);
        view.findViewById(R.id.btn_click).setOnClickListener(this);
        view.findViewById(R.id.btn_test_banner).setOnClickListener(this);
        banner = (Banner) view.findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());
        String[] images = {Constant.image1, Constant.image2, Constant.image3};
        banner.setImages(Arrays.asList(images));
        banner.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_click:
                final String url = et_url.getText().toString();
                HttpGetSomething.getImage(url, new ResponseListener<Bitmap>() {
                    @Override
                    public void onSucceed(int what, Response<Bitmap> response) {
                        Bitmap bm = response.get();
                        img.setImageBitmap(bm);
                        Log.e(TAG, "onSucceed: " + url);
                        Log.e(TAG, "onSucceed: " + "bm == null:" + (bm == null));
                    }

                    @Override
                    public void onFailed(int what, Response<Bitmap> response) {
                        Log.e(TAG, "onFailed: ");
                    }
                });
                break;
            case R.id.btn_test_banner:
                startActivity(new Intent(getContext(), BannerAty.class));
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
