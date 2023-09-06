package com.example.bilbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bilbox.Part;
import com.example.bilbox.R;

import java.util.List;

public class PartsAdapter extends RecyclerView.Adapter<PartsAdapter.ViewHolder> {

    private List<Part> partsList;
    private Context context;

    public PartsAdapter(List<Part> partsList, Context context) {
        this.partsList = partsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_part, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Part part = partsList.get(position);
        holder.partName.setText(part.getName());
        holder.partImage.setImageResource(part.getImageResId());


        holder.partCheckbox.setChecked(part.isSelected());


        holder.partCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                part.setSelected(!part.isSelected());
            }
        });
    }
    @Override
    public int getItemCount() {
        return partsList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView partImage;
        public TextView partName;
        public CheckBox partCheckbox;
        public ViewHolder(View itemView) {
            super(itemView);
            partImage = itemView.findViewById(R.id.partImage);
            partName = itemView.findViewById(R.id.partName);
            partCheckbox = itemView.findViewById(R.id.partCheckbox);
        }
    }
}
