package com.shablcu.shalu.sqliteexample;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText editname, editsurname, editmarks, editid;
    Button adddatabtn, buttonView, btnupdate, btndelete;
    int num, id1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);
        editid = (EditText) findViewById(R.id.editText_id);
        editname = (EditText) findViewById(R.id.editText_name);
        editsurname = (EditText) findViewById(R.id.editText_surname);
        editmarks = (EditText) findViewById(R.id.editText_marks);
        adddatabtn = (Button) findViewById(R.id.button_add);
        buttonView = (Button) findViewById(R.id.viewbutton);
        btnupdate = (Button) findViewById(R.id.button_update);
        btndelete = (Button) findViewById(R.id.button_delete);
        AddData();
        viewAll();
        Updatedata();
        deletedata();

    }


    public void deletedata() {

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = mydb.deleteData(editid.getText().toString());
                if (deletedRows > 0) {
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_SHORT).show();


                }
            }
        });
    }


    public void Updatedata() {
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = mydb.updatedata(editid.getText().toString(), editname.getText().toString(),
                        editsurname.getText().toString(), editmarks.getText().toString()
                );

                if (isUpdate == true) {
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void AddData() {
        adddatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = mydb.insertData(editname.getText().toString(),
                        editsurname.getText().toString(), editmarks.getText().toString());

                if (isInserted == true) {
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data Not  Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    public void viewAll() {

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = mydb.getAllData();
                if (res.getCount() == 0) {
                    //show message here
                    showMessage("Error", "NO Data Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID:" + res.getString(0) + "\n");
                    buffer.append("NAME:" + res.getString(1) + "\n");
                    buffer.append("SURNAME:" + res.getString(2) + "\n");
                    buffer.append("MARKS:" + res.getString(3) + "\n\n");

                }

                showMessage("DATA ", buffer.toString());
            }
        });
    }

    public void showMessage(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();


    }


}
