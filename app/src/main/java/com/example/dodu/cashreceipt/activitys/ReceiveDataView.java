package com.example.dodu.cashreceipt.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dodu.cashreceipt.R;

/**
 * Created by dodu on 15. 6. 5..
 */
public class ReceiveDataView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receive_data_view);

        Intent intent = getIntent();

        TextView name = (TextView) findViewById(R.id.name);
        TextView number = (TextView) findViewById(R.id.number);
        TextView payments = (TextView) findViewById(R.id.payments);

        name.setText(intent.getStringExtra(CashReceiveActivity.NAME));
        number.setText(intent.getStringExtra(CashReceiveActivity.PHONE_NUMBER));
        payments.setText(intent.getStringExtra(CashReceiveActivity.PAYMENTS));
    }
}
