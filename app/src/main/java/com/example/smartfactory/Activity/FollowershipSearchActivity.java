package com.example.smartfactory.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartfactory.Activity.CustomAdapter.Context;
import com.example.smartfactory.Activity.CustomAdapter.CustomViewAdapter;
import com.example.smartfactory.Activity.mainActivity.MainActivity;
import com.example.smartfactory.R;
import com.example.smartfactory.network.Callretrofit;
import com.example.smartfactory.Activity.CustomAdapter.userItem;
import com.example.smartfactory.network.GetMyrelation;

import java.util.ArrayList;
import java.util.List;

public class FollowershipSearchActivity extends AppCompatActivity {
    EditText inputUserId;
    Button findUserButton;

    CustomViewAdapter foundUserAdapter;
    ArrayList<userItem> foundArraylist;// 이동이나, 취소, 팔로우 상태를 가짐
    RecyclerView foundRecyclerView;     //이동:getFollow 리스트에 있고 enable인 경우
                                        //취소:getFollow 리스트에 있고 disable

    CustomViewAdapter followerAdapter;
    ArrayList<userItem> followerArrayList; // 삭제나 요청 상태를 가짐
    RecyclerView followerRecyclerView;//삭제: getFollower 에 있고 enalble인 경우.
                                       //요청 getFollower 에 있고 disable인경우






    class GetListOfUserTheread extends Thread{
        List<userItem> foundlist_found_true;
        List<userItem> foundlist_found_false;
        List<userItem> foundlist_found_just;
        List<String> listOfuser;

        List<String> followTrueList;
        List<String> followFalseList;
        List<userItem>trueFollow;
        List<userItem>falseFollow;
        GetListOfUserTheread(ArrayList<userItem> followTrueList,ArrayList<userItem> followFalseList){
            this.followTrueList=new ArrayList<>();
            this.followFalseList=new ArrayList<>();
            foundlist_found_true=new ArrayList<>();
            foundlist_found_false=new ArrayList<>();
            foundlist_found_just=new ArrayList<>();
            trueFollow=followTrueList;
            falseFollow=followFalseList;

            for (userItem s:followTrueList) {
                this.followTrueList.add(s.getUserName());
            }
            for (userItem s:followFalseList) {
                this.followFalseList.add(s.getUserName());
            }
        }
        @Override
        public void run() {
            super.run();
            listOfuser= Callretrofit.get_list_of_user_by(inputUserId.getText().toString());

            for (int i=0;i<listOfuser.size();i++) {
                String id=listOfuser.get(i);
                if (MainActivity.userId.equals(id)){
                    listOfuser.remove(i);
                    break;
                }
            }
            int offset1=0;
            int offset2=0;
            for (int i=0; i<listOfuser.size();i++){
                String id=listOfuser.get(i);
                if(followTrueList.contains(id)){
                    foundlist_found_true.add(trueFollow.get(offset1));
                    offset1++;
                }
                else if(followFalseList.contains(id)){
                    foundlist_found_false.add(falseFollow.get(offset2));
                    offset2++;
                }else{
                    foundlist_found_just.add(new userItem(-1,id, Context.follow));
                }
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    foundArraylist=new ArrayList<>();
                    for (userItem u:foundlist_found_true) {
                        foundArraylist.add(u);
                    }
                    for (userItem u:foundlist_found_false) {
                        foundArraylist.add(u);
                    }
                    for (userItem u:foundlist_found_just) {
                        foundArraylist.add(u);
                    }
                    for (userItem u:foundArraylist) {
                        System.out.println("GetListOfUserTheread.run:: processed "+u.getUserName());
                    }
                    foundUserAdapter.setFriendList(foundArraylist);
                }
            });
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followership_search);
        foundRecyclerView=findViewById(R.id.foundUserRecycler);
        followerRecyclerView=findViewById(R.id.followerRecycler);
        inputUserId=findViewById(R.id.inputFindingUser);
        findUserButton=findViewById(R.id.findBtn);
        initRecycler();
        new Thread(){
            @Override
            public void run() {
                super.run();
                GetMyrelation getMyrelation=new GetMyrelation();
                getMyrelation.start();
                try {
                    getMyrelation.join();//릴레이션 분류 작업이 끝나야 버튼 클릭 수행 가능함
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        followerArrayList=new ArrayList<>();
                        for (userItem u:getMyrelation.getRequestContextUsers()) {
                            followerArrayList.add(u);
                        }
                        for (userItem u:getMyrelation.getRemoveContextUsers()) {
                            followerArrayList.add(u);
                        }
                        followerAdapter.setFriendList(followerArrayList);
                        //관계 분류가 끝나면, 온클릭 리스너 셋업
                        findUserButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                GetListOfUserTheread getListOfUserTheread = new GetListOfUserTheread( getMyrelation.getMoveContextUsers(), getMyrelation.getCancelContextUsers());
                                getListOfUserTheread.start();
                            }
                        });
                    }
                });
            }
        }.start();






    }
    private void initRecycler() {
        foundUserAdapter = new CustomViewAdapter(foundArraylist);
        foundRecyclerView.setAdapter(foundUserAdapter);
        foundRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        foundArraylist = new ArrayList<>();
        /*for(int i=1;i<=10;i++){
            foundArraylist.add(new userItem(0,"유저+"+i,true,Context.follow));
        }*/
        foundUserAdapter.setFriendList(foundArraylist);

        followerAdapter = new CustomViewAdapter(followerArrayList);
        followerRecyclerView.setAdapter(followerAdapter);
        followerRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        followerArrayList = new ArrayList<>();
        /*for(int i=1;i<=2;i++){
            followerArrayList.add(new userItem(0,"유저+"+i,true,Context.request));
        }
        for(int i=1;i<=10;i++){
            followerArrayList.add(new userItem(0,"유저+"+i,true,Context.move));
        }*/
        followerAdapter.setFriendList(followerArrayList);
    }
}
