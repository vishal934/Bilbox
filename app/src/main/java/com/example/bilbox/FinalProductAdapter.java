package com.example.bilbox;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FinalProductAdapter extends RecyclerView.Adapter<FinalProductAdapter.ViewHolder> {

    private List<Part> assembledParts;
   private int selectedColor =Color.TRANSPARENT;
    public FinalProductAdapter(List<Part> assembledParts,  int selectedColor ) {
        this.assembledParts = assembledParts;
        this.selectedColor = selectedColor;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_final_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Part part = assembledParts.get(position);
        holder.partImage.setImageResource(part.getImageResId());
        holder.partName.setText(part.getName());
        Log.d("colorr",""+part.getColor());
        holder.partColor.setBackgroundColor(part.getColor());
    }

    @Override
    public int getItemCount() {
        return assembledParts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView partImage;
        public TextView partName;
        public View partColor;

        public ViewHolder(View itemView) {
            super(itemView);
            partImage = itemView.findViewById(R.id.finalPartImage);
            partName = itemView.findViewById(R.id.finalPartName);
            partColor = itemView.findViewById(R.id.finalPartColor);
        }
    }
}
