package com.ninggc.trade.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyphenate.easeui.ui.EaseConversationListFragment;

/**
 * Created by Ning on 11/12/2017 0012.
 */

public class ConversationFragment extends EaseConversationListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        hideTitleBar();
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
