//package com.example.x_splitter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CheckBox;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class AddGroupAdapter extends RecyclerView.Adapter<AddGroupAdapter.ViewHolder> {
//
//    private List<ListItem> listItems;
//    private Context context;
//
//    public AddGroupAdapter(List<ListItem> listItems, Context context) {
//        this.listItems = listItems;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.cardview_add_group, parent, false);
//        return new ViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        ListItem listItem = listItems.get(position);
//
//        holder.Users.setText(listItem.getUser());
//
//        holder.Checkbox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(((CheckBox) v).isChecked()){
//
//                }
//                else {
//
//                }
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return listItems.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        public TextView Users;
//        public android.widget.CheckBox Checkbox;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            Users = (TextView) itemView.findViewById(R.id.textview_addFriend);
//            Checkbox = (CheckBox) itemView.findViewById(R.id.checkBox_friend);
//
//        }
//    }
//}
