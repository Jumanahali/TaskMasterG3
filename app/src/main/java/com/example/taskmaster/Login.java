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


public class Login extends AppCompatActivity {
    Toolbar toolbar;
    EditText username, password;
    Button signin, signup;
    DataBaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        username = findViewById(R.id.editTextText2);
        password = findViewById(R.id.editTextTextPassword);
        signin = findViewById(R.id.button6);
        signup = findViewById(R.id.button4);
        DB = new DataBaseHelper(this);


        signin.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            if(user.equals("")||pass.equals(""))
                Toast.makeText(Login.this, "Please fill all the required fields", Toast.LENGTH_SHORT).show();
            else{
                Boolean check = DB.checkUsernamePassword(user, pass);
                if (check){
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", user);
                    editor.apply();
                    Toast.makeText(Login.this, "successful login in ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),categorieshomepage.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Wrong username or password", Toast.LENGTH_SHORT).show(); }
            } }
        });

        signup.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), Signup.class);
            startActivity(intent);
        } });


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle navigation arrow click here
            // For example, start the PlumbingSub activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}