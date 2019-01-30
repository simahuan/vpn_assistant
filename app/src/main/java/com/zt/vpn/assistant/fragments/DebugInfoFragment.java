package com.zt.vpn.assistant.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zt.vpn.assistant.R;

public class DebugInfoFragment extends BaseFragment {
    private TextView debugInfoTextView;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case VPN_CONNECTED:
                    if (null != debugInfoTextView) {
                        debugInfoTextView.setText("vpn已连接");
                    }
                    break;
                case VPN_DISCONNECTED:
                    if (null != debugInfoTextView) {
                        debugInfoTextView.setText("vpn已断开");
                    }
                    break;
                default:
                    break;
            }
            return false;
        }
    });

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.debug_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        debugInfoTextView = (TextView) view.findViewById(R.id.debugInfo);
    }

    @Override
    protected void setVpnConnectedState() {
        super.setVpnConnectedState();
        mHandler.sendEmptyMessage(VPN_CONNECTED);
    }

    @Override
    protected void setVpnDisconnectedState() {
        super.setVpnDisconnectedState();
        mHandler.sendEmptyMessage(VPN_DISCONNECTED);
    }
}
