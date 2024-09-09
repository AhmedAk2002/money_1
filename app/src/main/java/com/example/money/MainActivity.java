package com.example.money;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText expenseTextView;
    EditText income;
    TextView allincome;
    TextView allexpense;
    TextView profit;
    TextView loss;
    ImageView addbutton;
    ImageView homeImageView;
    ImageView transactionImageView;
    ImageView historyImageView;
    ImageView profileImageView;
    ImageView delete;

    List<String> transactionlist = new ArrayList<>();
    ArrayAdapter<String> transactionAdapter;
    double allincomevalue = 0.0;
    double allexpensevalue = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expenseTextView = findViewById(R.id.Expensevalue);
        income = findViewById(R.id.allIncome);
        addbutton = findViewById(R.id.addbutton);
        allincome = findViewById(R.id.allIncome);
        allexpense = findViewById(R.id.Expensevalue);
        delete = findViewById(R.id.delete);
//        loss = findViewById(R.id.loss);
//        profit = findViewById(R.id.profit);
        homeImageView = findViewById(R.id.imageView99);
        transactionImageView = findViewById(R.id.imageView111);
        historyImageView = findViewById(R.id.imageView1l);
        profileImageView = findViewById(R.id.imageView12h);
        ListView reportListView = findViewById(R.id.transactionlist);
        transactionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, transactionlist);
        reportListView.setAdapter(transactionAdapter);
        loadData();

        setImageViewClickListeners();


        delete.setOnClickListener(view -> {{
            transactionlist.clear();
            transactionAdapter.notifyDataSetChanged();
            SharedPreferences sharedPreferences = getSharedPreferences("transactionData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("transactionList");
            editor.apply();
            Toast.makeText(MainActivity.this, "All clear.", Toast.LENGTH_SHORT).show();
            Intent refresh = getIntent();
            finish();
            startActivity(refresh);
        }});





        addbutton.setOnClickListener(v -> {
            handleTransaction();
            saveData();
            transactionAdapter.notifyDataSetChanged();
            Intent refresh = getIntent();
            finish();
            startActivity(refresh);

//
//            openNextActivity();


        });




    }


        private void loadData() {
            SharedPreferences sharedPreferences = getSharedPreferences("transactionData", MODE_PRIVATE);
            Gson gson = new Gson();
            String transactionJson = sharedPreferences.getString("transactionList", null);

            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            if (transactionJson != null) {
                transactionlist = gson.fromJson(transactionJson, type);
            } else {
                transactionlist = new ArrayList<>();
            }

            transactionAdapter.clear();
            transactionAdapter.addAll(transactionlist);
            transactionAdapter.notifyDataSetChanged();
        }




    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("transactionData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String transactionJson = gson.toJson(transactionlist);
        editor.putString("transactionList", transactionJson);
        editor.apply();


    }



    private void setImageViewClickListeners() {
        transactionImageView.setOnClickListener(view -> {
            transactionImageView.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.darkmagenta));
            Intent transactionIntent = new Intent(MainActivity.this, transaction.class);
            startActivity(transactionIntent);
        });

        historyImageView.setOnClickListener(view -> {
            historyImageView.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.darkmagenta));
            Intent historyIntent = new Intent(MainActivity.this, history.class);
            startActivity(historyIntent);
        });

        profileImageView.setOnClickListener(view -> {
            profileImageView.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.darkmagenta));
            Intent profileIntent = new Intent(MainActivity.this, profile.class);
            startActivity(profileIntent);
        });
    }

    private void handleTransaction() {

        String expensetext = expenseTextView.getText().toString().trim();
        double expensevalue = 0.0;
        String incometext = income.getText().toString().trim();
        double incomevalue = 0.0;

        if (!incometext.isEmpty() && isNumeric(incometext)) {
            incomevalue = Double.parseDouble(incometext);
            addIncome(incomevalue);
        } else {
            income.setError("Please enter a valid numeric income.");
            Toast.makeText(this, "Invalid income value. Try again.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!expensetext.isEmpty() && isNumeric(expensetext)) {
            expensevalue = Double.parseDouble(expensetext);
            addExpense(expensevalue); // Update total expenses
        } else {
            expenseTextView.setError("Please enter a valid numeric expense.");
            Toast.makeText(this, "Invalid expense value. Try again.", Toast.LENGTH_SHORT).show();
            return;
        }

        double diff = incomevalue - expensevalue;
        String transactionResult;

        if (diff < 0) {
            double lossValue = -diff;
            transactionResult = String.format(" Loss of -$%.2f", lossValue);
        } else if (diff > 0) {
            double profitValue = diff;
            transactionResult = String.format(" Profit of $%.2f", profitValue);
        } else {
            transactionResult = "Transaction: No profit, no loss.";
        }
        transactionlist.add(transactionResult);
        transactionAdapter.notifyDataSetChanged();

        updateReportList();
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void addIncome(double value) {
        allincomevalue += value;
    }

    private void addExpense(double value) {
        allexpensevalue += value;
    }

    private void updateReportList() {
        transactionlist.add(String.format(" Income: $%.2f", allincomevalue));
        transactionlist.add(String.format(" Expense: $%.2f", allexpensevalue));
        transactionAdapter.notifyDataSetChanged();
        income.setText("");
        expenseTextView.setText("");


    }
}
