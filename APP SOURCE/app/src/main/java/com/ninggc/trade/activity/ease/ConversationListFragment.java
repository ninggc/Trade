package com.ninggc.trade.activity.ease;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.ninggc.trade.R;

/**
 * @author Ning
 * Created by Ning on 11/12/2017 0012.
 */

public class ConversationListFragment extends EaseConversationListFragment {
    String TAG = getClass().getSimpleName();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated: " + "会话列表");

        //点击显示所有好友
        getActivity().findViewById(R.id.iv_all_friends).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ContactActivity.class));
            }
        });
    }

    @Override
    public void setConversationListItemClickListener(EaseConversationListItemClickListener listItemClickListener) {
        super.setConversationListItemClickListener(listItemClickListener);
        Log.e(TAG, "setConversationListItemClickListener: ");
    }
}
