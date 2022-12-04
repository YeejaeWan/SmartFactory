package com.example.smartfactory.Activity.CustomAdapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartfactory.Activity.OtherUserActivity;
import com.example.smartfactory.Activity.mainActivity.MainActivity;
import com.example.smartfactory.R;
import com.example.smartfactory.network.Callretrofit;
import com.example.smartfactory.network.VO.FollowshipVO;

import java.util.ArrayList;





public class CustomViewAdapter extends RecyclerView.Adapter<CustomViewAdapter.CustomViewHolder>{
    private ArrayList<userItem> arrayList;
    android.content.Context parentContext;
    public CustomViewAdapter(ArrayList<userItem> arrayList) {
        System.out.println("CustomViewAdapter.CustomViewAdapter on init");
        this.arrayList = arrayList;
    }

    public void setFriendList(ArrayList<userItem> list){
        this.arrayList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {  //생성될 때 생명주기
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item,parent,false);
        parentContext=parent.getContext();
        System.out.println("CustomViewAdapter.onCreateViewHolder");
        CustomViewHolder holder =new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {//실제 추가될 떄의 생명주기
        int pos=holder.getAbsoluteAdapterPosition();
        holder.itemView.setTag(pos);
        System.out.println("CustomViewAdapter.onBindViewHolder");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.userName.setText(arrayList.get(pos).getUserName());
        System.out.println("CustomViewAdapter.onBindViewHolder : userName="+arrayList.get(pos).getUserName()+arrayList.get(pos).getContext());
        switch (arrayList.get(pos).getContext()){
            case Context.move:
                holder.moveButton.setText(arrayList.get(pos).getContext());
                holder.moveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), OtherUserActivity.class);
                        intent.putExtra("followerShipIndex",arrayList.get(pos).getFollowershipIndex());
                        intent.putExtra("userName",arrayList.get(pos).getUserName());
                        intent.putExtra("context",arrayList.get(pos).getContext());

                        view.getContext().startActivity(intent);
                    }
                });
                break;
            case Context.requested:
                holder.moveButton.setText(arrayList.get(pos).getContext());
                holder.moveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Callretrofit.delete_follower(arrayList.get(pos).getFollowershipIndex());
                        onBindViewHolder(holder,pos);
                    }
                });
                break;
            case Context.follow:
                holder.moveButton.setText(arrayList.get(pos).getContext());
                holder.moveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Callretrofit.post_follower(new FollowshipVO(MainActivity.userId,arrayList.get(pos).getUserName()));
                    }
                });
                break;
            case Context.delete:
                holder.moveButton.setText(arrayList.get(pos).getContext());
                holder.moveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Callretrofit.delete_follower(arrayList.get(pos).getFollowershipIndex());
                    }
                });
                break;
            case Context.request:
                holder.moveButton2=new Button(parentContext);
                holder.moveButton.setText("수락");
                holder.moveButton2.setText("거절");
                holder.moveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Callretrofit.patch_follower(arrayList.get(pos).getFollowershipIndex(),true);
                    }
                });
                holder.moveButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Callretrofit.delete_follower(arrayList.get(pos).getFollowershipIndex());
                    }
                });
                holder.linearLayout.addView(holder.moveButton2);
                break;

        }

    }

    @Override
    public int getItemCount() {
        return (arrayList!=null ? arrayList.size():0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        protected TextView userName;
        protected Button moveButton;
        protected Button moveButton2=null;

        protected boolean enabled;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            System.out.println("CustomViewHolder.CustomViewHolder");
            this.linearLayout=itemView.findViewById(R.id.userList_linearLayout);
            this.userName = itemView.findViewById(R.id.UserName);
            this.moveButton=itemView.findViewById(R.id.moveToUserBtn);

        }
    }
}