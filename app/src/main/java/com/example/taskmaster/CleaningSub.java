package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;


public class CleaningSub extends AppCompatActivity {
    RelativeLayout d ;
    RelativeLayout s;
    RelativeLayout c ;
    RelativeLayout r ;

    Toolbar toolbar, homebar, ordersbar, logoutbar, extraHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning_sub);

        d=findViewById(R.id.deep);
        d.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), subcategoreydetails.class);
            startActivity(intent);
        } });



        s=findViewById(R.id.shoe);
        s.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), SubactegorydetailsshoeActivity.class);
            startActivity(intent);
        } });

        c=findViewById(R.id.cloth);
        c.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), subcategorydetailsclotheswashing.class);
            startActivity(intent);
        } });

        r=findViewById(R.id.carpet);
        r.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), subcategorydetailscarpetwash.class);
            startActivity(intent);
        } });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle navigation arrow click here
            // For example, start the PlumbingSub activity
            Intent intent = new Intent(this, categorieshomepage.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}