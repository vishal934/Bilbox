package com.example.bilbox;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AssembledPartsAdapter extends RecyclerView.Adapter<AssembledPartsAdapter.ViewHolder> {

    private List<Part> assembledParts;

    public AssembledPartsAdapter(List<Part> assembledParts) {
        this.assembledParts = assembledParts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_assembled_part, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Part part = assembledParts.get(position);
        holder.partImage.setImageResource(part.getImageResId());
        holder.textView.setText(part.getName());


        holder.partImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("parttr11",""+part.isSelected());
                part.setSelected(!part.isSelected());

                holder.textView.setBackgroundColor(part.getColor());
                Log.d("TAG", "onClick: "+part.getColor());
                part.setColor(part.getColor());

                notifyItemChanged(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return assembledParts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView partImage;
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            partImage = itemView.findViewById(R.id.assembledPartImage);
            textView = itemView.findViewById(R.id.txtColor);
        }
    }
}
