package com.example.dodu.cashreceipt.shPrefInfo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dodu on 15. 5. 29..
 */
public class AppCountInfo {
    // this class contain App running Count Information

    private static int countValue = 0;
    public static final String CLASSNAME = "AppCountInfo";

    public AppCountInfo(Context context) {

        super();

        setCountValue(context);
    }

    private static void setCountValue(Context context) {
        // count variable is app running count.
        // when app nunning, count 1 increase.
        SharedPreferences shPref = context.getSharedPreferences(CLASSNAME, 0);
        countValue  = shPref.getInt("Count", -100);

        if(countValue == -100) {
            countValue = 1;
        } else {
            countValue++;
        }

        SharedPreferences.Editor prefEditor = shPref.edit();
        prefEditor.putInt("Count", countValue);
        prefEditor.commit();
    }


    public static boolean isRunning(Context context) {
        // if App Running Count is 1, return true. else, return false
        if(countValue == 0) {
            setCountValue(context);
        }

        if(countValue == 1)
            return false;
        else
            return true;
    }
}