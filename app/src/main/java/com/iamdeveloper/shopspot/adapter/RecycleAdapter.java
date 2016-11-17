package com.iamdeveloper.shopspot.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.iamdeveloper.shopspot.R;
import com.iamdeveloper.shopspot.onClick.OnItemClick;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Random;


/**
 * Created by IamDeveloper on 11/16/2016.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private OnItemClick itemClick;
    private Context ctx;
    private ArrayList<String> imageList = new ArrayList<>();

    public RecycleAdapter(ArrayList<String> list,Context ctx, OnItemClick itemClick) {
        this.imageList = list;
        this.ctx = ctx;
        this.itemClick = itemClick;
    }

     class ViewHolder extends RecyclerView.ViewHolder {
         ImageView imageView;
         ViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.image);

        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View firstview = inflater.inflate(R.layout.content_image, parent, false);

        return new ViewHolder(firstview);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.onItemClick(view, holder.getAdapterPosition());
            }
        });
        Picasso.with(ctx).load(imageList.get(position)).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

}

