package com.example.grocebud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class get_started extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.nav_home:
                        return true;

                    case R.id.nav_search:
                        startActivity(new Intent(getApplicationContext(),PermissionsActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_list:
                        startActivity(new Intent(getApplicationContext(),mygrocery.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_about:
                        startActivity(new Intent(getApplicationContext(),about.class));
                        overridePendingTransition(0,0);
                        return true;




                }
                return false;
            }
        });
    }
    public void additem(View view){
        Intent add = new Intent(get_started.this,add_item.class);
        startActivity(add);
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
                Intent Tologin = new Intent(get_started.this, login.class);
                startActivity(Tologin);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}

