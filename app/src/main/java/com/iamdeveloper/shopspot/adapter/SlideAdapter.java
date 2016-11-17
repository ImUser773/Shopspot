package com.iamdeveloper.shopspot.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iamdeveloper.shopspot.R;
import com.iamdeveloper.shopspot.onClick.OnItemClick;

import java.util.ArrayList;


/**
 * Created by IamDeveloper on 8/16/2016.
 */
public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.ViewHolder> {
    private OnItemClick itemClick;
    private Context ctx;
    private ArrayList<String> imageList = new ArrayList<>();

    public SlideAdapter(ArrayList<String> list,Context ctx, OnItemClick itemClick) {
        this.imageList = list;
        this.ctx = ctx;
        this.itemClick = itemClick;
    }

     class ViewHolder extends RecyclerView.ViewHolder {
         ViewHolder(View v) {
            super(v);
        }
    }


    private class TextHolder extends ViewHolder {
         TextView textView;

         TextHolder(View v) {
            super(v);

        }
    }

    private class ImageHolder extends ViewHolder {
        private ImageView imageView;

        private ImageHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case 0:
                //View firstview = inflater.inflate(R.layout.content_text, parent, false);
                //return new TextHolder(firstview);
            case 1:
                View secondview = inflater.inflate(R.layout.content_image, parent, false);
                return new ImageHolder(secondview);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.onItemClick(view, holder.getAdapterPosition());
            }
        });

        switch (holder.getItemViewType()) {
            case 0:
                TextHolder holder1 = (TextHolder) holder;
                //holder1.textView.setText(data[position]);
                break;
            case 1:
                ImageHolder holder2 = (ImageHolder) holder;
                //holder2.imageView.setImage();
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return 9;
    }
}
