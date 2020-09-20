package com.example.dodu.cashreceipt.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dodu.cashreceipt.R;
import com.example.dodu.cashreceipt.shPrefInfo.AppCountInfo;
import com.example.dodu.cashreceipt.shPrefInfo.JoinInfo;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        Intent intent;
        Context context = getApplicationContext();

        // if First run or don't join, join Screen view. else, CashReceive Screen view.
        if(!AppCountInfo.isRunning(context) || !JoinInfo.isJoin(context)) {
            intent = new Intent(getApplicationContext(), JoinActivity.class);
        } else {
            intent = new Intent(getApplicationContext(), CashReceiveActivity.class);
        }
        finish();
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}