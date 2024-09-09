package com.example.money;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class history extends AppCompatActivity {


    private TextView date;
    private ImageView home;
    private ImageView profile;
    private ImageView transaction;
    private ImageView exitvutton;
    List<String> transactionlist = new ArrayList<>();
    ArrayAdapter<String> transactionAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
        home = findViewById(R.id.imageView99);
        profile = findViewById(R.id.imageView12h);
        transaction = findViewById(R.id.imageView111);
        date = findViewById(R.id.textView5);
        exitvutton = findViewById(R.id.imageView4);
        loadData();
        ListView transactionListView = findViewById(R.id.transactionlist);
        transactionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, transactionlist);
        transactionListView.setAdapter(transactionAdapter);



        transactionAdapter.notifyDataSetChanged();


        exitvutton.setOnClickListener(view -> finish());



        home.setOnClickListener(view -> {
            home.setColorFilter(getResources().getColor(R.color.darkmagenta));
            Intent homeIntent = new Intent(history.this, MainActivity.class);
            startActivity(homeIntent);
        });

        transaction.setOnClickListener(view -> {
            transaction.setColorFilter(getResources().getColor(R.color.darkmagenta));
            Intent transactionIntent = new Intent(history.this, transaction.class);
            startActivity(transactionIntent);
        });

        profile.setOnClickListener(view -> {
            profile.setColorFilter(getResources().getColor(R.color.darkmagenta));
            Intent profileIntent = new Intent(history.this, profile.class);
            startActivity(profileIntent);
        });


    }
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("transactionData", MODE_PRIVATE);
        Gson gson = new Gson();
        String transactionJson = sharedPreferences.getString("transactionList", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        if (transactionJson != null) {
            transactionlist = gson.fromJson(transactionJson, type);
        }
    }

}
