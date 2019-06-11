package com.example.x_splitter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private ArrayList<String> mGroupNames;
//    private ArrayList<String> mUnsettleStatus = new ArrayList<>();
//    private ArrayList<String>
    private ArrayList<String> mImageurls;
    private Context mcontext;

    public RecycleViewAdapter(Context mcontext,ArrayList<String> mGroupNames, ArrayList<String> mImageurls) {
        this.mGroupNames = mGroupNames;
        this.mImageurls = mImageurls;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_group_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        //widget is attached
        viewHolder.g_name.setText(mGroupNames.get(position));
        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext,mGroupNames.get(position),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {

        return mGroupNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView g_name;
//        TextView unset_status,set_status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.group_image);
            g_name = itemView.findViewById(R.id.group_name);
//            unset_status = itemView.findViewById(R.id.group_unsettle_status);
//            set_status = itemView.findViewById(R.id.group_settle_status);
        }
    }
}
