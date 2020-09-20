package com.example.dodu.cashreceipt.smsReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsMessage;
import android.util.Log;

import com.example.dodu.cashreceipt.activitys.TestActivity;
import com.example.dodu.cashreceipt.customerDatabase.MySQLiteHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dodu on 15. 5. 18..
 */
public class SmsReceiver extends BroadcastReceiver {
    // SmsReceiver is if sms is bank send, data(name, phone number) save in Database

    public static final Uri contactURI =  ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

    private MySQLiteHelper mMySQLiteHelper; // DB manager
    private Context context;
    private String smsBody = null; // sms's content
    private String name = null; // name variable is that Depositor name in smsBody
    private String phone_number = null; // Depositor phone number

    @Override
    public void onReceive(Context context, Intent intent) {
        // sms receiver main
        this.context = context;
        smsBody = getSmsBody(intent);

        SmsFilter smsFilter = new SmsFilter(smsBody);

        name = smsFilter.filterName();

        if(name != null) {
            phone_number = matcheName(name);

            if(phone_number != null) {
                initDB();
                mMySQLiteHelper.insertColumn(name, phone_number, "100,000");
                Log.i("DataBase =", "insert = " + name +", " +phone_number+", "+"100,000");

                // test
                test();
                // end test
            }
        }
    }
    // end

    private void initDB() {
        // DB initialize
        mMySQLiteHelper = new MySQLiteHelper(context);
        mMySQLiteHelper.open();
    }

    public String getSmsBody(Intent intent) {
        // return sms's body
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String message = "";

        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];

            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                message += "\nSMS Body : " + msgs[i].getMessageBody().toString();
            }
        }
        return message;
    } // end

    public Cursor getUri() {
        // contact provider connect, return cursor
        String[] projection = new String[] { ContactsContract.CommonDataKinds.Phone._ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER };

        return context.getContentResolver().query(contactURI, projection, null, null, null);
    }

    public String matcheName(String name) {
        // cursor use, name compare cursor's colum name. if find,return cursor's colum number, else return null.

        Cursor mCursor = getUri();
        Pattern p = Pattern.compile(name);
        Matcher m;
        int name_idx = mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int number_idx = mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

        while(mCursor.moveToNext()) { // while
            m = p.matcher(mCursor.getString(name_idx));
            Log.i("contactData : ", mCursor.getString(name_idx) + " " + mCursor.getString(number_idx));

            if(m.find()) {
                Log.i("phone_number : ", mCursor.getString(number_idx));
                return mCursor.getString(number_idx);
            }
        } // end while
        return null;
    } // end


    public void test() {

        Intent smsIntent = new Intent(context, TestActivity.class);
        smsIntent.putExtra("NAME", name);
        smsIntent.putExtra("PHONE_NUMBER", phone_number);
        smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(smsIntent);
    }

} // end class