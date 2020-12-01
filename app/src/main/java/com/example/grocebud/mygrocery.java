 package com.example.grocebud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

 public class mygrocery extends AppCompatActivity {

    RecyclerView recview;
    myadapter myadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygrocery);

        recview = (RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager((new LinearLayoutManager(this)));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Data"), model.class)
                        .build();

        myadapter = new myadapter(options);
        recview.setAdapter(myadapter);
    }
     @Override
     protected void onStart() {
         super.onStart();
         myadapter.startListening();
     }

     @Override
     protected void onStop() {
         super.onStop();
         myadapter.stopListening();
     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu)
     {
         getMenuInflater().inflate(R.menu.searchmenu,menu);
         MenuItem item = menu.findItem(R.id.search);
         SearchView searchView = (SearchView)item.getActionView();
         searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
             @Override
             public boolean onQueryTextSubmit(String query) {
                 processsearch(query);
                 return false;
             }

             @Override
             public boolean onQueryTextChange(String newText) {
                 processsearch(newText);
                 return false;
             }
         });
         return super.onCreateOptionsMenu(menu);
     }

     private void processsearch(String query) {
         FirebaseRecyclerOptions<model> options =
                 new FirebaseRecyclerOptions.Builder<model>()
                         .setQuery(FirebaseDatabase.getInstance().getReference().child("Data").orderByChild("name").startAt(query).endAt(query+"\uf8ff"), model.class)
                         .build();

         myadapter = new myadapter(options);
         myadapter.startListening();
         recview.setAdapter(myadapter);
     }
 }