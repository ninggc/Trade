package com.ninggc.trade.activity.c_d_activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ninggc.trade.DAO.Commodity;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.base.BaseActivity;
import com.ninggc.trade.address.AddressCheckActivity;
import com.ninggc.trade.address.City;
import com.ninggc.trade.util.http.HttpResponseListener;
import com.ninggc.trade.util.http.Server;
import com.ninggc.trade.util.constants.Constant;
import com.ninggc.trade.util.constants.IRequestCode;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.nohttp.rest.Response;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileCallback;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ning
 * Created by Ning on 8/17/2017 0017.
 */

public class ReleaseCommodityActivity extends BaseActivity implements View.OnClickListener {
    EditText et_title;
    EditText et_note;
    Button btn_price;
//    TextView tv_endTime;
    Button btn_picture;
//    LinearLayout linearLayout;
    Button btn_choose_city;
    EditText et_detail_location;
    Button btn_release;
    String cityNumber;
    Toolbar toolbar;
    //取回的图片
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
        btn_price = (Button) findViewById(R.id.release_btn_price);
        btn_price.setOnClickListener(this);
//        tv_endTime = (TextView) findViewById(R.id.tv_endTime);
//        tv_endTime.setText(fmtDateAndTime.format(new Date()));
//        tv_endTime.setOnClickListener(this);
        btn_picture = (Button) findViewById(R.id.btn_picture);
        btn_picture.setOnClickListener(this);
//        linearLayout = (LinearLayout) findViewById(R.id.image_container);

        btn_choose_city = (Button) findViewById(R.id.release_btn_choose_city);
        btn_choose_city.setOnClickListener(this);
        et_detail_location = (EditText) findViewById(R.id.release_et_detail_location);

        btn_release = (Button) findViewById(R.id.btn_release);
        btn_release.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
//            case R.id.tv_endTime:              //获取日期格式器对象
//            {
//                Calendar now = null;
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//                    now = Calendar.getTestInstance(Locale.CHINA);
//                    final Calendar c = Calendar.getTestInstance(Locale.CHINA);
//                    new DatePickerDialog(ReleaseCommodityActivity.this,
//                            new DatePickerDialog.OnDateSetListener() {
//                                @Override
//                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                                        c.set(Calendar.YEAR, year);
//                                        c.set(Calendar.MONTH, month);
//                                        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
/////                                        设置发布和结束时间
////                                        delegation.setEndTime(new Timestamp(DemoLoadMoreView.getTimeInMillis()).toString());
////                                       delegation.setReleaseTime(new Timestamp(DemoLoadMoreView.getTimeInMillis()).toString());
//                                        ((TextView) v).setText(fmtDateAndTime.format(c.getTime()));
//                                    }
//                                }
//                            }
//                            , now.get(Calendar.YEAR)
//                            , now.get(Calendar.MONTH)
//                            , now.get(Calendar.DAY_OF_MONTH)
//
//                    ).show();
//                } else {
//                    Toast.makeText(ReleaseCommodityActivity.this, "需要更高Android系统", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            }
            case R.id.btn_release:
                Commodity commodity = check();
                if (commodity == null) {
                    break;
                } else {
                    Log.e("INFO", "onClick: " + gson.toJson(commodity));
                    Server.releaseCommodity(commodity, new HttpResponseListener<String>() {
                        @Override
                        public void onSucceed(int what, Response<String> response) {
                            super.onSucceed(what, response);
                            final String result = response.get();
                            Log.e("NOHTTP", "onSucceed: " + result);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    switch (result) {
                                        case "004" :
                                            Toast.makeText(ReleaseCommodityActivity.this, "非法字符串", Toast.LENGTH_SHORT).show();
                                            break;
                                        case "009" :
                                            Toast.makeText(ReleaseCommodityActivity.this, "上架成功", Toast.LENGTH_SHORT).show();
                                            break;
                                        case "005" :
                                            Toast.makeText(ReleaseCommodityActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                            break;
                                    }
                                }
                            });
                        }

                        @Override
                        public void onFailed(int what, Response<String> response) {
                            super.onFailed(what, response);
                            Toast.makeText(ReleaseCommodityActivity.this, "服务器异常", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
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

            case R.id.release_btn_choose_city :
                selectAddress();
                break;

            case R.id.release_btn_price :
                final EditText et_price = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("请输入价格")
                        .setView(et_price)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String s = et_price.getText().toString();
                                try {
                                    double price = Double.parseDouble(s);
                                    if (price < 0) {
                                        Toast.makeText(ReleaseCommodityActivity.this, "请输入正确的价格", Toast.LENGTH_SHORT).show();
                                    }
                                    btn_price.setText("￥" + s);
                                } catch (Exception e) {
                                    Toast.makeText(ReleaseCommodityActivity.this, "请输入正确的价格", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;

            default:break;
        }
    }

    private Commodity check() {
        Commodity commodity = new Commodity();

        commodity.setName(et_title.getText().toString());
        if (commodity.getName() == null || "".equals(commodity.getName())) {
            Toast.makeText(this, "标题不能为空", Toast.LENGTH_SHORT).show();
            return null;
        }

        commodity.setNote(et_note.getText().toString());
        if (commodity.getName() == null || "".equals(commodity.getNote())) {
            Toast.makeText(this, "请写下详细介绍", Toast.LENGTH_SHORT).show();
            return null;
        }

        if (images == null || images.size() < 1) {
            Toast.makeText(this, "请选择至少一张图片", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            commodity.setAlbumFiles(images);
        }

        commodity.setCityNumber(cityNumber);
        commodity.setDetailLocation(et_detail_location.getText().toString());
        if (cityNumber == null || "".equals(cityNumber) || commodity.getDetailLocation() == null || "".equals(commodity.getDetailLocation())) {
            Toast.makeText(this, "请选择地址", Toast.LENGTH_SHORT).show();
            return null;
        }

        String s = btn_price.getText().toString();
        if (s == null || "".equals(s)) {
            Toast.makeText(this, "请输入价格", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            double price = Double.parseDouble(s.substring(1));
            commodity.setPrice(price);
        }

        return commodity;
    }

    void finishAty() {
        finish();
    }

    /**
     * 去选择地址。
     */
    public void selectAddress() {
        Intent intent = new Intent(this, AddressCheckActivity.class);
        startActivityForResult(intent, Constant.ADDRESS);
    }

    /**
     * 解析地址。
     */
    public void parseAddress(Intent intent) {
        ArrayList<City> cityList = AddressCheckActivity.parse(intent);

        String tvAddress = "", lastId = "";
        if (cityList != null) {
            for (int i = 0; i < cityList.size(); i++) {
                City campus = cityList.get(i);
                lastId = campus.getId();
                tvAddress += campus.getName();
            }
        }
        cityNumber = lastId;
        Toast.makeText(this, tvAddress + "id : " + lastId, Toast.LENGTH_SHORT).show();
//        mTextView.setText(tvAddress + "\n提交到服务器的id是：" + lastId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case Constant.ADDRESS: {
                parseAddress(data);
                break;
            }
        }
    }
}
