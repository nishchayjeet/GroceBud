package com.example.grocebud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class about extends AppCompatActivity {
    RatingBar ratingBar;
    Button ratingBarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("About App");

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        ratingBarButton = (Button) findViewById(R.id.ratingBarButton);

        ratingBarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double rvalue = ratingBar.getRating();
                Toast.makeText(getApplicationContext(), "Rating:-" + rvalue, Toast.LENGTH_LONG).show();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_about);

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
                        startActivity(new Intent(getApplicationContext(), mygrocery.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_about:

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
                Intent Tologin = new Intent(about.this, login.class);
                startActivity(Tologin);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
