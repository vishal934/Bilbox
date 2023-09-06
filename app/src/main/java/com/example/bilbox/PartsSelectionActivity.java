package com.example.bilbox;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PartsSelectionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PartsAdapter adapter;
    private List<Part> partsList;
    private List<Part> selectedParts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parts_selection);
        selectedParts = new ArrayList<>();
        recyclerView = findViewById(R.id.partsRecyclerView);
        Button continueButton = findViewById(R.id.continue_button);
        partsList = new ArrayList<>();
        partsList.add(new Part("Wheel", R.drawable.wheels));
        partsList.add(new Part("Seat", R.drawable.seats));
        partsList.add(new Part("Door", R.drawable.cardoor));
        partsList.add(new Part("Engine", R.drawable.enginee));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new PartsAdapter(partsList, this);
        recyclerView.setAdapter(adapter);
        continueButton.setOnClickListener(v -> {

            selectedParts.clear();
            for (Part part : partsList) {
                if (part.isSelected()) {
                    selectedParts.add(part);
                }
            }
            Intent intent = new Intent(PartsSelectionActivity.this, AssemblyActivity.class);
            intent.putParcelableArrayListExtra("selectedParts", (ArrayList<? extends Parcelable>) selectedParts);
            startActivity(intent);
            Toast.makeText(this, "CONTINUE button clicked", Toast.LENGTH_SHORT).show();
        });
    }
}
