package com.example.gk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String name_db = "";
    private SQLiteDatabase sql;
    private Database db;

    ClassAdapter CA;
    SinhVienAdapter SA;

    Dialog dialog;
    Button bt_createDB, bt_createTbl, bt_insertRowToTbl, bt_updateRowToTbl, bt_insertStudent,
    bt_delTable, bt_dlDatabase, bt_querying_TbL, bt_querying_student;
    EditText ed_delTable, ed_id_class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_createDB = findViewById(R.id.bt_crDatabase);
        bt_dlDatabase = findViewById(R.id.bt_dlDatabase);
        bt_createTbl = findViewById(R.id.bt_crTable);
        bt_delTable = findViewById(R.id.bt_delTable);
        ed_delTable = findViewById(R.id.ed_delTable);
        ed_id_class = findViewById(R.id.ed_id_class);
        bt_insertRowToTbl = findViewById(R.id.bt_insertRowToTbl);
        bt_updateRowToTbl = findViewById(R.id.bt_updateRowToTbl);
        bt_insertStudent = findViewById(R.id.bt_insertStudent);
        bt_querying_TbL = findViewById(R.id.bt_querying_T);
        bt_querying_student = findViewById(R.id.bt_querying_S);



        setSQL();

        bt_createDB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openDialog_createDb(MainActivity.this, 0, "Create Database");
            }
        });
        bt_dlDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog_deleteDb(MainActivity.this, 1, "Drop Database");
            }
        });
        bt_createTbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAGTAG", "openDialog_createDb: ");

                openDialog_create_table(MainActivity.this, 1, "Create table");
            }
        });

        bt_delTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTbl = ed_delTable.getText().toString();
                String delete = "drop table "+ nameTbl;
                db.onRun(sql, delete);

            }
        });
        bt_insertRowToTbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog_insert_row_table(MainActivity.this, 1, "Insert Row to Table");
            }
        });

        bt_updateRowToTbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog_update_row(MainActivity.this, 1, "Insert Row to Table");
            }
        });
        bt_insertStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_class = ed_id_class.getText().toString();
                openDialog_insert_student(MainActivity.this, 1, id_class);

            }
        });

        bt_querying_TbL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog_querying_tbl(MainActivity.this, 1, "Querying TBL");
            }
        });
        bt_querying_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog_querying_student(MainActivity.this, 1, "Querying sinh vien");

            }
        });

    }

    private void setSQL(){
        db = new Database(MainActivity.this);
        sql = db.getWritableDatabase();
    }

    protected void openDialog_createDb(final Context _context, int _type, String _title){

        dialog = new Dialog(_context);
        dialog.setContentView(R.layout.dialog);


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView title = dialog.findViewById(R.id.dialogTextView);
        EditText name = dialog.findViewById(R.id.dialogEditText);
        Button bt_create = dialog.findViewById(R.id.dlog_bt_create);
        Button bt_cancel = dialog.findViewById(R.id.dlog_bt_cancel);

        setSQL();

       title.setText(_title);

        bt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name_db = name.getText().toString();
                title.setText(name_db);
                if (name_db.equals("")){
                    name_db = "DOTD";
                }

                db = new Database(_context, name_db, 1);
                db.getWritableDatabase();



                dialog.dismiss();
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    protected void openDialog_deleteDb(final Context _context, int _type, String _title){

        dialog = new Dialog(_context);
        dialog.setContentView(R.layout.dialog);
        db = new Database(_context);
        sql = db.getReadableDatabase();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView title = dialog.findViewById(R.id.dialogTextView);
        EditText name = dialog.findViewById(R.id.dialogEditText);
        Button bt_create = dialog.findViewById(R.id.dlog_bt_create);
        Button bt_cancel = dialog.findViewById(R.id.dlog_bt_cancel);



        title.setText(_title);

        bt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name_db = String.valueOf(name.getText());
                _context.deleteDatabase(name_db);

                dialog.dismiss();
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    protected void openDialog_create_table(final Context _context, int _type, String _title){

        dialog = new Dialog(_context);
        dialog.setContentView(R.layout.create_table_layout);
        setSQL();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);

        EditText tbl_name = dialog.findViewById(R.id.tableName);
        //text column
        EditText col1_1 = dialog.findViewById(R.id.col1_1);
        EditText col1_2 = dialog.findViewById(R.id.col1_2);
        EditText col2_1 = dialog.findViewById(R.id.col2_1);
        EditText col2_2 = dialog.findViewById(R.id.col2_2);
        EditText col3_1 = dialog.findViewById(R.id.col3_1);
        EditText col3_2 = dialog.findViewById(R.id.col3_2);
        EditText col4_1 = dialog.findViewById(R.id.col4_1);
        EditText col4_2 = dialog.findViewById(R.id.col4_2);
        EditText col5_1 = dialog.findViewById(R.id.col5_1);
        EditText col5_2 = dialog.findViewById(R.id.col5_2);

        Button bt_create = dialog.findViewById(R.id.button_create);
        Button bt_cancel = dialog.findViewById(R.id.button_cancel);



        bt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(name_db.equals("")){
                    name_db = "DOTD";
                }
                db = new Database(_context, name_db, 1);
                sql = db.getWritableDatabase();

                String _name_tbl = String.valueOf(tbl_name.getText());

                String _col1_1 = String.valueOf(col1_1.getText());
                String _col2_1 = String.valueOf(col2_1.getText());
                String _col3_1 = String.valueOf(col3_1.getText());
                String _col4_1 = String.valueOf(col4_1.getText());
                String _col5_1 = String.valueOf(col5_1.getText());

                String _col1_2 = String.valueOf(col1_2.getText());
                String _col2_2 = String.valueOf(col2_2.getText());
                String _col3_2 = String.valueOf(col3_2.getText());
                String _col4_2 = String.valueOf(col4_2.getText());
                String _col5_2 = String.valueOf(col5_2.getText());



                String create_sql = "create table if not exists " + _name_tbl +
                        "(" +
                                _col1_1 + " " + _col1_2+ "," +
                                _col2_1 + " " + _col2_2+ "," +
                                _col3_1 + " " + _col3_2+ "," +
                                _col4_1 + " " + _col4_2+ "," +
                                _col5_1 + " " + _col5_2+
                                " ) ";

                db.onRun(sql, create_sql);

                dialog.dismiss();
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }



    protected void openDialog_insert_row_table(final Context _context, int _type, String _title){

        dialog = new Dialog(_context);
        dialog.setContentView(R.layout.insert_row_to_tbl);
        db = new Database(_context);
        sql = db.getReadableDatabase();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);

        //text column
        EditText nameTbl = dialog.findViewById(R.id.ed_nameTbl);

        EditText row1 = dialog.findViewById(R.id.ed_row1);
        EditText row2 = dialog.findViewById(R.id.ed_row2);
        EditText row3 = dialog.findViewById(R.id.ed_row3);
        EditText row4 = dialog.findViewById(R.id.ed_row4);
        EditText row5 = dialog.findViewById(R.id.ed_row5);

        TextView ex = dialog.findViewById(R.id.ex);

        Button bt_create = dialog.findViewById(R.id.button_create);
        Button bt_cancel = dialog.findViewById(R.id.button_cancel);



        bt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(name_db.equals("")){
                    name_db = "DOTD";
                }
                db = new Database(_context, name_db, 1);
                sql = db.getWritableDatabase();

                System.out.println("name: "+name_db);
                String name_tbl = String.valueOf(nameTbl.getText());
                String _row1 = row1.getText().toString() +"";
                String _row2 = row2.getText().toString() +"";
                String _row3 = row3.getText().toString() +"";
                String _row4 = row4.getText().toString() +"";
                String _row5 = row5.getText().toString() +"";
                try {
                    String create_sql  = " insert into " + name_tbl +
                            " values (" +" '" +_row1 + "'" +"," +" '" + _row2 +"'" + "," +" '"
                            + _row3 + "'" +"," + " '" + _row4 +"'" + ","+ " '" +_row5 +"'" + ")";

//                    ex.setText(create_sql);


                    Toast.makeText(_context, ""+create_sql, Toast.LENGTH_SHORT).show();

                    db.onRun(sql, create_sql);
                }catch (Exception e){
                    Log.d("ERR", "ressul: "+e);
                }

                dialog.dismiss();
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    protected void openDialog_update_row(final Context _context, int _type, String _title){

        dialog = new Dialog(_context);
        dialog.setContentView(R.layout.update_row_tbl);
        db = new Database(_context);
        sql = db.getReadableDatabase();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);


        EditText nameTbl = dialog.findViewById(R.id.ed_nameTbl);

        EditText nameRow1 = dialog.findViewById(R.id.ed_name_row);
        EditText nameRow2 = dialog.findViewById(R.id.ed_name_row2);
        EditText newValue = dialog.findViewById(R.id.ed_new_value);
        EditText conditions = dialog.findViewById(R.id.ed_conditions);

        TextView ex = dialog.findViewById(R.id.ex);


        Button bt_create = dialog.findViewById(R.id.button_create);
        Button bt_cancel = dialog.findViewById(R.id.button_cancel);



        bt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(name_db.equals("")){
                    name_db = "DOTD";
                }
                db = new Database(_context, name_db, 1);
                sql = db.getWritableDatabase();

                System.out.println("name: "+name_db);
                String name_tbl = String.valueOf(nameTbl.getText());
                String _nameRow1 = String.valueOf(nameRow1.getText());
                String _nameRow2 = String.valueOf(nameRow2.getText());
                String _newValue = String.valueOf(newValue.getText());
                String _condition = String.valueOf(conditions.getText());
                try {

                    String create_sql = "update " +name_tbl+  " set " + _nameRow1 + " = "  + " '"+_newValue + "' " + " where " + _nameRow2 + " = '"+_condition+"'";

//                    ex.setText(create_sql);
                    db.onRun(sql, create_sql);
                }catch (Exception e){
                    Log.d("ERR", "ressul: "+e);
                }




                dialog.dismiss();
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    protected void openDialog_insert_student(final Context _context, int _type, String _title){

        dialog = new Dialog(_context);
        dialog.setContentView(R.layout.insert_student);
        db = new Database(_context);
        sql = db.getReadableDatabase();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);



        EditText ed_name = dialog.findViewById(R.id.ed_name_student);
        EditText ed_birthday = dialog.findViewById(R.id.ed_birthday);


        TextView tv_idClass = dialog.findViewById(R.id.tv_idClass);
        TextView ex = dialog.findViewById(R.id.ex);


        Button bt_create = dialog.findViewById(R.id.button_create);
        Button bt_cancel = dialog.findViewById(R.id.button_cancel);

        tv_idClass.setText(_title);

        bt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name_db.equals("")){
                    name_db = "DOTD";
                }
                db = new Database(_context, name_db, 1);
                sql = db.getWritableDatabase();

                System.out.println("name: "+name_db);
                String name_student = String.valueOf(ed_name.getText());
                String birthday = String.valueOf(ed_birthday.getText());
                String id_class = String.valueOf(tv_idClass.getText());
                try {
                    String create_sql = "create table if not exists " + "STUDENT" +
                    "(" +
                    "id" + " integer primary key autoincrement," +
                    "name" + " text," +
                    "birthday" + " text," +
                    "id_class" + " text" +
                    " ) ";
                    db.onRun(sql, create_sql);

                   create_sql  = " insert into " + "STUDENT" +
                            " values (" + "null" +"," +" '" + name_student +"'" + "," +" '"
                            + birthday + "'" +"," + " '" + id_class +"'" + ")";
                    db.onRun(sql, create_sql);


//                    ex.setText(create_sql);

                }catch (Exception e){
                    Log.d("ERR", "ressul: "+e);
                }




                dialog.dismiss();
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    protected void openDialog_querying_tbl(final Context _context, int _type, String _title){

        dialog = new Dialog(_context);
        dialog.setContentView(R.layout.list_view);
        db = new Database(_context);
        sql = db.getReadableDatabase();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);



        TextView tv_title = dialog.findViewById(R.id.dialogTextView);
        EditText ed_name_query = dialog.findViewById(R.id.ed_name_query);


        ListView lv = dialog.findViewById(R.id.list_view);


        Button bt_create = dialog.findViewById(R.id.button_create);
        Button bt_cancel = dialog.findViewById(R.id.button_cancel);

        tv_title.setText(_title);

        bt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name_db.equals("")){
                    name_db = "DOTD";
                }
                db = new Database(_context, name_db, 1);
                sql = db.getWritableDatabase();


                String name_tbl = String.valueOf(ed_name_query.getText());
                ArrayList<Class> ds = new ArrayList<Class>();
                try {

                    Cursor c = sql.rawQuery("select * from " +name_tbl, null);
                    if (c.moveToFirst()){
                        do{
                            String col1 = c.getString(0);
                            String col2 = c.getString(1);
                            String col3 = c.getString(2);
                            String col4 = c.getString(3);
                            String col5 = c.getString(4);
                            Class lh = new Class(col1, col2, col3, col4, col5);
                            ds.add(lh);
                        }while (c.moveToNext());
                    }

                    CA = new ClassAdapter(_context, ds);
                    lv.setAdapter(CA);

                }catch (Exception e){
                    Log.d("ERR", "ressul: "+e);
                }
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    protected void openDialog_querying_student(final Context _context, int _type, String _title){

        dialog = new Dialog(_context);
        dialog.setContentView(R.layout.list_view);
        db = new Database(_context);
        sql = db.getReadableDatabase();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);



        TextView tv_title = dialog.findViewById(R.id.dialogTextView);
        EditText ed_name_query = dialog.findViewById(R.id.ed_name_query);


        ListView lv = dialog.findViewById(R.id.list_view);


        Button bt_create = dialog.findViewById(R.id.button_create);
        Button bt_cancel = dialog.findViewById(R.id.button_cancel);

        tv_title.setText(_title);
        bt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name_db.equals("")){
                    name_db = "DOTD";
                }
                db = new Database(_context, name_db, 1);
                sql = db.getWritableDatabase();


                String name_tbl = String.valueOf(ed_name_query.getText());
                ArrayList<SinhVien> ds = new ArrayList<SinhVien>();
                try {

                    Cursor c = sql.rawQuery("select * from " +"STUDENT" + " where id_class = ?", new String[]{name_tbl});
                    if (c.moveToFirst()){
                        do{
                            Integer col1 = c.getInt(0);
                            String col2 = c.getString(1);
                            String col3 = c.getString(2);
                            String col4 = c.getString(3);
                            SinhVien lh = new SinhVien(col1, col2, col3, col4);
                            ds.add(lh);
                        }while (c.moveToNext());
                    }

                    SA = new SinhVienAdapter(_context, ds);
                    lv.setAdapter(SA);


                }catch (Exception e){
                    Log.d("ERR", "ressul: "+e);
                }


            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}