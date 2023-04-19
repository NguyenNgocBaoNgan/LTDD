package com.example.gk;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public String NAME_TBL, NAME_COLUMN;

    public Database(Context context) {
        super(context, "DOTD", null, 1);
    }

    public Database(Context context, String name_database, int version ) {
        super(context, name_database, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String sql = "create table if not exists " + TABLE_NV +
//                "(" +
//                COLUMN_NV_MA + " text primary key ," +
//                COLUMN_NV_PASS + " text," +
//                COLUMN_NV_HOTEN + " text," +
//                COLUMN_NV_EMAIL + " text," +
//                COLUMN_NV_HA + " bold " +
//                " ) ";
//
//        db.execSQL(sql);
//
//        sql = " insert into " + TABLE_NV + " values ('admin', 'admin', 'Đinh Quốc Đạt', 'nhoctengu2001@gmail.com', null) ";
//        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        int upgrade = oldVersion + 1;
        while (upgrade < newVersion){
            switch (upgrade){
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                default:

                    break;
            }
            upgrade++;
        }
    }

    public void onRun(SQLiteDatabase db, String sql){
        db.execSQL(sql);
    }

}
