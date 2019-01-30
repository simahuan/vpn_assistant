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
import com.zt.vpn.assistant.utils.Toasts;
import com.zt.vpn.assistant.utils.VpnUtil;


/**
 * @author
 */
public class BaseFragment extends Fragment {
    protected Activity activity;
    private ConnectivityManager mConnectivityManager;

    /**
     * @author VPN_CONNECTED 已连接
     *         VPN_DISCONNECTED 断开连接
     */
    protected enum VpnState {
        /**
         * 已连接
         */
        VPN_CONNECTED,
        /**
         * 断开连接
         */
        VPN_DISCONNECTED;
    }

    protected VpnState mVpnState;

    protected void setVpnState(VpnState state) {
        this.mVpnState = state;
    }

    protected VpnState getVpnState() {

        return mVpnState;
    }


    private static final NetworkRequest VPN_REQUEST = new NetworkRequest.Builder()
            .removeCapability(NetworkCapabilities.NET_CAPABILITY_NOT_VPN)
            .removeCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED)
            .removeCapability(NetworkCapabilities.NET_CAPABILITY_TRUSTED)
            .build();


    private ConnectivityManager.NetworkCallback mNetworkCallback = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onAvailable(Network network) {
            boolean vpnUsed = VpnUtil.isVpnUsed();
            Toasts.show(activity, "网络-Available;vpnUsed=" + vpnUsed);
            if (vpnUsed) {
                onVpnConnected();
            } else {
                onVpnDisconnected();
            }
        }

        @Override
        public void onLost(Network network) {
            Toasts.show(activity, "网络-Lost");
            onVpnDisconnected();
        }
    };

    protected void onVpnConnected() {
        setVpnState(VpnState.VPN_CONNECTED);
    }

    protected void onVpnDisconnected() {
        setVpnState(VpnState.VPN_DISCONNECTED);
    }


    protected boolean isConnected() {
        return getVpnState() == VpnState.VPN_CONNECTED;
    }


    /**
     * @return
     */
    @Override
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
        mConnectivityManager = (ConnectivityManager) activity.getSystemService(Activity.CONNECTIVITY_SERVICE);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mConnectivityManager.registerNetworkCallback(VPN_REQUEST, mNetworkCallback);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mConnectivityManager.unregisterNetworkCallback(mNetworkCallback);
    }

}
