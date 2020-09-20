package com.example.dodu.cashreceipt.shPrefInfo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dodu on 15. 5. 29..
 */
public class JoinInfo {
    // JoinInfo data's name
    public static final String JOIN_NAME = "name";
    public static final String JOIN_PHONENUMBER = "phoneNumber";
    public static final String JOIN_ADDRESS = "address";
    public static final String JOIN_STORENAME = "storeName";
    public static final String JOIN_BUSINESSNUMBER = "businessNumber";
    public static final String JOIN_ISJOIN = "isJoin";
    public static final String CLASSNAME = "JoinInfo";

    private static boolean boolJoin = false; // Whether user's join

    private static SharedPreferences shPref;

    private static void setboolJoin(Context context) {
        shPref = context.getSharedPreferences(CLASSNAME, 0);

        boolJoin  = shPref.getBoolean(JOIN_ISJOIN, false);
    }

    public static boolean isJoin(Context context) {
        // return Whether user's join
        if(shPref == null) {
            setboolJoin(context);
        }
        return boolJoin;
    }

    public JoinInfo(Context context) {

        if(isJoin(context)) {
            shPref.getString(JOIN_NAME, null);
            shPref.getString(JOIN_PHONENUMBER, null);
            shPref.getString(JOIN_ADDRESS, null);
            shPref.getString(JOIN_STORENAME, null);
            shPref.getString(JOIN_BUSINESSNUMBER, null);
        }
    }

    public void saveData(String name, String phoneNumber, String address, String storeName, String businessNumber) {
        // join Data save.
        SharedPreferences.Editor editor = shPref.edit();

        editor.putString(JOIN_NAME, name);
        editor.putString(JOIN_PHONENUMBER, phoneNumber);
        editor.putString(JOIN_ADDRESS, address);
        editor.putString(JOIN_STORENAME, storeName);
        editor.putString(JOIN_BUSINESSNUMBER, businessNumber);
        editor.putBoolean(JOIN_ISJOIN, true);
        editor.commit();
    }

    public String getData(String key) {
        //  return key match joiner data.
        String data = null;

        switch (key) {
            case JOIN_NAME :
                data = shPref.getString(JOIN_NAME, null); break;
            case JOIN_PHONENUMBER :
                data = shPref.getString(JOIN_PHONENUMBER, null); break;
            case JOIN_BUSINESSNUMBER :
                data = shPref.getString(JOIN_BUSINESSNUMBER, null); break;
        }
        return data;
    }
}