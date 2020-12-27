package com.example.android_num_30_sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DataBase DataBase;
    EditText Name, Level, Site, ID;
    Button Add_data, All_view, UpDate, Delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBase = new DataBase(this);

        Name = (EditText)findViewById(R.id.name);
        Level = (EditText)findViewById(R.id.level);
        Site = (EditText)findViewById(R.id.site);
        ID = (EditText)findViewById(R.id.id);

        Add_data = (Button)findViewById(R.id.adddata);
        All_view = (Button)findViewById(R.id.viewall);
        UpDate = (Button)findViewById(R.id.update);
        Delete = (Button)findViewById(R.id.delete);
        addData();
        viewAll();
        Update();
        deleteData();
    }
    public void addData(){
        Add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = DataBase.insertData(Name.getText().toString(),
                                Level.getText().toString(),
                                Site.getText().toString());
                if (isInserted == true){
                    Toast.makeText(MainActivity.this, "ADD DATA", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Not ADD DATA", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void viewAll(){
        All_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DataBase.getData();
                if (res.getCount() == 0){
                    showMsg("Error", "Nothing");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID : " + res.getString(0) + "\n");
                    buffer.append("NAME : " + res.getString(1) + "\n");
                    buffer.append("LEVEL : " + res.getString(2) + "\n");
                    buffer.append("SITE: " + res.getString(3) + "\n\n");
                }
                showMsg("SCP Data", buffer.toString());
            }
        });
    }
    public void showMsg(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void Update(){
        UpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = DataBase.updateData(ID.getText().toString(),
                        Name.getText().toString(),
                        Level.getText().toString(),
                        Site.getText().toString());
                if (isUpdate == true){
                    Toast.makeText(MainActivity.this, "DATA Update", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "DATA Not Update", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void deleteData(){
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows = DataBase.deleteData(ID.getText().toString());
                if (deleteRows > 0){
                    Toast.makeText(MainActivity.this, "DATA Delete", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "DATA Not Delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}