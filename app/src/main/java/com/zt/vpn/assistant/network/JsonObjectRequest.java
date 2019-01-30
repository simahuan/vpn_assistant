package com.zt.vpn.assistant.network;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.zt.vpn.assistant.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

class JsonObjectRequest extends Request<JSONObject> {
    private static final String PROTOCOL_CHARSET = "utf-8";
    private Response.Listener<JSONObject> listener;
    private Map<String, String> params;

    JsonObjectRequest(Map<String, String> params,
                      Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(Method.GET, BuildConfig.HOST, errorListener);
        this.listener = listener;
        this.params = params;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
//        Map<String, String> m = super.getHeaders();
//
//        if (m == null || m.equals(Collections.emptyMap())) {
//            m = new HashMap<>();
//        }
//        m.put("token", BuildConfig.TOKEN);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("token", BuildConfig.TOKEN);
        return headers;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            JSONObject j = new JSONObject(jsonString);
            return Response.success(j, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        if (listener != null) {
            listener.onResponse(response);
        }
    }
}

