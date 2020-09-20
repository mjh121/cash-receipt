package com.example.dodu.cashreceipt.activitys;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dodu.cashreceipt.R;
import com.example.dodu.cashreceipt.customerDatabase.DataBases;
import com.example.dodu.cashreceipt.customerDatabase.MySQLiteHelper;

/**
 * Created by dodu on 15. 5. 29..
 */
public class CashReceiveActivity extends Activity {

    public static final String NAME = "name";
    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String PAYMENTS = "payments";

    private MySQLiteHelper mMySQLiteHelper;
    private Cursor mCursor;

    private EditText input_name, input_phoneNumber, input_payments;
    private String name, phoneNumber, payments;
    private int id;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cashreceive);

        init();
        initDB();
        autoInputData();
    }

    private void init() {

        Button btn_send = (Button) findViewById(R.id.btn_send);

        input_name = (EditText) findViewById(R.id.input_name);
        input_phoneNumber = (EditText) findViewById(R.id.input_phoneNumber);
        input_payments = (EditText) findViewById(R.id.input_payments);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendReceive();
            }
        });

    }

    private void initDB() {
        mMySQLiteHelper = new MySQLiteHelper(getApplicationContext());
        mMySQLiteHelper.open();

        mCursor = mMySQLiteHelper.getAllColumn();
    }

    public void sendReceive() {

        Intent intent = new Intent(this, ReceiveDataView.class);

        intent.putExtra(NAME, name);
        intent.putExtra(PHONE_NUMBER, phoneNumber);
        intent.putExtra(PAYMENTS, payments);

        startActivity(intent);

        mMySQLiteHelper.deleteColumn(id);
        Log.d("intent success? : ", "success");
    }

    private void autoInputData() {


        if(mCursor.moveToNext()) {
            id = mCursor.getInt(mCursor.getColumnIndex(DataBases.CreateDB._ID));
            name = mCursor.getString(mCursor.getColumnIndex(DataBases.CreateDB.NAME));
            phoneNumber = mCursor.getString(mCursor.getColumnIndex(DataBases.CreateDB.CONTACT));
            payments = mCursor.getString(mCursor.getColumnIndex(DataBases.CreateDB.PAYMENTS));
        }

        input_name.setText(name);
        input_phoneNumber.setText(phoneNumber);
        input_payments.setText(payments);
    }
}
