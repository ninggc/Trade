package com.ninggc.trade.activity.c_d_activity;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.ninggc.trade.DAO.Commodity;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.BaseActivity;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Ning on 8/17/2017 0017.
 */

public class ReleaseCommodityActivity extends BaseActivity {
    EditText et_title;
    EditText et_text;
    TextView tv_endTime;
    Button btn_release;

    Commodity commodity = new Commodity();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_commodity);

        initView();

    }

    private void initView() {
        et_title = (EditText) findViewById(R.id.et_title);
        et_text = (EditText) findViewById(R.id.et_body);
        tv_endTime = (TextView) findViewById(R.id.tv_endTime);
        final DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();
        tv_endTime.setText(fmtDateAndTime.format(new Date()));
        tv_endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //获取日期格式器对象

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
//                                        设置发布和结束时间
//                                        delegation.setEndTime(new Timestamp(c.getTimeInMillis()).toString());
//                                        delegation.setReleaseTime(new Timestamp(c.getTimeInMillis()).toString());
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
            }
        });
        btn_release = (Button) findViewById(R.id.btn_release);
        btn_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request<String> request = createStringRequest(url_commodity + "release");
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
            }
        });
    }

    void finishAty() {
        finish();
    }
}
