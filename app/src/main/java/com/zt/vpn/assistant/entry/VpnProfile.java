package com.zt.vpn.assistant.entry;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author
 */
public class VpnProfile implements Parcelable {

    public static ArrayList<VpnProfile> parse(JSONArray array) {
        ArrayList<VpnProfile> items = new ArrayList<>();
        if (array == null) {
            return items;
        }
        for (int i = 0; i < array.length(); ++i) {
            JSONObject j = array.optJSONObject(i);
            Builder b = VpnProfile.newBuilder()
                    .userName(j.optString("userName").trim())
                    .password(j.optString("password").trim())
                    .address(j.optString("address").trim())
                    .session("vpn_" + j.optString("id").trim())
                    .area(j.optString("area").trim());
            items.add(b.build());
        }
        return items;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String pPassword) {
        password = pPassword;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String pSession) {
        session = pSession;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String pAddress) {
        address = pAddress;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String pArea) {
        area = pArea;
    }

    public String getUserName() {
        return userName;
    }

    private String userName;

    @Override
    public String toString() {
        return "VpnProfile{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", session='" + session + '\'' +
                ", address='" + address + '\'' +
                ", area='" + area + '\'' +
                '}';
    }

    private String password;
    private String session;
    private String address;
    private String area;

    private VpnProfile(Builder builder) {
        password = builder.password;
        session = builder.session;
        area = builder.area;
        userName = builder.userName;
        address = builder.address;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(VpnProfile copy) {
        Builder builder = new Builder();
        builder.password = copy.getPassword();
        builder.session = copy.getSession();
        builder.area = copy.getArea();
        builder.address = copy.getAddress();
        builder.userName = copy.getUserName();
        return builder;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.password);
        dest.writeString(this.session);
        dest.writeString(this.address);
        dest.writeString(this.area);
        dest.writeString(this.userName);
    }

    public VpnProfile() {
    }

    protected VpnProfile(Parcel in) {
        this.password = in.readString();
        this.session = in.readString();
        this.address = in.readString();
        this.area = in.readString();
        this.userName = in.readString();
    }

    public static final Creator<VpnProfile> CREATOR = new Creator<VpnProfile>() {
        @Override
        public VpnProfile createFromParcel(Parcel source) {
            return new VpnProfile(source);
        }

        @Override
        public VpnProfile[] newArray(int size) {
            return new VpnProfile[size];
        }
    };

    public static final class Builder {
        private String password;
        private String session;
        private String area;
        private String userName;
        private String address;

        private Builder() {
        }

        public Builder address(String val) {
            address = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder session(String val) {
            session = val;
            return this;
        }

        public Builder area(String val) {
            area = val;
            return this;
        }

        public Builder userName(String val) {
            userName = val;
            return this;
        }

        public VpnProfile build() {
            return new VpnProfile(this);
        }
    }
}
