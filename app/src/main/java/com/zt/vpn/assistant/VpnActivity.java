package com.zt.vpn.assistant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.zt.vpn.assistant.fragments.DebugInfoFragment;
import com.zt.vpn.assistant.fragments.VpnServerFragment;
import com.zt.vpn.assistant.views.NonSwipeableViewPager;

public class VpnActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_TAB_ID = "extra_tab_id";

    private NonSwipeableViewPager viewPager;
    private RadioButton debugInfoRadioButton;
    private RadioButton vpnServerRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vpn);
        setUpView();
    }

    public void setUpView() {
        viewPager = (NonSwipeableViewPager) findViewById(R.id.pager);
        debugInfoRadioButton = (RadioButton) findViewById(R.id.debugInfo);
        vpnServerRadioButton = (RadioButton) findViewById(R.id.vpnServer);

        debugInfoRadioButton.setOnClickListener(this);
        vpnServerRadioButton.setOnClickListener(this);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(new NonSwipeableViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                checkTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int tabId = intent.getIntExtra(EXTRA_TAB_ID, -1);
        changeTab(tabId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.debugInfo:
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.vpnServer:
                viewPager.setCurrentItem(1, false);
                break;
            default:
                break;
        }
    }

    public void changeTab(int tabId) {
        if (tabId >= 0 && tabId < viewPager.getChildCount()) {
            viewPager.setCurrentItem(tabId);
        }
    }

    private class FragmentPagerAdapter extends FragmentStatePagerAdapter {
        FragmentPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new DebugInfoFragment();
                case 1:
                    return new VpnServerFragment();
            }
            return null;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    private void checkTab(int position) {
        RadioButton[] tabs = {debugInfoRadioButton, vpnServerRadioButton};
        tabs[position].setChecked(true);
    }

    public static void open(Context context, int tabId) {
        open(context, tabId, null);
    }

    public static void open(Context context, int tabId, Bundle args) {
        Intent intent = new Intent(context, VpnActivity.class);
        intent.putExtra(VpnActivity.EXTRA_TAB_ID, tabId);
        if (args != null) {
            intent.putExtras(args);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

}
