package com.ninggc.trade.fragment;

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
import com.ninggc.trade.factory.http.HttpGetSomething;
import com.ninggc.trade.factory.http.ResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Ning on 10/18/2017 0018.
 */

public class IndexFragment extends Fragment {
    final String TAG = getClass().getSimpleName();
    final String url = "http://123.207.244.139:8080/trade/image/1.jpg";
//    final String url = "http://img4.imgtn.bdimg.com/it/u=2611079001,3896435225&fm=27&gp=0.jpg";

    View view;
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
        view.findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

//                Request<Bitmap> request = NoHttp.createImageRequest(url, RequestMethod.GET);
//                CallServer.getInstance().add(0, request, new SimpleResponseListener<Bitmap>() {
//                    @Override
//                    public void onSucceed(int what, Response<Bitmap> response) {
//                        Bitmap bm = response.get();
//                        img.setImageBitmap(bm);
//                        Log.e(TAG, "onSucceed: " + "bm == null:" + (bm == null));
//                    }
//
//                    @Override
//                    public void onFailed(int what, Response<Bitmap> response) {
//                        Log.e(TAG, "onFailed: ");
//                    }
//                });
            }
        });
    }
}
