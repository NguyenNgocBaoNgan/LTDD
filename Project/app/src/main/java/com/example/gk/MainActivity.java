package com.example.gk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String name_db = "";
    private SQLiteDatabase sql;
    private Database db;

    Dialog dialog;
    Button bt_createDB, bt_createTbl;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_createDB = findViewById(R.id.bt_crDatabase);
        bt_createTbl = findViewById(R.id.bt_crTable);

        db = new Database(MainActivity.this);
        sql = db.getReadableDatabase();


        bt_createDB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openDialog_createDb(MainActivity.this, 0, "Create Database");
            }
        });
        bt_createTbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog_create_table(MainActivity.this, 1, "Create table");
            }
        });

    }

    protected void openDialog_createDb(final Context _context, int _type, String _title){

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

        name_db = String.valueOf(name.getText());

       title.setText(_title);

        bt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = new Database(_context, name_db, 1);

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
        db = new Database(_context);
        sql = db.getReadableDatabase();

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

                        db = new Database(_context, name_db, 1);

                        String _name_tbl = String.valueOf(tbl_name.getText());
                        if(name_db.equals("")){
                            name_db = "DOTD";
                        }
                        System.out.println("name: "+name_db);
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

        name_db = String.valueOf(name.getText());

        title.setText(_title);

        bt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = new Database(_context, name_db, 1);

                String delete = "drop table "+ name;
                db.onRun(sql, delete);
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

}