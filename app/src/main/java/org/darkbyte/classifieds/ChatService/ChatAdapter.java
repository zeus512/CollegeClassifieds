package org.darkbyte.classifieds.ChatService;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.darkbyte.classifieds.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by parvesh_dhull on 4/2/17.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {


    private Context context;

    private String TAG = "TAG";
    private  String user;
    private List<modelChat> chats = new ArrayList<modelChat>();


    @Override
    public int getItemCount() {
        return chats.size();
    }

    public ChatAdapter(List<modelChat>  modelChat, Context context,String user) {
        this.chats = modelChat;
        this.context = context;
        this.user = user;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.one_chat_dialog, parent, false);



        return new MyViewHolder(itemView);
    }

    public void refresh(List<modelChat> modelChat,Context context,String user) {
        this.chats = modelChat;
        this.context = context;
        this.user = user;

        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, final int position) {

        modelChat chat = chats.get(position);
        holder.textView.setText(chat.getMessage());
        Resources r = context.getResources();
        int px = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 220, r.getDisplayMetrics() );
        int pxM = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 12, r.getDisplayMetrics() );
        if (chat.getSender().equals(user)){

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(px, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.gravity = Gravity.RIGHT;
            params.bottomMargin = pxM;

            holder.relativeLayout.setLayoutParams(params);
        }   else {

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(px, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.gravity = Gravity.LEFT;
            params.bottomMargin = pxM;

            holder.relativeLayout.setLayoutParams(params);
        }


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        RelativeLayout relativeLayout;
        public MyViewHolder(View view) {
            super(view);
          textView  = (TextView) view.findViewById(R.id.message);
            relativeLayout = (RelativeLayout)view.findViewById(R.id.chatServiceRelativeLayout);



        }
    }






}

