package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.os.Bundle;

import com.yalantis.library.Koloda;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SwipeAdapter adapter;
    private List<Integer> list;
    Koloda koloda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        koloda = findViewById(R.id.koloda);
        list = new ArrayList<>();
        adapter = new SwipeAdapter(this,list);
        koloda.setAdapter(adapter);

    }

}