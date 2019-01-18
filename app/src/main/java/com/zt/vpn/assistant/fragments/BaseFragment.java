package com.zt.vpn.assistant.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.zt.vpn.assistant.ZtApplication;
import com.zt.vpn.assistant.entry.VpnProfile;
import com.zt.vpn.assistant.utils.Helper;
import com.zt.vpn.assistant.utils.Toasts;
import com.zt.vpn.assistant.utils.VpnUtil;


public class BaseFragment extends Fragment {
    protected Activity activity;
    protected final int VPN_CONNECTED = 0x001;
    protected final int VPN_DISCONNECTED = 0x002;
    protected int vpnState = VPN_DISCONNECTED;
    private ConnectivityManager mConnectivityManager;


    private static final NetworkRequest VPN_REQUEST = new NetworkRequest.Builder()
            .removeCapability(NetworkCapabilities.NET_CAPABILITY_NOT_VPN)
            .removeCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED)
            .removeCapability(NetworkCapabilities.NET_CAPABILITY_TRUSTED)
            .build();


    private ConnectivityManager.NetworkCallback mNetworkCallback = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onAvailable(Network network) {
            Toasts.show(activity, "网络-Available");
            boolean vpnUsed = VpnUtil.isVpnUsed();
            if (vpnUsed) {
                vpnConnected();
            } else {
                vpnDisconnected();
            }
        }

        @Override
        public void onLost(Network network) {
            Toasts.show(activity, "网络-Lost");
            vpnDisconnected();
        }
    };

    protected void vpnConnected() {
        setVpnState(VPN_CONNECTED);
        VpnProfile profile = (VpnProfile) Helper.getVpnProfile();
        Helper.loadVpnProfile();
    }

    protected void vpnDisconnected() {
        setVpnState(VPN_DISCONNECTED);
    }

    protected boolean isConnected() {
        return getVpnState() == VPN_CONNECTED ? true : false;
    }

    protected int getVpnState() {
        return vpnState;
    }

    protected void setVpnState(int state) {
        this.vpnState = state;
    }

    public Context getContext() {
        if (activity == null) {
            return ZtApplication.getInstance();
        }
        return activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
        mConnectivityManager = (ConnectivityManager) activity.getSystemService(activity.CONNECTIVITY_SERVICE);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Start monitoring
        mConnectivityManager.registerNetworkCallback(VPN_REQUEST, mNetworkCallback);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mConnectivityManager.unregisterNetworkCallback(mNetworkCallback);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
