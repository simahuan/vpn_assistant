package com.zt.vpn.assistant.network;

import android.text.TextUtils;

import com.android.volley.Response;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 */
public class Requests {

    /**
     * 获取Vpn Profiles
     *
     * @param l
     * @param e
     * @return
     */
    public static Object getVpnProfiles(String district, Response.Listener<JSONObject> l, Response.ErrorListener e) {
        Map<String, String> params = new HashMap<>();
        params.put("district", URLEncoder.encode(district));
        if (TextUtils.isEmpty(district)) {
            params = null;
        }
        JsonObjectRequest r = new JsonObjectRequest(params, l, e);
        return RequestManager.add(r);
    }
}
