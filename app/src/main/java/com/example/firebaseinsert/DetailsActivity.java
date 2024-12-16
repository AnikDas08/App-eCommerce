package com.example.firebaseinsert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class DetailsActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        recyclerView=findViewById(R.id.Rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        FirebaseRecyclerOptions<ModelClass> options =
                new FirebaseRecyclerOptions.Builder<ModelClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("students"), ModelClass.class)
                        .build();
        adapter=new MyAdapter(options);
        recyclerView.setAdapter(adapter);





    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        MenuItem menuItem=menu.findItem(R.id.Search);
        SearchView searchView= (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                process(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                process(s);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void process(String s) {
        FirebaseRecyclerOptions<ModelClass> options =
                new FirebaseRecyclerOptions.Builder<ModelClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("students").orderByChild("name").startAt(s).endAt(s+"\uf8ff"), ModelClass.class)
                        .build();
        adapter=new MyAdapter(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);


    }

}