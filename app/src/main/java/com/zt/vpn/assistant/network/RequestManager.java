package com.zt.vpn.assistant.network;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestManager {
    private static RequestQueue requestQueue;

    /**
     * 初始化Volley
     * @param context
     */
    public static void init(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    private static RequestQueue getRequestQueue() {
        if (requestQueue != null) {
            return requestQueue;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }

    public static <T> Object add(Request<T> request) {
        Object tag = new Object();
        request.setTag(tag);
        request.setRetryPolicy(new DefaultRetryPolicy(5000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(request);
        return tag;
    }

    public static void cancel(Object tag) {
        if (tag != null) {
            getRequestQueue().cancelAll(tag);
        }
    }
}
