package com.zt.vpn.assistant.network;


import com.zt.vpn.assistant.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author
 */
public class ResponseParser {
    private JSONObject src;
    private int okCode;

    public ResponseParser(JSONObject o) {
        LogUtils.d("response: %s", o.toString());
        src = o;
        okCode = o.optInt("code", -1);
    }

    public boolean isOk() {
        return okCode == 200;
    }

    public int getOkCode() {
        return okCode;
    }

    public JSONObject getData() {
        return src.optJSONObject("data");
    }

    public String getDataString() {
        return src.optString("data");
    }

    public JSONArray getDataArray() {
        return src.optJSONArray("data");
    }
}
