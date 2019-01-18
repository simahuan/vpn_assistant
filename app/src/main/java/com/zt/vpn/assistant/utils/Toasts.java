package com.zt.vpn.assistant.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.zt.vpn.assistant.R;

public class Toasts {
    private static Toast toast;

    public static void show(Context c, int message) {
        if (c == null) return;
        show(c, c.getString(message));
    }

    public static void show(Context c, String message) {
        if (c == null) return;
        if (toast == null) {
            toast = Toast.makeText(c, message, Toast.LENGTH_SHORT);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(message);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void showNetworkError(Context context, VolleyError error) {
        if (error.networkResponse != null && error.networkResponse.statusCode == 500) {
            show(context, R.string.service_not_available);
            return;
        }
        show(context, R.string.network_error);
    }
}
