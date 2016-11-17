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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by IamDeveloper on 8/16/2016.
 */
public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.ViewHolder> {
    private OnItemClick itemClick;
    private Context ctx;
    private ArrayList<String> imageList = new ArrayList<>();
    private int[] viewType = {1,0,1,0,1,0,1,0,1,0};
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


    private class FirstImageHolder extends ViewHolder {
         ImageView imageView;

        FirstImageHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.image);

        }
    }

    private class SecondImageHolder extends ViewHolder {
         ImageView imageView;

        SecondImageHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case 0:
                View firstview = inflater.inflate(R.layout.content_recyclerview_1, parent, false);
                return new FirstImageHolder(firstview);
            case 1:
                View secondview = inflater.inflate(R.layout.content_recyclerview_2, parent, false);
                return new SecondImageHolder(secondview);

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
                FirstImageHolder holder1 = (FirstImageHolder) holder;
                Picasso.with(ctx).load(imageList.get(position)).into(holder1.imageView);
                break;
            case 1:
                SecondImageHolder holder2 = (SecondImageHolder) holder;
                Picasso.with(ctx).load(imageList.get(position)).into(holder2.imageView);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return viewType[position];
    }
}
