package com.ninggc.trade.activity.ease;

import android.os.Bundle;
import android.util.Log;

import com.hyphenate.easeui.ui.EaseConversationListFragment;

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
    }

    @Override
    public void setConversationListItemClickListener(EaseConversationListItemClickListener listItemClickListener) {
        super.setConversationListItemClickListener(listItemClickListener);
        Log.e(TAG, "setConversationListItemClickListener: ");
    }
}
