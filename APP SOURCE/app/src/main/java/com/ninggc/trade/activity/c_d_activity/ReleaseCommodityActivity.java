package com.ninggc.trade.activity.c_d_activity;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ninggc.trade.DAO.Commodity;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.base.BaseActivity;
import com.ninggc.trade.factory.constants.IRequestCode;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileCallback;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ning on 8/17/2017 0017.
 */

public class ReleaseCommodityActivity extends BaseActivity implements View.OnClickListener {
    EditText et_title;
    EditText et_note;
    TextView tv_endTime;
    Button btn_picture;
//    LinearLayout linearLayout;
    Button btn_release;
    Toolbar toolbar;
    Commodity commodity = new Commodity();
    List<AlbumFile> images;
    final DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_release_commodity);
        super.onCreate(savedInstanceState);

        initView();

    }

    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        et_title = (EditText) findViewById(R.id.et_title);
        et_note = (EditText) findViewById(R.id.et_body);
        tv_endTime = (TextView) findViewById(R.id.tv_endTime);
        tv_endTime.setText(fmtDateAndTime.format(new Date()));
        tv_endTime.setOnClickListener(this);
        btn_picture = (Button) findViewById(R.id.btn_picture);
        btn_picture.setOnClickListener(this);
//        linearLayout = (LinearLayout) findViewById(R.id.image_container);
        btn_release = (Button) findViewById(R.id.btn_release);
        btn_release.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.tv_endTime:              //获取日期格式器对象
            {
                Calendar now = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    now = Calendar.getInstance(Locale.CHINA);
                    final Calendar c = Calendar.getInstance(Locale.CHINA);
                    new DatePickerDialog(ReleaseCommodityActivity.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                        c.set(Calendar.YEAR, year);
                                        c.set(Calendar.MONTH, month);
                                        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
///                                        设置发布和结束时间
//                                        delegation.setEndTime(new Timestamp(DemoLoadMoreView.getTimeInMillis()).toString());
//                                       delegation.setReleaseTime(new Timestamp(DemoLoadMoreView.getTimeInMillis()).toString());
                                        ((TextView) v).setText(fmtDateAndTime.format(c.getTime()));
                                    }
                                }
                            }
                            , now.get(Calendar.YEAR)
                            , now.get(Calendar.MONTH)
                            , now.get(Calendar.DAY_OF_MONTH)

                    ).show();
                } else {
                    Toast.makeText(ReleaseCommodityActivity.this, "需要更高Android系统", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.btn_release:
                final Request<String> request = createStringRequest(url_commodity + "release");
                request(NO_WHAT, request, new OnResponseListener<String>() {
                    @Override
                    public void onStart(int what) {

                    }

                    @Override
                    public void onSucceed(int what, Response<String> response) {
                        Toast.makeText(ReleaseCommodityActivity.this, "SUCCEED", Toast.LENGTH_SHORT).show();
                        //TODO
                    }

                    @Override
                    public void onFailed(int what, Response<String> response) {
                        Toast.makeText(ReleaseCommodityActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
                        //TODO
                    }

                    @Override
                    public void onFinish(int what) {
                        Toast.makeText(ReleaseCommodityActivity.this, "FINISH", Toast.LENGTH_SHORT).show();
                        //TODO
                    }
                });
                break;
            case R.id.btn_picture:
                Album.image(this)
                        .multipleChoice()
                        .requestCode(IRequestCode.RELEASE_COMMODITY)
                        .camera(true)
                        .columnCount(3)
                        .selectCount(5)
                        .onResult(new Action<ArrayList<AlbumFile>>() {
                            @Override
                            public void onAction(int requestCode, @NonNull ArrayList<AlbumFile> result) {
                                images = result;
                                btn_picture.setText("已选择" + result.size() + "张图片");
                                String path = result.get(0).getPath();
                                Bitmap bitmap = BitmapFactory.decodeFile(path);
                                Log.e("TINY", "onAction: " + bitmap.getRowBytes() * bitmap.getHeight());
                                Tiny.BitmapCompressOptions options = new Tiny.BitmapCompressOptions();
                                Tiny.FileCompressOptions options1 = new Tiny.FileCompressOptions();
                                Tiny.getInstance().source(path).asFile().withOptions(options1).compress(new FileCallback() {
                                    @Override
                                    public void callback(boolean isSuccess, String outfile, Throwable t) {

                                    }
                                });
                            }
                        })
                        .onCancel(new Action<String>() {
                            @Override
                            public void onAction(int requestCode, @NonNull String result) {
                                btn_picture.setText("选择图片");
                                Toast.makeText(ReleaseCommodityActivity.this, "已取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .start();
                break;

            default:break;

        }
    }

    void finishAty() {
        finish();
    }
}
