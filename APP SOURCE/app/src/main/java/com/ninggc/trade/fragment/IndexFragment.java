package com.ninggc.trade.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ninggc.trade.R;
import com.ninggc.trade.factory.nohttp.CallServer;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.SimpleResponseListener;

/**
 * Created by Ning on 10/18/2017 0018.
 */

public class IndexFragment extends Fragment {
    final String TAG = getClass().getSimpleName();
    final String url = "http://123.207.244.139:8080/trade/image/test.jpg";

    ImageView img;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index, container, false);

        img = (ImageView) view.findViewById(R.id.img);

        view.findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Request<Bitmap> request = NoHttp.createImageRequest(url, RequestMethod.POST);
                CallServer.getInstance().add(0, request, new SimpleResponseListener<Bitmap>() {
                    @Override
                    public void onSucceed(int what, Response<Bitmap> response) {
                        img.setImageBitmap(response.get());
                        Log.e(TAG, "onSucceed: ");
                    }

                    @Override
                    public void onFailed(int what, Response<Bitmap> response) {
                        Log.e(TAG, "onFailed: ");
                    }
                });
            }
        });

        return view;
    }
}
