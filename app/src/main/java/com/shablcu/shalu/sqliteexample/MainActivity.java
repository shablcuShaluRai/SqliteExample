package com.shablcu.shalu.sqliteexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
DatabaseHelper mydb;
    EditText editname,editsurname,editmarks;
    Button adddatabtn;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb =  new DatabaseHelper(this);

        editname=(EditText)findViewById(R.id.editText_name);
        editsurname=(EditText)findViewById(R.id.editText_surname);
        editmarks=(EditText)findViewById(R.id.editText_marks);
       adddatabtn=(Button)findViewById(R.id.button_add);
        AddData();

    }


    public void AddData(){
        adddatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted=mydb.insertData(editname.getText().toString(),
                        editsurname.getText().toString(),
                        num = Integer.parseInt(editmarks.getText().toString()));

                if (isInserted=true) {
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Data Not  Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}
