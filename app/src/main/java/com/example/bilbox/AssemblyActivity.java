package com.example.bilbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssemblyActivity extends AppCompatActivity {

    private RecyclerView selectedPartsRecyclerView;
    private AssemblyAdapter assemblyAdapter;
    private FrameLayout assemblyArea;
    private List<Part> selectedParts;
    private LinearLayout assemblyContainer;
    private List<Part> dragParts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assembly);
        selectedPartsRecyclerView = findViewById(R.id.selectedPartsRecyclerView);
        assemblyArea = findViewById(R.id.assemblyArea);
        assemblyContainer = findViewById(R.id.assemblyContainer);
        selectedParts = getIntent().getParcelableArrayListExtra("selectedParts");
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navigateToColorSelection();
            }
        });
        selectedPartsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        assemblyAdapter = new AssemblyAdapter(selectedParts, this);
        selectedPartsRecyclerView.setAdapter(assemblyAdapter);
        assemblyArea.setOnDragListener(new MyDragListener());
        for (Part part : selectedParts) {
            addPartToAssemblyArea(part);

        }
    }

    private void navigateToColorSelection() {
        Intent intent = new Intent(AssemblyActivity.this, ColorSelectionActivity.class);
        intent.putParcelableArrayListExtra("selectedParts", new ArrayList<>(selectedParts));
        startActivity(intent);

    }

    private void addPartToAssemblyArea(Part part) {

        PartView partView = new PartView(this);
        partView.setPart(part);
        assemblyContainer.addView(partView);
    }

    private class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (action) {
                case DragEvent.ACTION_DROP:
                    PartView droppedPartView = (PartView) event.getLocalState();
                    Part droppedPart = droppedPartView.getPart();
                    addPartToAssemblyArea(droppedPart);
                    dragParts.add(droppedPart);
                    droppedPartView.setVisibility(View.VISIBLE);
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundColor(Color.TRANSPARENT);
                    return true;
                default:
                    break;
            }
            return true;
        }
    }


}
