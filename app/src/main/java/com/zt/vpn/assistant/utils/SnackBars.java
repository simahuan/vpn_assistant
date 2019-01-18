package com.zt.vpn.assistant.utils;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zt.vpn.assistant.R;


public class SnackBars {

    public static void show(View view, int message) {
        show(view, view.getContext().getString(message));
    }

    private static void show(View view, String message) {
        if (view == null || view.getParent() == null) return;
        ViewGroup g= (ViewGroup)view.getParent();
        if (g.getContext() == null) return;
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT).setAction("确认", null);
        TextView tv = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        tv.setMaxLines(10);
        snackbar.show();
    }

    public static void showDeveloping(View view) {
        show(view, R.string.in_developing);
    }
}
