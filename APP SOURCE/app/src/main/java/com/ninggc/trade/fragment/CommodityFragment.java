package com.ninggc.trade.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.c_d_activity.CommodityList;

import java.util.List;

/**
 * Created by Ning on 10/18/2017 0018.
 */

public class CommodityFragment extends Fragment implements View.OnClickListener {
    String TAG = getClass().getSimpleName();
    View view;
    Button btn_click;
    View layout;
    EditText et_username;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commodity_index, container, false);
        this.view = view;
        initView(view);
        initIMMessage();
        return view;
    }

    private void initIMMessage() {
        EMMessageListener msgListener = new EMMessageListener() {

            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                Log.e(TAG, "onMessageReceived: " + "message size is : " + messages.size());
                for (int i = 0; i < messages.size(); i++) {
                    Log.e(TAG, "onMessageReceived: " + messages.get(i).getBody().toString());
                }
                for (EMMessage emMessage :
                        messages) {
                    Log.e(TAG, "onMessageReceived: " + emMessage.getBody().toString());
                }
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                Log.e(TAG, "onCmdMessageReceived: ");
                //收到透传消息
            }

            @Override
            public void onMessageRead(List<EMMessage> messages) {
                //收到已读回执
                Log.e(TAG, "onMessageRead: ");
            }

            @Override
            public void onMessageDelivered(List<EMMessage> message) {
                //收到已送达回执
                Log.e(TAG, "onMessageDelivered: ");
            }
            @Override
            public void onMessageRecalled(List<EMMessage> messages) {
                //消息被撤回
                Log.e(TAG, "onMessageRecalled: ");
            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
                Log.e(TAG, "onMessageChanged: ");
            }
        };
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    private void initView(View view) {
        btn_click = (Button) view.findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toList();
            }
        });
        layout = view.findViewById(R.id.fragment1_card1);
        layout.setOnClickListener(this);
    }

    void toList() {
        Intent i = new Intent(getContext(), CommodityList.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment1_card1:
                et_username = (EditText) view.findViewById(R.id.fragment1_et_username);
                //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                EMMessage message = EMMessage.createTxtSendMessage(et_username.getText().toString(), et_username.getText().toString());
                //如果是群聊，设置chattype，默认是单聊
//                if (chatType == CHATTYPE_GROUP)
//                    message.setChatType(EMMessage.ChatType.GroupChat);
                //发送消息
                EMClient.getInstance().chatManager().sendMessage(message);
                break;
        }
    }
}
