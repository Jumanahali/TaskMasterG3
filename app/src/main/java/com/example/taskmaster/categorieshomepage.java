package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class categorieshomepage extends AppCompatActivity {
    RelativeLayout p ;
    RelativeLayout c ;

    Toolbar homebar, ordersbar, logoutbar, extraHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorieshomepage);
        p=findViewById(R.id.plumb);
        p.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), PlumbingSub.class);
            startActivity(intent);
        } });

        c=findViewById(R.id.clean);
        c.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), CleaningSub.class);
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
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

    }

}