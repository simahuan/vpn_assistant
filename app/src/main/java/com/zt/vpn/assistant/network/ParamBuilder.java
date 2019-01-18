package com.zt.vpn.assistant.network;

import android.text.TextUtils;

import com.zt.vpn.assistant.utils.LogUtils;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class ParamBuilder {
    private Map<String, String> params = new HashMap<>();
    private static final String KEY_METHOD = "method";

    private ParamBuilder() {
    }

    public static ParamBuilder newInstance() {
        return new ParamBuilder();
    }

    public ParamBuilder setApiName(String s) {
        if (!TextUtils.isEmpty(s)) {
            params.put(KEY_METHOD, s);
        }
        return this;
    }

    public ParamBuilder put(String key, String value) {
        if (value != null) {
            params.put(key, value);
        }
        return this;
    }

    public ParamBuilder putOmitEmpty(String key, String value) {
        if (TextUtils.isEmpty(value)) {
            return this;
        }
        params.put(key, String.valueOf(value));
        return this;
    }

    public ParamBuilder put(String key, boolean value) {
        params.put(key, value ? "true" : "false");
        return this;
    }

    public ParamBuilder put(String key, int value) {
        params.put(key, String.valueOf(value));
        return this;
    }

    public ParamBuilder put(String key, JSONArray value) {
        if (value != null) {
            params.put(key, value.toString());
        }
        return this;
    }

    public Map<String, String> build() {
        if (TextUtils.isEmpty(params.get(KEY_METHOD))) {
            throw new IllegalStateException("Method is not set.");
        }

        LogUtils.d("Request %s", this);
        return params;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("method: ").append(params.get(KEY_METHOD)).append("\n");
        sb.append("params: {\n");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getKey().equals(KEY_METHOD)) continue;
            sb.append("\t");
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }
}
