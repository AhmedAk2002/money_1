package com.example.money;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class transaction extends AppCompatActivity {

ImageView exit;
Button save;
String description;
String category;
RadioGroup radioGroup;
    private List<SurfaceControl.Transaction> transactions;


//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transaction);
//      categries

        exit = findViewById(R.id.imageView5);
        save = findViewById(R.id.savebutton);
        radioGroup = findViewById(R.id.radioGroup1);




        exit.setOnClickListener(view -> finish());

//        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
//        switch (checkedId) {
//            case R.id.radioEntertainment:
//                break;
//            case R.id.radioSocialLifestyle:
//                break;
//            case R.id.radioBeautyHealth:
//                break;
//            case R.id.radioOther:
//                break;
//        }
//
//
    }


    }
