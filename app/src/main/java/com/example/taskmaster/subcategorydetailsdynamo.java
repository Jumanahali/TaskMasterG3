package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class subcategorydetailsdynamo extends AppCompatActivity {

    Toolbar toolbar;
    EditText location, time;
    Button button;
    DataBaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategorydetailsdynamo);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        location = findViewById(R.id.locationEditText);
        time = findViewById(R.id.orderTimeEditText);
        button = findViewById(R.id.placeOrderButton);
        DB = new DataBaseHelper(this);

        button.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            String loc = location.getText().toString();
            String tim = time.getText().toString();
            if(loc.equals("")||tim.equals(""))
                Toast.makeText(subcategorydetailsdynamo.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            else{
                SharedPreferences sharedPreferences= getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                String targetEmail = sharedPreferences.getString("email","email@hotmail.com");
                ordermod order = new ordermod(0,8, targetEmail, loc, 0,tim , "Kamal", "Accepted", "05434443");
                Boolean insert = DB.addOrder(order);
                if(insert){
                    Toast.makeText(subcategorydetailsdynamo.this, "Successful Order", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),ActiveOrder.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(subcategorydetailsdynamo.this, "Failed to complete the order", Toast.LENGTH_SHORT).show();
                }

            } }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle navigation arrow click here
            // For example, start the PlumbingSub activity
            Intent intent = new Intent(this, PlumbingSub.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}