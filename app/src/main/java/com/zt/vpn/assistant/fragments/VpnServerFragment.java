package com.zt.vpn.assistant.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.android.settings.vpn2.AidlVpnSettingsServer;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.zt.vpn.assistant.R;
import com.zt.vpn.assistant.entry.VpnProfile;
import com.zt.vpn.assistant.network.Requests;
import com.zt.vpn.assistant.network.ResponseParser;
import com.zt.vpn.assistant.utils.LogUtils;
import com.zt.vpn.assistant.utils.Toasts;

import org.json.JSONObject;

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

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mAidlVpnSettingsServer = AidlVpnSettingsServer.Stub.asInterface(service);
            // 服务起来之后 回调
            startConnectVpnServer();
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
//        bindService();
    }

    private void setUpView(View view) {
        vpnStartRadioButton = (RadioButton) view.findViewById(R.id.vpnStart);
        vpnStopRadioButton = (RadioButton) view.findViewById(R.id.vpnStop);
        vpnChangeUserRadioButton = (RadioButton) view.findViewById(R.id.vpnChangeUser);
        vpnStartRadioButton.setOnClickListener(this);
        vpnStopRadioButton.setOnClickListener(this);
        vpnChangeUserRadioButton.setOnClickListener(this);
    }


    private void bindService() {
        Intent intent = new Intent();
        intent.setPackage("com.android.settings");
        intent.setAction("com.vpn.aidl.settingsvpn");
        if (null != getActivity()) {
            getActivity().bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
            LogUtils.e("开始服务绑定...");
        }
    }

    private void startConnectVpnServer() {
        Requests.getVpnProfiles("江苏徐州", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ResponseParser r = new ResponseParser(response);
                if (r.isOk()) {
                    ArrayList<VpnProfile> vpns = VpnProfile.parse(r.getDataArray());
                    final VpnProfile profile = vpns.get(0);
                    Toasts.show(activity, profile.toString());
                    LogUtils.e("profile = %s", profile.toString());
                    connected(profile);
                } else {
                    Toasts.show(activity, R.string.service_not_available);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toasts.show(activity, R.string.network_error);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vpnStart:
                bindService();
//                if (!isVpnConnecting) {
//                    isVpnConnecting = !isVpnConnecting;
////                    connected();
//                }
                break;
            case R.id.vpnStop:
                disconnected();
                break;
            case R.id.vpnChangeUser:
                startConnectVpnServer();
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
            LogUtils.e("connected" + mAidlVpnSettingsServer.toString());
            mAidlVpnSettingsServer.connectVpn(profile.getSession(), profile.getAddress(), profile.getUserName(), profile.getPassword());
            result = true;
        } catch (RemoteException e) {
            e.printStackTrace();
            result = false;
        } finally {
            LogUtils.e("connected  finally:" + result);
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
