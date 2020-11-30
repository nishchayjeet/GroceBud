package com.example.grocebud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class myList extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    List<String> groceryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);
        setTitle("Item List");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_list);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(),get_started.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_search:
                        startActivity(new Intent(getApplicationContext(),search_item.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_list:

                        return true;

                    case R.id.nav_about:
                        startActivity(new Intent(getApplicationContext(),about.class));
                        overridePendingTransition(0,0);
                        return true;




                }
                return false;
            }
        });
        groceryList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(groceryList);

        //    recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


        groceryList.add("Air Freshners");
        groceryList.add("Aloe Vera Juices");
        groceryList.add("Alumininum Foil");
        groceryList.add("Anchovies");
        groceryList.add("Antipasto");
        groceryList.add("Apple Cider");
        groceryList.add("Apple Sauce");
        groceryList.add("Artificial Sweetners");
        groceryList.add("Bacon");
        groceryList.add("Bacon Bites");
        groceryList.add("Baking Cups");
        groceryList.add("Baking Pans");
        groceryList.add("Baking Soda");
        groceryList.add("Bathroom Cleaners");
        groceryList.add("Bathroom Tissues");
        groceryList.add("BBQ Sauce");
        groceryList.add("Beans");
        groceryList.add("Beer");
        groceryList.add("Beer Kids");
        groceryList.add("Bird Food");
        groceryList.add("Birthday Candles");
        groceryList.add("Breakfast Powders");
        groceryList.add("Broth");
        groceryList.add("Cake Mix");
        groceryList.add("Calamari");
        groceryList.add("Candles & Difusers");
        groceryList.add("Candy");
        groceryList.add("Canned Fruit");
        groceryList.add("Caramel Spread");
        groceryList.add("Cheese Balls");
        groceryList.add("Cherries");
        groceryList.add("Chestnuts");
        groceryList.add("Chewing Gums");
        groceryList.add("Chicken Strios");
        groceryList.add("Chili");
        groceryList.add("Chips");
        groceryList.add("Chocolate");
        groceryList.add("Chutneys");
        groceryList.add("Clam Juice");
        groceryList.add("Clamato Rimmer");
        groceryList.add("Cleaners");
        groceryList.add("Cocktail Mixes");
        groceryList.add("Coconut Milk");
        groceryList.add("Coconut Oil");
        groceryList.add("Coffee");
        groceryList.add("Coffee Cremer/Whitener");
        groceryList.add("Coffee Filters");
        groceryList.add("Condensed Milk");
        groceryList.add("Cookie Cutters");
        groceryList.add("Cookies");
        groceryList.add("Corn Flour");
        groceryList.add("Corn Milk");
        groceryList.add("Cranberries");
        groceryList.add("Eggroll Wraps");
        groceryList.add("Dust Pans");
        groceryList.add("Energy Drinks");
        groceryList.add("Evaporated Milk");
        groceryList.add("Fish Food");
        groceryList.add("Fish Sauce");
        groceryList.add("Flat Breads");
        groceryList.add("Floor Cleaner");
        groceryList.add("Flour");
        groceryList.add("Flavours");
        groceryList.add("Fresh Herbs");
        groceryList.add("Fired Onions");
        groceryList.add("Fruit Cups");
        groceryList.add("Fruit Bars");
        groceryList.add("Furniture Pollish");
        groceryList.add("Garbage Bags");
        groceryList.add("Gelatin");
        groceryList.add("Glaze");
        groceryList.add("Gloves");
        groceryList.add("Gold Fish ");
        groceryList.add("Granola Bars");
        groceryList.add("Gravy");
        groceryList.add("Hamburger");
        groceryList.add("Hash Browns");
        groceryList.add("Hawalin Punch ");
        groceryList.add("Iced Creams");
        groceryList.add("Ice");
        groceryList.add("Iced Tea");
        groceryList.add("Iicing Sugar");
        groceryList.add("Indian and Thai Sauces");
        groceryList.add("International Sauces");
        groceryList.add("Jalapeno ");
        groceryList.add("Jam");
        groceryList.add("Juices Boxes");
        groceryList.add("Ketchup");

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_settings:
                FirebaseAuth.getInstance().signOut();
                Intent Tologin = new Intent(myList.this, login.class);
                startActivity(Tologin);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }



}