package com.example.singup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity<Database> extends AppCompatActivity {
EditText editText0;
EditText editText1;
EditText editText2;
Button button;
Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText0=findViewById(R.id.edittextname);
        editText1=findViewById(R.id.editTextEmail);
        editText2=findViewById(R.id.editTextPassword);
        button=findViewById(R.id.btn);
        button1=findViewById(R.id.btn1);


        database db=new database(this);
        SQLiteDatabase Sa=db.getReadableDatabase();

        button.setOnClickListener(v -> {

            String name = editText0.getText().toString();
            String email=editText1.getText().toString();
            String password=editText2.getText().toString();



         boolean response=   db.putData(name,email,password);

         if (response=true){
             Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_SHORT).show();
         }else{
             Toast.makeText(getApplicationContext(), "Not inserted", Toast.LENGTH_SHORT).show();
         }


        });

        button1.setOnClickListener(v -> {

             Cursor  viewdata=db.viewdata();
             if (viewdata.getCount()==0){

                 Toast.makeText(this, "no data found", Toast.LENGTH_SHORT).show();
             }
             else {
                 StringBuffer SB=new StringBuffer();
                 while (viewdata.moveToNext()){

                     SB.append("name: ").append(viewdata.getString(1)+"\n");
                     SB.append("username: ").append(viewdata.getString(2)+"\n");
                     SB.append("password: ").append(viewdata.getString(3)+"\n  \n \n");
                 }
                 AlertDialog.Builder Ad= new AlertDialog.Builder(MainActivity.this);
                 Ad.setMessage(SB.toString());
                 Ad.setCancelable(true);
                 Ad.setTitle("Data");
                 Ad.setIcon(R.drawable.check);
                 Ad.show();

             }
        });




    }
    @Override
    public void onBackPressed(){
        AlertDialog.Builder alrt=new AlertDialog.Builder(MainActivity.this);
        alrt.setTitle("are you sure to want exit");
        alrt.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
//
            }
        });
        alrt.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alrt.setCancelable(true);
            }
        });
        alrt.create();
        alrt.show();

    }

}