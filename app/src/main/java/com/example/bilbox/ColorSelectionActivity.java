package com.example.bilbox;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColorSelectionActivity extends AppCompatActivity {

    private RecyclerView assembledPartsRecyclerView;
    private List<Part> assembledParts;
    private RadioGroup colorOptionsGroup;
    private Button applyColorButton;
    private Map<Part, Integer> partColors = new HashMap<>();

    private int selectedColor = Color.TRANSPARENT;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_section);
        assembledParts = getIntent().getParcelableArrayListExtra("selectedParts");
        Part part = new Part();
        part.setSelected(false);
        assembledPartsRecyclerView = findViewById(R.id.assembledPartsRecyclerView);
        colorOptionsGroup = findViewById(R.id.colorOptionsGroup);
        applyColorButton = findViewById(R.id.applyColorButton);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        assembledPartsRecyclerView.setLayoutManager(layoutManager);
        AssembledPartsAdapter adapter = new AssembledPartsAdapter(assembledParts);
        assembledPartsRecyclerView.setAdapter(adapter);
        applyColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Part> assembledPartsWithColors = new ArrayList<>();
                for (Part part : assembledParts) {
                    if (partColors.containsKey(part)) {
                        int selectedColor = partColors.get(part);
                        part.setColor(selectedColor);
                    }
                    assembledPartsWithColors.add(part);
                }
                Intent intent = new Intent(ColorSelectionActivity.this, FinalProductActivity.class);
                intent.putParcelableArrayListExtra("assembledParts", (ArrayList<? extends Parcelable>) assembledPartsWithColors);
                intent.putExtra("selectedColor", selectedColor);//send optional for testing
                startActivity(intent);
            }
        });

        colorOptionsGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedColor = getColorForRadioButton(checkedId);
                applyColorToAssembledParts(selectedColor);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private int getColorForRadioButton(int radioButtonId) {

        switch (radioButtonId) {
            case R.id.radioColorOption1:
                return Color.RED;
            case R.id.radioColorOption2:
                return Color.BLUE;
            case R.id.radioColorOption3:
                return Color.GRAY;
            case R.id.radioColorOption4:
                return Color.YELLOW;
            case R.id.radioColorOption5:
                return Color.GREEN;

            default:
                return Color.TRANSPARENT;
        }
    }

    private void applyColorToAssembledParts(int color) {
        for (Part part : assembledParts) {
            if (part.isSelected()) {
                Log.d("TAG", "applyColorToAssembledParts: " + color);
                part.setColor(color);
                partColors.put(part, color);
            }
        }


    }
}
