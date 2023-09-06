package com.example.bilbox;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FinalProductActivity extends AppCompatActivity {

    private RecyclerView finalProductRecyclerView;
    private List<Part> assembledParts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_product);
        assembledParts = getIntent().getParcelableArrayListExtra("assembledParts");
        int selectedColor = getIntent().getIntExtra("selectedColor", Color.TRANSPARENT);
        finalProductRecyclerView = findViewById(R.id.finalProductRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        finalProductRecyclerView.setLayoutManager(layoutManager);
        FinalProductAdapter adapter = new FinalProductAdapter(assembledParts,selectedColor);
        finalProductRecyclerView.setAdapter(adapter);
    }
}
