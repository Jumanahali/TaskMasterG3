package com.example.taskmaster;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.content.SharedPreferences;





import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.List;
//try11
public class ActiveOrder extends AppCompatActivity {

    Toolbar homebar, ordersbar, logoutbar, extraHome;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_order);

        homebar = findViewById(R.id.homebutton);
        ordersbar = findViewById(R.id.ordersbutton);
        logoutbar = findViewById(R.id.logoutbutton);
        extraHome = findViewById(R.id.extraHome);

        homebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), categorieshomepage.class);
                startActivity(intent);
            }
        });

        extraHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), categorieshomepage.class);
                startActivity(intent);
            }
        });
        ordersbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActiveOrder.class);
                startActivity(intent);
            }
        });

        logoutbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        // Get the order ID from the intent
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        SharedPreferences sharedPreferences= getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String targetEmail = sharedPreferences.getString("email","email@hotmail.com");
        int i = 1;


        while (true) {
            ordermod order = dbHelper.getOrderById(i);
            if (order == null) {
                // No more orders available, exit the loop
                break;
            }

            if (order.getClientID().equals(targetEmail)) {
                displayOrderDetails(order);
            }
            i++;


        }
        // Set up search functionality
        android.widget.SearchView searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterOrders(newText);
                return true;
            }
        });


    }
    private void displayOrderDetails(ordermod order) {
        // Inflate the appropriate layout based on the order status
        View orderItemView;
        if (order.getOrderStatus().equals("Accepted")) {
            orderItemView = LayoutInflater.from(this).inflate(R.layout.activity_order_item, null);
        } else {
            orderItemView = LayoutInflater.from(this).inflate(R.layout.activity_order_item_rejected, null);
        }

        // Find views inside the order item layout
        TextView orderLocationTextView = orderItemView.findViewById(R.id.loc);
        TextView orderTimeTextView = orderItemView.findViewById(R.id.textClock);
        TextView orderWorkerNameTextView = orderItemView.findViewById(R.id.WorkerInfo);
        TextView orderStatusTextView = orderItemView.findViewById(R.id.orderStatus);
        TextView serviceNameTextView = orderItemView.findViewById(R.id.orderName);
        TextView servicePriceTextView = orderItemView.findViewById(R.id.amount);
        Button delete=orderItemView.findViewById(R.id.CancleOreder);
        Button editButton=orderItemView.findViewById(R.id.editButton);
        TextView rateEd=orderItemView.findViewById(R.id.rating);
        if(order.getRate()!=0){
        rateEd.setText(order.getRate()+"");}

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass the order ID to the update activity
                Intent intent = new Intent(getApplicationContext(), udpateTimeLoc.class);
                intent.putExtra("ORDER_ID", order.getOrderID()); // Pass the order ID as an extra
                startActivity(intent);
            }
        });

//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                DataBaseHelper dbHelper = new DataBaseHelper(getApplicationContext());
//               order.setOrderStatus("rejected");
//                dbHelper.updateOrder(order);
//                dbHelper.close();
//            }
//        });
        // Set order details to the views
        orderLocationTextView.setText(order.getLocation());
        orderTimeTextView.setText(order.getTime());
        orderWorkerNameTextView.setText(order.getWorkerName() + " " + order.getWorkerPhone());
        orderStatusTextView.setText(order.getOrderStatus());

        // Retrieve and set service details
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        servicemod service = dbHelper.getserviceById(order.getServiceID());
        if (service != null) {
            serviceNameTextView.setText(service.getSubcategory());
            servicePriceTextView.setText(String.valueOf(service.getPrice()));


        } else {
            // Handle case where service details for the order are not available
        }

        // Set margins for the order item view
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(10, 10, 10, 10);
        orderItemView.setLayoutParams(params);

        // Add the order item view to the appropriate container in your layout
        LinearLayout ordersContainer = findViewById(R.id.ordersContainer);
        ordersContainer.addView(orderItemView);
    }

    private void filterOrders(String query) {
        // Get all orders again to ensure fresh data
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        SharedPreferences sharedPreferences= getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String targetEmail = sharedPreferences.getString("email","email@hotmail.com");

        int i = 1;
        List<ordermod> allOrders = new ArrayList<>();

        while (true) {
            ordermod order = dbHelper.getOrderById(i);
            if (order == null) {
                // No more orders available, exit the loop
                break;
            }

            if (order.getClientID().equals(targetEmail)) {
                allOrders.add(order);
            }
            i++;
        }

        // Filter orders based on the query
        List<ordermod> filteredOrders = new ArrayList<>();
        for (ordermod order : allOrders) {
            servicemod service = dbHelper.getserviceById(order.getServiceID());
            if (service != null && service.getSubcategory().toLowerCase().contains(query.toLowerCase())) {
                filteredOrders.add(order);
            }
        }

        // Clear previous order views
        LinearLayout ordersContainer = findViewById(R.id.ordersContainer);
        ordersContainer.removeAllViews();

        // Display filtered orders
        for (ordermod order : filteredOrders) {
            displayOrderDetails(order);
        }
        TextView noOrdersTextView = findViewById(R.id.noOrdersTextView);
        if (filteredOrders.isEmpty()) {
            noOrdersTextView.setVisibility(View.VISIBLE);
        } else {
            noOrdersTextView.setVisibility(View.GONE);
        }
    }


}
