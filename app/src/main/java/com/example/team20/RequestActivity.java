package com.example.team20;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.team20.databinding.ActivityRequestBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class RequestActivity extends AppCompatActivity {
    private ActivityRequestBinding binding;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRequestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");
        arrayList.add("7");
        arrayList.add("8");
        arrayList.add("9");
        arrayList.add("10");
        arrayList.add("11");
        arrayList.add("12");
        arrayList.add("13");
        arrayList.add("14");
        arrayList.add("15");
        arrayList.add("16");
        arrayList.add("17");
        arrayList.add("18");
        arrayList.add("19");
        arrayList.add("20");
        arrayList.add("21");
        arrayList.add("22");
        arrayList.add("23");
        arrayList.add("24");
        arrayList.add("25");
        arrayList.add("26");
        arrayList.add("27");
        arrayList.add("28");
        arrayList.add("29");
        arrayList.add("30");

        spinner = binding.spnRequest;

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        binding.nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_my_page:
                        Intent intent_my_page = new Intent(RequestActivity.this,MainActivity.class);
                        startActivity(intent_my_page);
                        break;
                    case R.id.item_borrow:
                        Intent intent_borrow = new Intent(RequestActivity.this, RentPageActivity.class);
                        startActivity(intent_borrow);
                        break;
                    case R.id.item_registration:
                        Intent intent_registration = new Intent(RequestActivity.this,RegisterActivity.class);
                        startActivity(intent_registration);
                        break;
                    case R.id.item_return:
                        Intent intent_return = new Intent(RequestActivity.this,ReturnActivity.class);
                        startActivity(intent_return);
                        break;
                }
                return false;
            }
        });


        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        binding.btnRequestFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRequestDialog();
            }
        });

    }
    void showRequestDialog(){
        AlertDialog.Builder changeBuilder = new AlertDialog.Builder(RequestActivity.this)
                .setMessage("요청하겠습니까?")
                .setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        AlertDialog changeDlg = changeBuilder.create();
        changeDlg.show();
    }
}