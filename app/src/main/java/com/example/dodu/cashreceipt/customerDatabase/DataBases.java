package com.example.dodu.cashreceipt.customerDatabase;

import android.provider.BaseColumns;

/**
 * Created by dodu on 15. 5. 24..
 */
public class DataBases {
    /* DataBase contain Attribute(name, contact, payments)
    * primary key id(integer)
    * */
    public static final class CreateDB implements BaseColumns {
        public static final String NAME = "name";
        public static final String CONTACT = "contact";
        public static final String PAYMENTS = "payments";
        public static final String _TABLENAME = "payer";

        public static final String _CREATETABLE =
                "create table "+_TABLENAME+"("
                        +_ID+" integer primary key autoincrement, "
                        +NAME+" text not null , "
                        +CONTACT+" text not null , "
                        +PAYMENTS+" text not null );";
    }
}
