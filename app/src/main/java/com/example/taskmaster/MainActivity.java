package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    Button signup, login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup = findViewById(R.id.buttons);
        login = findViewById(R.id.buttonl);
        login.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        } });

        signup.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), Signup.class);
            startActivity(intent);
        } });



        DataBaseHelper dbhelper = new DataBaseHelper(this);

        clientmod client1 = new clientmod("Ahmad Mustafa", 1, "amustafa@hotmail.com", "ahmad1111");
        clientmod client2 = new clientmod("Nora Ali", 2, "nali@gmail.com", "ahmad1111");
        clientmod client3 = new clientmod("Hamid Abdullah", 3, "habdullah@yahoo.com", "ahmad1111");
        clientmod client4 = new clientmod("Abbud Obeid", 4, "aobeid@hotmail.com", "ahmad1111");
        clientmod client5 = new clientmod("Arfa Ansari", 5, "ansari@gmail.com", "ahmad1111");
        clientmod client6 = new clientmod("Shireen Tamer", 6, "stamer@yahoo.com", "ahmad1111");
        clientmod client7 = new clientmod("Ayman Yousuf", 7, "ayousuf@hotmail.com", "ahmad1111");
        clientmod client8 = new clientmod("Dameer Sattar", 8, "dsattar@gmail.com", "ahmad1111");
        clientmod client9 = new clientmod("Ibrahim Khalil", 9, "ikhalil@yahoo.com", "ahmad1111");

        dbhelper.addClient(client1);
        dbhelper.addClient(client2);
        dbhelper.addClient(client3);
        dbhelper.addClient(client4);
        dbhelper.addClient(client5);
        dbhelper.addClient(client6);
        dbhelper.addClient(client7);
        dbhelper.addClient(client8);
        dbhelper.addClient(client9);

        servicemod service1 = new servicemod("Cleaning", 1, "Shoe Wash", 75, "", "@drawable/washbasins");
        servicemod service2 = new servicemod("Cleaning", 2, "Deep cleaning", 500, "", "@drawable/washbasins");
        servicemod service3 = new servicemod("Cleaning", 3, "Clothes Washing", 300, "", "@drawable/washbasins");
        servicemod service4 = new servicemod("Cleaning", 4, "Carpet Wash", 150, "", "@drawable/washbasins");
        servicemod service5 = new servicemod("Plumbing", 5, "Washbasins", 100, "", "@drawable/washbasins");
        servicemod service6 = new servicemod("Plumbing", 6, "Roof Insulation", 200, "", "@drawable/washbasins");
        servicemod service7 = new servicemod("Plumbing", 7, "Water Filter", 75, "", "@drawable/washbasins");
        servicemod service8 = new servicemod("Plumbing", 8, "Dynamo", 500, "", "@drawable/washbasins");

        dbhelper.addService(service1);
        dbhelper.addService(service2);
        dbhelper.addService(service3);
        dbhelper.addService(service4);
        dbhelper.addService(service5);
        dbhelper.addService(service6);
        dbhelper.addService(service7);
        dbhelper.addService(service8);

        ordermod order1 = new ordermod(1, 1, "amustafa@hotmail.com", "P.O.Box: 55570", 5, "2:00", "Khalid ", "Accepted", "051234567");
        ordermod order2 = new ordermod(2, 5, "amustafa@hotmail.com", "P.O.Box: 88910", 0, "3:00", "Muhammad ", "Rejected", "054567890");
        ordermod order3 = new ordermod(3, 7, "ikhalil@yahoo.com", "P.O.Box: 21634", 0, "2:00", "Asif ", "Accepted", "059378378");
        dbhelper.addOrder(order1);
        dbhelper.addOrder(order2);
        dbhelper.addOrder(order3);



    }







}