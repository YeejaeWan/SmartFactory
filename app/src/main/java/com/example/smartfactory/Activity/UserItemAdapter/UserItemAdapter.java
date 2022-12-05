package com.example.smartfactory.Activity.UserItemAdapter;

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





public class UserItemAdapter extends RecyclerView.Adapter<UserItemAdapter.CustomViewHolder>{
    private ArrayList<UserItem> arrayList;
    android.content.Context parentContext;
    public UserItemAdapter(ArrayList<UserItem> arrayList) {
        System.out.println("CustomViewAdapter.CustomViewAdapter on init");
        this.arrayList = arrayList;
    }

    public void setFriendList(ArrayList<UserItem> list){
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
        int pos=holder.getLayoutPosition();
        holder.itemView.setTag(pos);
        Button accept = new Button(holder.itemView.getContext());
        Button deny = new Button(holder.itemView.getContext());
        Button delete = new Button(holder.itemView.getContext());
        Button move = new Button(holder.itemView.getContext());
        Button follow = new Button(holder.itemView.getContext());
        Button requested = new Button(holder.itemView.getContext());
        accept.setText("수락");
        deny.setText("거절");
        delete.setText("삭제");
        move.setText("이동");
        follow.setText("팔로우");
        requested.setText("취소");

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Callretrofit.patch_follower(arrayList.get(pos).getFollowershipIndex(),true);
                holder.linearLayout.removeView(holder.button1);
                holder.linearLayout.removeView(holder.button2);
                holder.button2=null;
                holder.button1 =delete;
                holder.linearLayout.addView(holder.button1);

            }
        });
        deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Callretrofit.delete_follower(arrayList.get(pos).getFollowershipIndex());
                arrayList.remove(pos);
                notifyItemRemoved(pos);
                notifyDataSetChanged();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Callretrofit.delete_follower(arrayList.get(pos).getFollowershipIndex());
                arrayList.remove(pos);
                notifyItemRemoved(pos);
                notifyDataSetChanged();
            }
        });
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), OtherUserActivity.class);
                intent.putExtra("followerShipIndex",arrayList.get(pos).getFollowershipIndex());
                intent.putExtra("userName",arrayList.get(pos).getUserName());
                intent.putExtra("context",arrayList.get(pos).getContext());

                view.getContext().startActivity(intent);
            }
        });
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Callretrofit.post_follower(new FollowshipVO(MainActivity.userId,arrayList.get(pos).getUserName()));
                holder.linearLayout.removeView(holder.button1);
                holder.button1 =requested;
                holder.linearLayout.addView(holder.button1);

            }
        });
        requested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Callretrofit.delete_follower(arrayList.get(pos).getFollowershipIndex());
                holder.linearLayout.removeView(holder.button1);
                holder.button1 =follow;
                holder.linearLayout.addView(holder.button1);
            }
        });
        holder.userName.setText(arrayList.get(pos).getUserName());
        System.out.println("CustomViewAdapter.onBindViewHolder : userName="+arrayList.get(pos).getUserName()+arrayList.get(pos).getContext());
        if (arrayList.get(pos).getContext().equals(Context.request)) {
            holder.linearLayout.removeView(holder.button1);
            holder.linearLayout.removeView(holder.button2);
            holder.button1 =accept;
            holder.button2 =deny;
            holder.linearLayout.addView(holder.button1);
            holder.linearLayout.addView(holder.button2);
        }
        else if (arrayList.get(pos).getContext().equals(Context.delete)){
            holder.linearLayout.removeView(holder.button1);
            holder.linearLayout.removeView(holder.button2);
            holder.button1 =delete;
            holder.linearLayout.addView(holder.button1);
        }
        else if (arrayList.get(pos).getContext().equals(Context.move)){
            holder.linearLayout.removeView(holder.button1);
            holder.linearLayout.removeView(holder.button2);
            holder.button1 =move;
            holder.linearLayout.addView(holder.button1);
            holder.linearLayout.removeView(holder.button2);

        }
        else if (arrayList.get(pos).getContext().equals(Context.requested)){
            holder.linearLayout.removeView(holder.button1);
            holder.linearLayout.removeView(holder.button2);
            holder.button1 =requested;
            holder.linearLayout.addView(holder.button1);

        }
        else {
            holder.linearLayout.removeView(holder.button1);
            holder.linearLayout.removeView(holder.button2);
            holder.button1 =follow;
            holder.linearLayout.addView(holder.button1);


        }


    }

    @Override
    public int getItemCount() {
        return (arrayList!=null ? arrayList.size():0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        protected TextView userName;
        protected Button button1;
        protected Button button2;

        protected boolean enabled;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            System.out.println("CustomViewHolder.CustomViewHolder");
            this.linearLayout=itemView.findViewById(R.id.userList_linearLayout);
            this.userName = itemView.findViewById(R.id.UserName);
        }

    }
}