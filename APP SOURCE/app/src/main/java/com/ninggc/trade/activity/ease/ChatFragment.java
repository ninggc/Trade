package com.ninggc.trade.activity.ease;

import android.util.Log;
import android.view.View;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;

/**
 * Created by Ning on 11/12/2017 0012.
 */

public class ChatFragment extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentHelper {

    /**
     * 设置消息扩展属性
     */
    @Override
    public void onSetMessageAttributes(EMMessage message) {
        Log.e(TAG, "onSetMessageAttributes: ");
    }

    /**
     * 进入会话详情
     */
    @Override
    public void onEnterToChatDetails() {
        Log.e(TAG, "onEnterToChatDetails: ");
    }

    /**
     * 用户头像点击事件
     * @param username
     */
    @Override
    public void onAvatarClick(String username) {
        Log.e(TAG, "onAvatarClick: ");
    }

    @Override
    public void onAvatarLongClick(String username) {
        Log.e(TAG, "onAvatarLongClick: ");
    }

    /**
     * 消息气泡框点击事件
     */
    @Override
    public boolean onMessageBubbleClick(EMMessage message) {
        return false;
    }

    /**
     * 消息气泡框长按事件
     */
    @Override
    public void onMessageBubbleLongClick(EMMessage message) {

    }

    /**
     * 扩展输入栏item点击事件,如果要覆盖EaseChatFragment已有的点击事件，return true
     * @param view
     * @param itemId
     * @return
     */
    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        return false;
    }

    /**
     * 设置自定义chatrow提供者
     * @return
     */
    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return null;
    }
}
