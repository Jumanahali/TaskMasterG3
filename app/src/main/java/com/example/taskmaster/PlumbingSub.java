package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class PlumbingSub extends AppCompatActivity {
RelativeLayout w ;
RelativeLayout r;
RelativeLayout f ;
RelativeLayout d ;

Toolbar toolbar, homebar, ordersbar, logoutbar, extraHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumbing_sub);

        w=findViewById(R.id.washbasins);
        w.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), subcategorydetailswashbasins.class);
            startActivity(intent);
        } });

        r=findViewById(R.id.roof);
        r.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), subcategorydetailsroofinsulation.class);
            startActivity(intent);
        } });



        f=findViewById(R.id.water);
        f.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), subcategorydetailswaterfilter.class);
            startActivity(intent);
        } });

        d=findViewById(R.id.dynamo);
        d.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), subcategorydetailsdynamo.class);
            startActivity(intent);
        } });

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

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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