 package com.example.grocebud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
}