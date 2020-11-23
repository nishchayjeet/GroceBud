package com.example.grocebud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class add_item extends AppCompatActivity {


    private Button BtnSave;
    private EditText inputAsile, InputName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        setTitle("Add New Item");


        InputName = findViewById(R.id.editText5);
        inputAsile = findViewById(R.id.editText7);


        BtnSave = findViewById(R.id.button6);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), get_started.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_search:
                        startActivity(new Intent(getApplicationContext(), search_item.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_list:
                        startActivity(new Intent(getApplicationContext(), about.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_about:
                        startActivity(new Intent(getApplicationContext(), about.class));
                        overridePendingTransition(0, 0);
                        return true;


                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_settings:
                FirebaseAuth.getInstance().signOut();
                Intent Tologin = new Intent(add_item.this, login.class);
                startActivity(Tologin);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}