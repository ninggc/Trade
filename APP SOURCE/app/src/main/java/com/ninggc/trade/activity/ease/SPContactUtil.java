package com.ninggc.trade.activity.ease;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.chat.EMClient;

import java.util.List;

/**
 * @author Ning
 * Created by Ning on 11/12/2017 0012.
 */

public class SPContactUtil {
    private final Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson = new Gson();

    public SPContactUtil(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("contact_" + EMClient.getInstance().getCurrentUser(), Context.MODE_PRIVATE);
    }


    public void setAllContacts(List<String> list) {
        editor = sharedPreferences.edit();
        editor.putString("all", gson.toJson(list));
        editor.apply();
    }

    public List<String> getALLContacts() {
        String json = sharedPreferences.getString("all", "null");
        if ("null".equals(json)) {
            return null;
        }
        return gson.fromJson(json, new TypeToken<List<String>>(){}.getType());
    }

    public void clear() {
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
