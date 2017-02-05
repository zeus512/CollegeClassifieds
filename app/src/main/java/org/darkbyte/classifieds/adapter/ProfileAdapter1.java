package org.darkbyte.classifieds.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.darkbyte.classifieds.ChatService.Chat_Service;
import org.darkbyte.classifieds.R;
import org.darkbyte.classifieds.payement_gateway.Payement_MainActivity;
import org.darkbyte.classifieds.settings.AccountInformation;


/**
 * Created by sukhbir on 25/8/16.
 */
public class ProfileAdapter1 extends RecyclerView.Adapter<ProfileAdapter1.viewHolder> {

    String list1[];
    Integer image1[];
    Context context;

    public ProfileAdapter1(Context context, String[] list1, Integer[] image1) {
        this.list1 = list1;
        this.context=context;
        this.image1 = image1;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, final int position) {
        holder.title.setText(list1[position]);
        holder.image.setImageResource(image1[position]);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(position+1){
                    case 1:
                        context.startActivity(new Intent(context,AccountInformation.class));
                        break;
//                    case 2:
//                        context.startActivity(new Intent(context,answers.class));
//                        break;
//                    case 3:
//                        context.startActivity(new Intent(context,appointments.class));
//                        break;
                    case 4:
                        context.startActivity(new Intent(context,Payement_MainActivity.class));
                        break;
                    case 5:
                        context.startActivity(new Intent(context,Chat_Service.class));
                        break;

                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return list1.length;
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView image;
        private LinearLayout parent;

        public viewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.title);
            parent=(LinearLayout)itemView.findViewById(R.id.parent);
            image=(ImageView)itemView.findViewById(R.id.image);
        }
    }
}
