package com.zt.vpn.assistant.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Handler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Helper {
    /**
     * Key prefix for VPN.
     */
    public static final String VPN = "VPN_";

    public static void resetVpn(final Context context) {
        disconnect(context);//调用连接前先断开已连接的VPN，保证顺利连接我们要连接的VPN
        //断开当前VPN连接到再次连接VPN之间需要时间间隔，否则会连接失败。
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Object profile = getVpnProfile();
                connect(context, profile);
            }
        }, 500);
    }

    public static Object loadVpnProfile() {
        try {
//            Class clsVpnSettings = Class.forName("com.android.settings.vpn2.VpnSettings");
//            Method loadVpnProfilesMethod = clsVpnSettings.getMethod("loadVpnProfiles", KeyStore.class, Integer[].class);
//            Object keyStoreObj = getKeyStoreInstance();
//            List<VpnProfile> list  = ( List<VpnProfile> )loadVpnProfilesMethod.invoke(null,keyStoreObj);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Class getKeyStoreClazz() {
        Class clsKeyStore = null;
        try {
            clsKeyStore = Class.forName("android.security.KeyStore");
        } catch (Exception e) {
            e.printStackTrace();
            clsKeyStore = null;
        }
        return clsKeyStore;
    }

    private static Object getKeyStoreInstance() throws Exception {
        Class clsKeyStore = Class.forName("android.security.KeyStore");
        Method methodGetInstance = clsKeyStore.getMethod(
                "getInstance", null);
        methodGetInstance.setAccessible(true);
        Object objKeyStore = methodGetInstance.invoke(null, null);
        return objKeyStore;
    }

    public static Object getVpnProfile() {
        try {
            Class clsKeyStore = getKeyStoreClazz();
            Object objKeyStore = getKeyStoreInstance();

            Method methodIsUnlocked = clsKeyStore.getMethod(
                    "isUnlocked", null);
            methodIsUnlocked.setAccessible(true);
            Boolean b = (Boolean) methodIsUnlocked.invoke(objKeyStore, null);

            Method methodList = clsKeyStore.getMethod("list",
                    String.class);
            methodList.setAccessible(true);
            String[] keys = (String[]) methodList.invoke(objKeyStore, VPN);

            if (keys == null || keys.length == 0) {
                return null;
            }

            Class clsVpnProfile = Class
                    .forName("com.android.internal.net.VpnProfile");
            Method methodDecode = clsVpnProfile.getDeclaredMethod("decode",
                    String.class, byte[].class);
            methodDecode.setAccessible(true);

            Method methodGet = clsKeyStore.getDeclaredMethod("get",
                    String.class);
            methodGet.setAccessible(true);

            Object byteArrayValue = methodGet.invoke(objKeyStore, VPN
                    + keys[0]);//此处0表示连接VPN列表中的第一个VPN

            Object objVpnProfile = methodDecode.invoke(null, keys[0],
                    byteArrayValue);

            return objVpnProfile;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Class getVpnProfileClass() {
        try {
            return Class.forName("com.android.internal.net.VpnProfile");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void connect(Context context, Object profile) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        Field fieldIConManager = null;

        try {
            fieldIConManager = cm.getClass().getDeclaredField("mService");
            fieldIConManager.setAccessible(true);
            Object objIConManager = fieldIConManager.get(cm);
            Class clsIConManager = Class.forName(objIConManager.getClass()
                    .getName());

            Class clsVpnProfile = Class
                    .forName("com.android.internal.net.VpnProfile");
            Method metStartLegacyVpn = clsIConManager.getDeclaredMethod(
                    "startLegacyVpn", clsVpnProfile);
            metStartLegacyVpn.setAccessible(true);

            metStartLegacyVpn.invoke(objIConManager, profile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void disconnect(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        Field fieldIConManager = null;

        try {
            fieldIConManager = cm.getClass().getDeclaredField("mService");
            fieldIConManager.setAccessible(true);
            Object objIConManager = fieldIConManager.get(cm);
            Class clsIConManager = Class.forName(objIConManager.getClass()
                    .getName());
            Method metPrepare = clsIConManager.getDeclaredMethod("prepareVpn",
                    String.class, String.class);
            metPrepare.invoke(objIConManager, "[Legacy VPN]", "[Legacy VPN]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
