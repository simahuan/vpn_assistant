package com.zt.vpn.assistant.network;

import android.text.TextUtils;

import com.zt.vpn.assistant.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonParamBuilder {
    private JSONObject body = new JSONObject();
    private Map<String, String> params = new HashMap<>();
    private static final String KEY_METHOD = "method";
    private static final String KEY_BODY = "body";

    private JsonParamBuilder() {
    }

    public static JsonParamBuilder newInstance() {
        return new JsonParamBuilder();
    }

    public JsonParamBuilder setApiName(String s) {
        if (!TextUtils.isEmpty(s)) {
            params.put(KEY_METHOD, s);
        }
        return this;
    }

    public JsonParamBuilder put(String key, String value) {
        if (value != null) {
            try {
                body.putOpt(key, value);
            } catch (JSONException e) {
                LogUtils.e(e, "Invalid json object key: %s, value: %s.", key, value);
            }
        }
        return this;
    }

    public JsonParamBuilder putOmitEmpty(String key, String value) {
        if (TextUtils.isEmpty(value)) return this;

        return put(key, value);
    }

    public JsonParamBuilder put(String key, int value) {
        try {
            body.putOpt(key, value);
        } catch (JSONException e) {
            LogUtils.e(e, "Invalid json object key: %s, value: %s.", key, value);
        }
        return this;
    }


    public JsonParamBuilder put(String key, boolean value) {
        try {
            body.putOpt(key, value);
        } catch (JSONException e) {
            LogUtils.e(e, "Invalid json object key: %s, value: %s.", key, value);
        }
        return this;
    }

    public JsonParamBuilder put(String key, JSONArray value) {
        if (value != null) {
            try {
                body.put(key, value);
            } catch (JSONException e) {
                LogUtils.e(e, "Invalid json object key: %s, value: %s.", key, value);
            }
        }
        return this;
    }

    public Map<String, String> build() {
        if (TextUtils.isEmpty(params.get(KEY_METHOD))) {
            throw new IllegalStateException("Method is not set.");
        }
        String s = body.toString();
        params.put(KEY_BODY, s);

        LogUtils.d("Request %s", this);
        return params;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("method: ").append(params.get(KEY_METHOD)).append("\n");
        sb.append("body: ").append(body.toString());

        return sb.toString();
    }
}
