package com.example.taskmaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class udpateTimeLoc extends AppCompatActivity {
    EditText TimeEd,LocEd,rateEd;
            Button statusEd;
    Button update,rateUpdate,cancel;
    DataBaseHelper DB;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_udpate_time_loc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        int orderId = getIntent().getIntExtra("ORDER_ID", -1); // -1 is the default value if the key is not found
DB=new DataBaseHelper(this);
        // Get the order ID from the intent
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        SharedPreferences sharedPreferences= getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        //String targetEmail = sharedPreferences.getString("email","email@hotmail.com");
        cancel= findViewById(R.id.button);
        TimeEd= findViewById(R.id.TimeUpd);
        LocEd= findViewById(R.id.LocUpd);
        update=findViewById(R.id.update);
        rateEd=findViewById(R.id.RateUpd);
        rateUpdate=findViewById(R.id.rateBtn);


        //add current data based on user setText
//        statusEd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteOrder(orderId);
//            }
//        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    updateLocTime(orderId);
                }
        });
        rateUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    updateRate(orderId);
                }
        });

      /*  cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
DB.cancelOrder(orderId);
            }
        });*/


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void updateRate(int orderId) {
        String rate = rateEd.getText().toString();
       if (rate.isEmpty()) {
            // Both location and time are empty, show an error message
            Toast.makeText(this, "Please enter  ratings", Toast.LENGTH_SHORT).show();
            return;
        }
        // Get the order ID from your application logic

        // Create an instance of ordermod and set its properties
        ordermod order ;
        DB=new DataBaseHelper(this);
        order=DB.getOrderById(orderId);
        order.setOrderID(orderId);
        if (!rate.isEmpty()) {
            order.setRate(Integer.parseInt(rate));
        }
        boolean isUpdated = DB.updateOrder(order);

        if (isUpdated) {
            // Show a toast message indicating successful update
            Toast.makeText(this, "Rating added successfully", Toast.LENGTH_SHORT).show();
        } else {
            // Show a toast message indicating failure to update
            Toast.makeText(this, "Failed to update Rating", Toast.LENGTH_SHORT).show();
        }
    }





    public void updateLocTime(int id) {
        // Get the edited location and time from the EditText fields
        String newLoc = LocEd.getText().toString();
        String newTime = TimeEd.getText().toString();
//        String status = statusEd.getText().toString();

        // Check if both location and time are empty
        if (newLoc.isEmpty() && newTime.isEmpty()) {
            // Both location and time are empty, show an error message
            Toast.makeText(this, "Please enter a location , time", Toast.LENGTH_SHORT).show();
            return; // Exit the method without further processing
        }

        // Get the order ID from your application logic
        int orderId = id; // Replace getOrderID() with your logic to retrieve the order ID

        // Create an instance of ordermod and set its properties
        ordermod order ;
        DB=new DataBaseHelper(this);
        order=DB.getOrderById(orderId);
        order.setOrderID(orderId);

        // Check if the location was edited and update the order object
        if (!newLoc.isEmpty()) {
            order.setLocation(newLoc);
        }

        // Check if the time was edited and update the order object
        if (!newTime.isEmpty()) {
            order.setTime(newTime);
        }


        // Check if the time was edited and update the order object
//        if (!status.isEmpty()) {
//            order.setOrderStatus(status);
//        }

        // Update the record in the database using the DataBaseHelper
        boolean isUpdated = DB.updateOrder(order);

        if (isUpdated) {
            // Show a toast message indicating successful update
            Toast.makeText(this, "Location or time updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            // Show a toast message indicating failure to update
            Toast.makeText(this, "Failed to update location or time", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent intent = new Intent(this, ActiveOrder.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void updateStatus(int orderId) {


        ordermod order ;
        DB=new DataBaseHelper(this);
        order=DB.getOrderById(orderId);
        order.setOrderID(orderId);

       order.setRate(Integer.parseInt("canclled"));

        boolean isUpdated = DB.updateOrder(order);

        if (isUpdated) {

            Toast.makeText(this, "oder cancled", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(this, "Failed to cancle order", Toast.LENGTH_SHORT).show();
        }
    }





}