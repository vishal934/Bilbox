package com.example.bilbox;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AssemblyAdapter extends RecyclerView.Adapter<AssemblyAdapter.ViewHolder> {

    private List<Part> selectedParts;
    private Context context;

    public AssemblyAdapter(List<Part> selectedParts, Context context) {
        this.selectedParts = selectedParts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_selected_part, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Part part = selectedParts.get(position);
        holder.partImage.setImageResource(part.getImageResId());
        holder.partName.setText(part.getName());
    }

    @Override
    public int getItemCount() {
        return selectedParts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView partImage;
        public TextView partName;

        public ViewHolder(View itemView) {
            super(itemView);
            partImage = itemView.findViewById(R.id.selectedPartImage);
            partName = itemView.findViewById(R.id.selectedPartName);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    ClipData clipData = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                    view.startDrag(clipData, shadowBuilder, view, 0);
                    return true;
                }
            });
        }



    }
}
