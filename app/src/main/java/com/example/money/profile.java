package com.example.money;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class profile extends AppCompatActivity {
    ImageView home;
    ImageView transaction;
    ImageView history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        home = findViewById( R.id.imageView99);
        transaction = findViewById(R.id.imageView111);
        history = findViewById(R.id.imageView1l);




        home.setOnClickListener(view -> {
//            home.setSelected(true);
//            home.setSelected(false);
            home.setColorFilter(getResources().getColor(R.color.darkmagenta));
            Intent home = new Intent(profile.this, MainActivity.class);
            startActivity(home);
        });
        transaction.setOnClickListener(view -> {

            transaction.setColorFilter(getResources().getColor(R.color.darkmagenta));
            Intent transaction = new Intent(profile.this, transaction.class);
            startActivity(transaction);
        });
        history.setOnClickListener(view -> {

            history.setColorFilter(getResources().getColor(R.color.darkmagenta));
            Intent history = new Intent(profile.this, history.class);
            startActivity(history);
        });






    }
}