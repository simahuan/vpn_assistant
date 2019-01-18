package com.zt.vpn.assistant.network;


import com.zt.vpn.assistant.utils.LogUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class ResponseParser {
    private JSONObject src;
    private int errorCode;

    public ResponseParser(JSONObject o) {
        LogUtils.d("response: %s", o.toString());
        src = o;
        errorCode = o.optInt("ErrorCode", -1);
    }

    public boolean isOk() {
        return errorCode == 1;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public JSONObject getData() {
        return src.optJSONObject("Data");
    }

    public String getDataString() {
        return src.optString("Data");
    }

    public JSONArray getDataArray() {
        return src.optJSONArray("Data");
    }
}
