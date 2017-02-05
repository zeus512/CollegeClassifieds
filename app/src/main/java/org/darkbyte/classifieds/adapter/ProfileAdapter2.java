package org.darkbyte.classifieds.adapter;

        import android.content.Context;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import org.darkbyte.classifieds.R;


/**
 * Created by sukhbir on 25/8/16.
 */
public class ProfileAdapter2 extends RecyclerView.Adapter<ProfileAdapter2.viewHolder> {

    String list1[];
    Integer image1[];
    Context context;

    public ProfileAdapter2(Context context, String[] list1, Integer[] image1) {
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
//                    case 1:
//                        context.startActivity(new Intent(context,treatments.class));
//                        break;
//                    case 2:
//                        context.startActivity(new Intent(context,prescriptions.class));
//                        break;
//                    case 3:
//                        context.startActivity(new Intent(context,appointments.class));
//                        break;
//                    case 4:
//                        context.startActivity(new Intent(context,packages.class));
//                        break;


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
