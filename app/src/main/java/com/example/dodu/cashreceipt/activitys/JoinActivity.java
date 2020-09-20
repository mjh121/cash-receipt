package com.example.dodu.cashreceipt.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dodu.cashreceipt.R;
import com.example.dodu.cashreceipt.shPrefInfo.JoinInfo;

/**
 * Created by dodu on 15. 5. 29..
 */
public class JoinActivity extends Activity {

    private EditText et_name, et_phoneNumber, et_address, et_storeName, et_businessNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        init();
    }

    public void init() {

        Button btn_submit = (Button) findViewById(R.id.submit);
        Button btn_cancel = (Button) findViewById(R.id.cancel);

        et_name = (EditText) findViewById(R.id.name);
        et_phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        et_address = (EditText) findViewById(R.id.address);
        et_storeName = (EditText) findViewById(R.id.storeName);
        et_businessNumber = (EditText) findViewById(R.id.businessNumber);

        View.OnClickListener btn_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.submit :
                        submit(); break;
                    case R.id.cancel :
                        cancel(); break;
                }
            }
        };

        btn_submit.setOnClickListener(btn_listener);
        btn_cancel.setOnClickListener(btn_listener);
    }

    public void submit() {
        //
        String name = et_name.getText().toString();
        String phoneNumber = et_phoneNumber.getText().toString();
        String address = et_address.getText().toString();
        String storeName = et_storeName.getText().toString();
        String businessNumber = et_businessNumber.getText().toString();

        JoinInfo joinInfo = new JoinInfo(getApplicationContext());
        joinInfo.saveData(name, phoneNumber, address, storeName, businessNumber);

        reset();


        Intent intent = new Intent(this, CashReceiveActivity.class);
        finish();
        startActivity(intent);
    }

    public void reset() {
        // TextField reset text.
        et_name.setText("");
        et_phoneNumber.setText("");
        et_address.setText("");
        et_storeName.setText("");
        et_businessNumber.setText("");
    }

    public void cancel() {
        // activity end.
        finish();
    }
}