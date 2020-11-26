package com.example.grocebud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class add_item extends AppCompatActivity {


    RecyclerView recyclerView;
    private Button BtnSave;
    private EditText inputAsile, InputName;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Upload upload;

    String nm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        setTitle("Add New Item");


        InputName = findViewById(R.id.editText5);
        inputAsile = findViewById(R.id.editText7);
        BtnSave = findViewById(R.id.button_save);
        upload = new Upload();
        recyclerView = findViewById(R.id.recyclerview_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = firebaseDatabase.getInstance().getReference().child("Data");

        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload.setName(InputName.getText().toString());
                upload.setAsile(inputAsile.getText().toString());

                String id = databaseReference.push().getKey();
                databaseReference.child(id).setValue(upload);
                Toast.makeText(add_item.this,"Data Saved",Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Upload>options = new FirebaseRecyclerOptions.Builder<Upload>().setQuery(databaseReference,Upload.class).build();

        FirebaseRecyclerAdapter<Upload,ViewHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Upload, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Upload model) {
holder.setData(getApplicationContext(),model.getName(),model.getAsile() );

holder.setonClickListener(new ViewHolder.clicklistner() {
    @Override
    public void onlongclick(View view, int position) {
nm = getItem(position).getName();
    showDeleteDataDiaglog(nm);
    }
});



            }


            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
                return new ViewHolder(view);
            }
        };
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }
    private void showDeleteDataDiaglog(String nm){
        AlertDialog.Builder builder = new AlertDialog.Builder(add_item.this);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure you want to Delete this data");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Query query = databaseReference.orderByChild("name").equalTo(nm);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            ds.getRef().removeValue();
                        }
                        Toast.makeText(add_item.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}