package com.ninggc.trade.activity.ease;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.exceptions.HyphenateException;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.account.AccountUtil;
import com.ninggc.trade.activity.base.BaseActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ning on 11/12/2017 0012.
 */

public class ContactActivity extends BaseActivity {

    ContactListFragment contactListFragment;
    SPContactUtil spContactUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_chat);

        spContactUtil = new SPContactUtil(this);
//        if (spContactUtil.getALLContacts())

        contactListFragment = new ContactListFragment();
//        需要设置联系人列表才能启动fragment
        contactListFragment.setContactsMap(getContacts());
//        设置item点击事件
        contactListFragment.setContactListItemClickListener(new EaseContactListFragment.EaseContactListItemClickListener() {

            @Override
            public void onListItemClicked(EaseUser user) {
                startActivity(new Intent(ContactActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, user.getUsername()));
            }
        });

        getSupportFragmentManager().beginTransaction().add(R.id.container, contactListFragment).commit();
    }

    public Map<String,EaseUser> getContacts() {
        List<String> list = spContactUtil.getALLContacts();
        return contactToMap(list);
    }

    public static Map<String, EaseUser> contactToMap(List<String> list) {
        if (list == null) {
            return null;
        }
        Map<String, EaseUser> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(String.valueOf(i), new EaseUser(list.get(i)));
        }
        return map;
    }
}
