package org.darkbyte.classifieds.product_full_details;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.darkbyte.classifieds.ChatService.modelChat;
import org.darkbyte.classifieds.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by parvesh_dhull on 4/2/17.
 */

public class product_image_adapter extends RecyclerView.Adapter<product_image_adapter.MyViewHolder> {


    private Context context;


    private List<Bitmap> bitmaps = new ArrayList<Bitmap>();


    @Override
    public int getItemCount() {
        return bitmaps.size();
    }

    public product_image_adapter(List<Bitmap> bitmaps, Context context) {
        this.bitmaps = bitmaps;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_image_layout, parent, false);



        return new MyViewHolder(itemView);
    }

    public void refresh(List<Bitmap> bitmaps,Context context) {
        this.bitmaps = bitmaps;
        this.context = context;


        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Bitmap bitmap = bitmaps.get(position);
        holder.imageView.setImageBitmap(bitmap);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ((BitmapDrawable)holder.imageView.getDrawable()).getBitmap();

                ImageView imageView = (ImageView)((Activity)context).findViewById(R.id.largeImageView);
                imageView.setImageBitmap(bitmap);


            }
        });


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;


        public MyViewHolder(View view) {
            super(view);
          imageView  = (ImageView) view.findViewById(R.id.small_imageView);




        }
    }






}

