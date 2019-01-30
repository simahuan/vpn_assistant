package com.zt.vpn.assistant.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.android.settings.vpn2.AidlVpnSettingsServer;
import com.zt.vpn.assistant.R;
import com.zt.vpn.assistant.entry.VpnProfile;
import com.zt.vpn.assistant.utils.LogUtils;
import com.zt.vpn.assistant.utils.Toasts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class VpnServerFragment extends BaseFragment implements View.OnClickListener {
    private RadioButton vpnStartRadioButton;
    private RadioButton vpnStopRadioButton;
    private RadioButton vpnChangeUserRadioButton;
    private boolean connected;
    private AidlVpnSettingsServer mAidlVpnSettingsServer;

    private List<VpnProfile> mVpnProfileList = null;

    private boolean isVpnConnecting = false;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case VpnState.VPN_CONNECTED:
                    if (null != vpnStartRadioButton && null != vpnStopRadioButton) {
                        vpnStartRadioButton.setEnabled(false);
                        vpnStopRadioButton.setEnabled(true);
                    }
                    break;
                case VpnState.VPN_DISCONNECTED:
                    if (null != vpnStartRadioButton && null != vpnStopRadioButton) {
                        vpnStartRadioButton.setEnabled(true);
                        vpnStopRadioButton.setEnabled(false);
                    }
                    break;
                default:
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onVpnConnected() {
        super.onVpnConnected();
        mHandler.sendEmptyMessage(VpnState.VPN_CONNECTED);
    }

    @Override
    protected void onVpnDisconnected() {
        super.onVpnDisconnected();
        mHandler.sendEmptyMessage(VpnState.VPN_DISCONNECTED);
    }

    @Override
    protected void setVpnConnectedState() {
        super.setVpnConnectedState();
        isVpnConnecting = false;
        mHandler.sendEmptyMessage(VPN_CONNECTED);
    }

    @Override
    protected void setVpnDisconnectedState() {
        super.setVpnDisconnectedState();
        isVpnConnecting = false;
        mHandler.sendEmptyMessage(VPN_DISCONNECTED);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mAidlVpnSettingsServer = AidlVpnSettingsServer.Stub.asInterface(service);
            // 服务起来之后 回调
            connected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            connected = false;
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.vpn_server, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpView(view);
        bindService();
        initVpnProfile();
    }

    private void setUpView(View view) {
        vpnStartRadioButton = (RadioButton) view.findViewById(R.id.vpnStart);
        vpnStopRadioButton = (RadioButton) view.findViewById(R.id.vpnStop);
        vpnChangeUserRadioButton = (RadioButton) view.findViewById(R.id.vpnChangeUser);
        vpnStartRadioButton.setOnClickListener(this);
        vpnStopRadioButton.setOnClickListener(this);
        vpnChangeUserRadioButton.setOnClickListener(this);
    }

    private void initVpnProfile() {
        mVpnProfileList = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            VpnProfile profile = VpnProfile.newBuilder()
                    .session("vpn" + i)
                    .address("3wx." + "f332" + i + ".net")
                    .userName("test" + i)
                    .password("aaaa" + i).build();
            mVpnProfileList.add(profile);
        }
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setPackage("com.android.settings");
        intent.setAction("com.vpn.aidl.settingsvpn");
        if (null != getActivity()) {
            getActivity().bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
            LogUtils.d("开始服务绑定...");
        }
    }

    private VpnProfile getProfile() {

        VpnProfile profile = VpnProfile.newBuilder()
                .session("vpn")
                .address("61.235.101.12")
                .userName("14231")
                .password("14231").build();
        return profile;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vpnStart:
                if (!isVpnConnecting) {
                    isVpnConnecting = !isVpnConnecting;
                    connected(getProfile());
                }
                break;
            case R.id.vpnStop:
                disconnected();
                break;
            case R.id.vpnChangeUser:
                vpnChangeUser();
                break;
            default:
                break;
        }
    }

    private void vpnChangeUser() {
        if (isConnected()) {
            disconnected();
        }
        if (mVpnProfileList != null && mVpnProfileList.size() > 0) {
            Random r = new Random();
            int index = r.nextInt(mVpnProfileList.size());
            VpnProfile profile = mVpnProfileList.get(index);
            connected(profile);
        }
    }

    private void connected(VpnProfile profile) {
        boolean result = false;
        try {
            LogUtils.d("connected" + mAidlVpnSettingsServer.toString());
            mAidlVpnSettingsServer.connectVpn(profile.getSession(), profile.getAddress(), profile.getUserName(), profile.getPassword());
            result = true;
        } catch (RemoteException e) {
            e.printStackTrace();
            result = false;
        } finally {
            LogUtils.d("connected  finally:" + result);
        }
    }

    private void disconnected() {
        try {
            if (null != mAidlVpnSettingsServer) {
                mAidlVpnSettingsServer.disconnectVpn();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            LogUtils.e("disconnectVpn failure...");
        }
    }

    private void unbindService() {
        if (connected) {
            Toasts.show(getActivity(), "解除服务");
            getActivity().unbindService(mServiceConnection);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindService();
    }

}
