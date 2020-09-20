package com.example.dodu.cashreceipt.smsReceiver;

import android.util.Log;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dodu on 15. 5. 18..
 */
public class SmsFilter {

    private static final String[] bankName_kr = {"농협", "신한", "기업", "우리"}; // bankName korea name
    private static final String[] bankName_eng = {"nonghyup", "shinhan", "ibk", "woori"}; // bankName english name

    private String smsBody;
    private String name;

    public SmsFilter(String smsBody) {
        this.smsBody = smsBody;
        this.name = null;
    }

    public String isBankSms() {

        String isBank = null;
        //String[] bankName = bankName_kr;
        String[] bankName = bankName_eng;


        Pattern p;
        Matcher m;

        for(int i=0; i<bankName.length; i++) {
            p = Pattern.compile(bankName[i]);
            m = p.matcher(smsBody);

            if(m.find()){
                isBank = bankName[i];
                break;
            }
        }


        return isBank;
    }

    public String filterName() {

        String bankName = isBankSms();

        if(bankName != null) {
            switch (bankName) {
                //case "농협" : nonghyupFilter(); break;
                case "nonghyup" : nonghyupFilter(); break;
            }
        }
        return name;
    }

    public void nonghyupFilter() {

        StringBuffer  nameLine = new StringBuffer();

        //Pattern p = Pattern.compile("[0-9]+[123456789\\*-]+[0-9]+.[가-힣]{2,}");
        Pattern p = Pattern.compile("[0-9]+[123456789\\*-]+[0-9]+.[a-zA-Z]{4,}");
        Matcher m = p.matcher(smsBody);

        while(m.find()) {
            nameLine.append(m.group());
            System.out.println(nameLine);
        }

        //p = Pattern.compile("[가-힣]+");
        p = Pattern.compile("[a-zA-Z]+");
        m = p.matcher(nameLine);

        while(m.find()) {
            name = m.group();
            Log.i("test : ", "name : " + name);
        }
    }
}