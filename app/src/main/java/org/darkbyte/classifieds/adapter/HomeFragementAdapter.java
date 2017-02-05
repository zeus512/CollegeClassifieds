package org.darkbyte.classifieds.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.darkbyte.classifieds.R;
import org.darkbyte.classifieds.model.HomeItem_Model;
import org.darkbyte.classifieds.product_full_details.Product_Full_Details;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 5/9/16.
 */
public class HomeFragementAdapter extends RecyclerView.Adapter<HomeFragementAdapter.MyViewHolder> {
    private Context context;
    private List<HomeItem_Model> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout linearLayout;
        public TextView itemTitle, itemPrice, itemDescription;
        public ImageView itemImage;
        public MyViewHolder(View view) {
            super(view);
            linearLayout=(LinearLayout)view.findViewById(R.id.home_item_layout);
            itemTitle = (TextView) view.findViewById(R.id.item_title);
            itemPrice = (TextView) view.findViewById(R.id.item_price);
            itemDescription =(TextView) view.findViewById(R.id.item_description);
             itemImage=(ImageView) view.findViewById(R.id.item_image);
        }
    }

    public  void  refresh(List<HomeItem_Model> list){
        this.list=list;
        notifyDataSetChanged();
    }
    public HomeFragementAdapter(Context mContext, List<HomeItem_Model> List) {
        this.context = mContext;
        this.list = List;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_home_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final HomeItem_Model homeItem_model = list.get(position);
        holder.itemTitle.setText(homeItem_model.getItemName());
        holder.itemDescription.setText(homeItem_model.getItemDescription());
        holder.itemPrice.setText(homeItem_model.getSellingPrice());
//        holder.itemImage.setImageBitmap(homeItem_model.getImages());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Product_Full_Details.class);
                intent.putExtra("ItemId",position+1);
                context.startActivity(intent);
            }
        });
        //glide set image
        // TODO: 5/2/17 set image of the item
        // loading album cover using Glide library
String a = null;

        Glide.with(context)
                .load(homeItem_model.getItemImage())
                .placeholder(R.drawable.h1)
                .into(holder.itemImage);
        //Glide.with(context).load(HomeItem_Model.getPackage_image()).into(holder.package_image);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}