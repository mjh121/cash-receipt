package com.example.dodu.cashreceipt.activitys;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.dodu.cashreceipt.R;
import com.example.dodu.cashreceipt.customerDatabase.DataBases;
import com.example.dodu.cashreceipt.customerDatabase.MySQLiteHelper;


public class TestActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);

        Intent intent  = getIntent();
        TextView name = (TextView) findViewById(R.id.name);
        TextView number = (TextView) findViewById(R.id.number);
        TextView DBview = (TextView) findViewById(R.id.dbview);

        name.setText(intent.getStringExtra("NAME"));
        number.setText(intent.getStringExtra("PHONE_NUMBER"));
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(getApplicationContext());
        mySQLiteHelper.open();

        Cursor cursor = mySQLiteHelper.getAllColumn();

        Log.i("COUNT = ", "DB count : "+cursor.getCount());

        int id_index = cursor.getColumnIndex(DataBases.CreateDB._ID);
        int name_index = cursor.getColumnIndex(DataBases.CreateDB.NAME);
        int contact_index = cursor.getColumnIndex(DataBases.CreateDB.CONTACT);
        int payments_index = cursor.getColumnIndex(DataBases.CreateDB.PAYMENTS);

        while(cursor.moveToNext()) {
            String db_content = cursor.getString(id_index)+", "+cursor.getString(name_index)+", "+cursor.getString(contact_index)+", "+cursor.getString(payments_index);
            DBview.setText(DBview.getText()+"\n"+db_content+"\n");
        }
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
