package com.example.bilbox;

import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

//public class PartView extends AppCompatImageView implements View.OnLongClickListener, View.OnDragListener {
//
//    private Part part;
//
//    public PartView(Context context) {
//        super(context);
//        init();
//    }
//
//    public PartView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init();
//    }
//
//    public PartView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init();
//    }
//
//    private void init() {
//        setOnLongClickListener(this);
//    }
//
//    public void setPart(Part part) {
//        this.part = part;
//        setImageResource(part.getImageResId());
//        setContentDescription(part.getName());
//    }
//
//    @Override
//    public boolean onLongClick(View v) {
//        // Start a drag operation when a part is long-pressed
//        ClipData data = ClipData.newPlainText("", "");
//        DragShadowBuilder shadowBuilder = new DragShadowBuilder(v);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            v.startDragAndDrop(data, shadowBuilder, v, 0);
//        }
//        return true;
//    }
//
//
//
//    @Override
//    public boolean onDrag(View v, DragEvent event) {
//        int action = event.getAction();
//        switch (action) {
//            case DragEvent.ACTION_DRAG_STARTED:
//                return true;
//            case DragEvent.ACTION_DRAG_ENTERED:
//                // Highlight the view when a part is dragged over it
//                setAlpha(0.7f);
//                return true;
//            case DragEvent.ACTION_DRAG_EXITED:
//            case DragEvent.ACTION_DROP:
//                // Handle the dropped part
//                // Remove the highlight when the part is dragged away
//                setAlpha(1f);
//                return true;
//            default:
//                break;
//        }
//        return true;
//    }
//}

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

public class PartView extends AppCompatImageView {

    private Part part;

    public PartView(Context context) {
        super(context);
        init();
    }

    public PartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ClipData clipData = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(clipData, shadowBuilder, view, 0);
                view.setVisibility(INVISIBLE);
                return true;
            }
        });

        setOnDragListener(new OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                final int action = event.getAction();
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:

                        if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                            return true;
                        } else {
                            return false;
                        }

                    case DragEvent.ACTION_DRAG_ENTERED:

                        v.setBackgroundColor(Color.LTGRAY);
                        return true;

                    case DragEvent.ACTION_DRAG_EXITED:

                        v.setBackgroundColor(Color.TRANSPARENT);
                        return true;

                    case DragEvent.ACTION_DROP:

                        ClipData.Item item = event.getClipData().getItemAt(0);
                        String dragData = item.getText().toString();


                        if (dragData.equals("")) {

                            PartView sourceView = (PartView) event.getLocalState();
                            Part droppedPart = sourceView.getPart();


                            sourceView.setVisibility(VISIBLE);


                            v.setBackgroundColor(Color.TRANSPARENT);

                            return true;
                        } else {
                            return false;
                        }

                    case DragEvent.ACTION_DRAG_ENDED:

                        v.setBackgroundColor(Color.TRANSPARENT);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
        setImageResource(part.getImageResId());
    }

    public void setParts() {

        setImageResource(R.drawable.enginee);
    }
}
