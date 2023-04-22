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

        String sql = "create table if not exists " + "Class" +
                "(" +
                "id" + " text primary key ," +
                "name" + " text," +
                "teacher" + " text," +
                "count" + " text," +
                "top" + " text " +
                " ) ";

        db.execSQL(sql);

        sql = " insert into " + "Class" + " values ('1', '!2A1', 'Bao Ngan', '32', '3') ";
        db.execSQL(sql);

        sql = "create table if not exists " + "STUDENT" +
                "(" +
                "id" + " integer primary key autoincrement," +
                "name" + " text," +
                "birthday" + " text," +
                "id_class" + " text" +
                " ) ";
        db.execSQL(sql);

        sql  = " insert into " + "STUDENT" +
                " values (null, 'Quoc Dat', '23/07/2002', '1' )";
        db.execSQL(sql);
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
